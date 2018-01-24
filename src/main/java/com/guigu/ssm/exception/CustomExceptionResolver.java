package com.guigu.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.exception.CustomExceptionResolver</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月20日上午9:58:42 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */

public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        CustomException customException =null;
        
        System.err.println(ex);
        if(ex instanceof CustomException) {
            customException =(CustomException) ex;
        }else {
            customException=new CustomException("未知的异常");
        }
        
        //得到错误信息
        String message =customException.getMessage();
        
        ModelAndView  modelAndView =new ModelAndView();
        modelAndView.addObject("message",message);
        //指向错误页面
        modelAndView.setViewName("error");
        
        return modelAndView;
    }

}


