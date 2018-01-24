package com.guigu.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guigu.ssm.po.ItemsCustom;

/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.controller.JSONTest</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月20日下午2:07:19 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */
@Controller
public class JSONTest {
    
    @RequestMapping("/requestJson")
    public @ResponseBody ItemsCustom  requestJson(@RequestBody ItemsCustom itemsCustom) {
        System.out.println(itemsCustom.getName());
        System.out.println(itemsCustom.getPrice());
        return itemsCustom;
    }
    
    
    @RequestMapping("/responseJson")
    public @ResponseBody ItemsCustom  responseJson(ItemsCustom itemsCustom) {
        System.out.println(itemsCustom.getName());
        System.out.println(itemsCustom.getPrice());
        return itemsCustom;
    }
    
    
}


