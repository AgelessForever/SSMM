package com.guigu.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.guigu.ssm.exception.CustomException;
import com.guigu.ssm.po.ItemsCustom;
import com.guigu.ssm.po.ItemsQueryVO;
import com.guigu.ssm.service.ItemsService;
import com.guigu.ssm.validation.ValidGroupAdd;
import com.guigu.ssm.validation.ValidGroupUpdate;

/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.controller.ItemsController</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月18日下午2:26:04 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */
@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    @Qualifier("itemsService")
    private ItemsService itemsService;
    
    //商品分类  
    //通过@ModelAttribute可以在页面中获取这个数据
    @ModelAttribute("itemsType")
    public Map<String, String> getItemsType(){
        Map<String, String> itemsType=new HashMap<>();
        itemsType.put("101", "数码类");
        itemsType.put("102", "母婴类");
        itemsType.put("103", "手机类");
        return itemsType;
    }
    
    
    //商品信息修改页面
//    @RequestMapping("/editItems")
//    @RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
//    public ModelAndView eidtItems() throws Exception{
//        //先使用固定的的数据
//        ItemsCustom itemsCustom =itemsService.findItemsById(1);
//        
//        ModelAndView modelAndView =new ModelAndView();
//        //填充数据
//        modelAndView.addObject("items", itemsCustom);
//        //设置视图
//        modelAndView.setViewName("items/editItems");
//        
//        return modelAndView;
//    }
    
    @RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
    public String eidtItems(Model model,@RequestParam(value="id",required=true)Integer items_id) throws Exception{
        ItemsCustom itemsCustom =itemsService.findItemsById(items_id);
        
        if(itemsCustom==null) {
            throw new  CustomException("修改的商品信息不存在....");
        }
        
        
        model.addAttribute("items", itemsCustom);
        return "items/editItems";
    }
    
    
    
    @RequestMapping("/editItemsSubmit")
//    public String editItemsSubmit(Model model,Integer id, @ModelAttribute("items")@Validated(value= {ValidGroupUpdate.class}) ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception{
      public String editItemsSubmit(HttpServletRequest request,Model model,Integer id,MultipartFile pic,
              @Validated(value= {ValidGroupUpdate.class}) ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception{
        //获取校验错误信息
        if(bindingResult.hasErrors()) {
            //输出错误信息
            List<ObjectError> allErrors=bindingResult.getAllErrors();
            //将错误信息传递到页面
            model.addAttribute("allErrors", allErrors);
            
            //通过model对象再次传递数据
            model.addAttribute("items", itemsCustom);
            
            //转发到商品修改页面
            return "items/editItems";
        }
        
        //上传图片信息
        //得到原始文件的名字
        String orininalFilename=pic.getOriginalFilename(); //文件名
        if(pic!=null && orininalFilename!=null && orininalFilename.length()>0) {
            System.out.println(pic.getOriginalFilename()+"-----------222");
            //存储路径信息
            String pic_path=request.getSession().getServletContext().getRealPath("/uploadfile/");
            
            //随机生成文件名
            String newFileName=UUID.randomUUID().toString()+"_"+orininalFilename;
            //文件存储的位置
            File newFile=new File(pic_path+newFileName);
            //直接把数据写出
            pic.transferTo(newFile);
            
            itemsCustom.setPic(newFileName);
        }else {
            ItemsCustom oldItemCustom =itemsService.findItemsById(id);
            itemsCustom.setPic(oldItemCustom.getPic());
        }
      
        
        itemsService.updateItems(id, itemsCustom);
        
        //重定向到商品列表页面、
//        return "redirect:queryItems.action";
        
        //页面转发
        return "forward:queryItems.action";
        
        //转发到jsp页面
//        return "success";
    }
    
    
    
    //商品的查询
    @RequestMapping("/queryItems.action")
    public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVO itemsQueryVO) throws Exception{
        
        //测试请求转发  和重定向后是否共享request
        System.out.println(request.getParameter("id"));
        
        List<ItemsCustom> itemsList =itemsService.findItemsList(itemsQueryVO);
        //返回ModelAndView
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        modelAndView.setViewName("items/itemsList");
        
        System.out.println("--------------guigu--------");
        return modelAndView;
        
    }
    
    //批量删除
    @RequestMapping("/deleteItems")
    public String deleteItems( Integer [] items_id) throws Exception{
        itemsService.deleteItems(items_id);
        
        return "forward:queryItems.action";
        
    }
    
    
    //添加
    @RequestMapping("/addItems")
    public String  addItems(@Validated(value= {ValidGroupAdd.class}) ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception{
        return "forward:queryItems.action";
    }
    
    
    
    
}


