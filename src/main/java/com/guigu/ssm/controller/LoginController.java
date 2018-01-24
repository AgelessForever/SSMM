package com.guigu.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.controller.LoginController</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月20日下午3:26:49 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */
@Controller
public class LoginController {
    
    @RequestMapping("/login")
    public String login(HttpSession session,String username,String password) {
        //调用UserService 进行身份的校验 ....
        //User findUser=userService.findUserByUsernameAndPassoword(username,password);
        //..根据用户是否存在执行相关的操作... 
        //if(findUser!=null){ ....}
        //如果用户存在 保存到Session作用域中
        session.setAttribute("username", username);
        //重定向到商品查询列表页面
        return "redirect:/items/queryItems.action";
        
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //销毁session
        //重定向到商品查询列表页面
        return "redirect:/items/queryItems.action";
        
    }
    
}


