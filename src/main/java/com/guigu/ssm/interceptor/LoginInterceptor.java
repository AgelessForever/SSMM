package com.guigu.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.interceptor.LoginInterceptor</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月20日下午3:32:30 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */

public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        //获取用户请求的url
        //如果用户没有登录  所有的操作除了登录以外都拦截
        String url =request.getRequestURI();
        
        if(url.contains("login.action")) {
            //如果是进行登录操作直接放行 
            return true;
        }
        
        
        //如果不是登录 判断用户是否已经登录
        HttpSession session =request.getSession();
        String username=(String) session.getAttribute("username");
        if(username!=null) {
            //用户已经登录 身份存在
            return true;
        }
        
        //这里需要用户进行登录  进行身份的验证
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
        
    }
    
}


