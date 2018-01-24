package com.guigu.ssm.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.converter.CustomFileStringConverter</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月20日下午3:03:04 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */

public class CustomFileStringConverter implements Converter<MultipartFile, String> {

    @Override
    public String convert(MultipartFile arg0) {
        return null;
    }

}


