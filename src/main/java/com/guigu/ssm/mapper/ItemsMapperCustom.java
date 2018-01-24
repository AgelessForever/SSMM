package com.guigu.ssm.mapper;
/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.mapper.ItemsMapperCustom</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月18日下午1:49:40 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */

import java.util.List;

import com.guigu.ssm.po.ItemsCustom;
import com.guigu.ssm.po.ItemsQueryVO;

public interface ItemsMapperCustom {
    
    //商品查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVO itemsQueryVO) throws Exception;
}


