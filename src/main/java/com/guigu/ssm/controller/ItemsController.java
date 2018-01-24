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
 * <p>description��</p>
 * <p>@author������ʦ<p>   
 * <p> date:2018��1��18������2:26:04 </p>
 * <p>comments��    </p>
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
    
    //��Ʒ����  
    //ͨ��@ModelAttribute������ҳ���л�ȡ�������
    @ModelAttribute("itemsType")
    public Map<String, String> getItemsType(){
        Map<String, String> itemsType=new HashMap<>();
        itemsType.put("101", "������");
        itemsType.put("102", "ĸӤ��");
        itemsType.put("103", "�ֻ���");
        return itemsType;
    }
    
    
    //��Ʒ��Ϣ�޸�ҳ��
//    @RequestMapping("/editItems")
//    @RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
//    public ModelAndView eidtItems() throws Exception{
//        //��ʹ�ù̶��ĵ�����
//        ItemsCustom itemsCustom =itemsService.findItemsById(1);
//        
//        ModelAndView modelAndView =new ModelAndView();
//        //�������
//        modelAndView.addObject("items", itemsCustom);
//        //������ͼ
//        modelAndView.setViewName("items/editItems");
//        
//        return modelAndView;
//    }
    
    @RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
    public String eidtItems(Model model,@RequestParam(value="id",required=true)Integer items_id) throws Exception{
        ItemsCustom itemsCustom =itemsService.findItemsById(items_id);
        
        if(itemsCustom==null) {
            throw new  CustomException("�޸ĵ���Ʒ��Ϣ������....");
        }
        
        
        model.addAttribute("items", itemsCustom);
        return "items/editItems";
    }
    
    
    
    @RequestMapping("/editItemsSubmit")
//    public String editItemsSubmit(Model model,Integer id, @ModelAttribute("items")@Validated(value= {ValidGroupUpdate.class}) ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception{
      public String editItemsSubmit(HttpServletRequest request,Model model,Integer id,MultipartFile pic,
              @Validated(value= {ValidGroupUpdate.class}) ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception{
        //��ȡУ�������Ϣ
        if(bindingResult.hasErrors()) {
            //���������Ϣ
            List<ObjectError> allErrors=bindingResult.getAllErrors();
            //��������Ϣ���ݵ�ҳ��
            model.addAttribute("allErrors", allErrors);
            
            //ͨ��model�����ٴδ�������
            model.addAttribute("items", itemsCustom);
            
            //ת������Ʒ�޸�ҳ��
            return "items/editItems";
        }
        
        //�ϴ�ͼƬ��Ϣ
        //�õ�ԭʼ�ļ�������
        String orininalFilename=pic.getOriginalFilename(); //�ļ���
        if(pic!=null && orininalFilename!=null && orininalFilename.length()>0) {
            System.out.println(pic.getOriginalFilename()+"-----------222");
            //�洢·����Ϣ
            String pic_path=request.getSession().getServletContext().getRealPath("/uploadfile/");
            
            //��������ļ���
            String newFileName=UUID.randomUUID().toString()+"_"+orininalFilename;
            //�ļ��洢��λ��
            File newFile=new File(pic_path+newFileName);
            //ֱ�Ӱ�����д��
            pic.transferTo(newFile);
            
            itemsCustom.setPic(newFileName);
        }else {
            ItemsCustom oldItemCustom =itemsService.findItemsById(id);
            itemsCustom.setPic(oldItemCustom.getPic());
        }
      
        
        itemsService.updateItems(id, itemsCustom);
        
        //�ض�����Ʒ�б�ҳ�桢
//        return "redirect:queryItems.action";
        
        //ҳ��ת��
        return "forward:queryItems.action";
        
        //ת����jspҳ��
//        return "success";
    }
    
    
    
    //��Ʒ�Ĳ�ѯ
    @RequestMapping("/queryItems.action")
    public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVO itemsQueryVO) throws Exception{
        
        //��������ת��  ���ض�����Ƿ���request
        System.out.println(request.getParameter("id"));
        
        List<ItemsCustom> itemsList =itemsService.findItemsList(itemsQueryVO);
        //����ModelAndView
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        modelAndView.setViewName("items/itemsList");
        
        System.out.println("--------------guigu--------");
        return modelAndView;
        
    }
    
    //����ɾ��
    @RequestMapping("/deleteItems")
    public String deleteItems( Integer [] items_id) throws Exception{
        itemsService.deleteItems(items_id);
        
        return "forward:queryItems.action";
        
    }
    
    
    //����
    @RequestMapping("/addItems")
    public String  addItems(@Validated(value= {ValidGroupAdd.class}) ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception{
        return "forward:queryItems.action";
    }
    
    
    
    
}

