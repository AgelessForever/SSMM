package com.guigu.ssm.exception;

/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.exception.CustomException</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月20日上午9:56:39 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */

public class CustomException extends Exception {

    private String message;

//    public CustomException() {
//
//    }

    public CustomException(String message) {
        super(message);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
