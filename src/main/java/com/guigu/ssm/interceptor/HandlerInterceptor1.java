package com.guigu.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.interceptor.HandlerInterceptor1</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月20日下午3:10:07 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */

public class HandlerInterceptor1 implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        /**
         * 进入Handler方法之前执行 就是说Controller执行之前先调用此方法
         * 用于身份认证、身份授权、权限拦截
         */
        System.out.println("Handler......preHandler1...");
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /**
         * controller方法执行 但是未返回结果之前 调用此方法
         * 这里一般是用于把公共模型数据比如菜单导航栏 在这里传递到视图然后进行显示
         */
        System.out.println("information..... post ..over11");
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /**
         * 在Controller执行返回后  调用此方法 
         *  用于 统一的异常处理、 统一的日志处理、清理资源、
         */
        System.out.println("HandlerInterceptor1...afterCompletion");
    }
    
}


