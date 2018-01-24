package com.guigu.ssm.service;
/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.service.ItemsService</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月18日下午2:01:16 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */

import java.util.List;

import com.guigu.ssm.po.ItemsCustom;
import com.guigu.ssm.po.ItemsQueryVO;

public interface ItemsService {
    
    //商品列表查询
    public List<ItemsCustom> findItemsList(ItemsQueryVO itemsQueryVO) throws Exception;
    
    //根据id主键进行更新数据
    public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
    
    
    public ItemsCustom findItemsById(Integer id) throws Exception;
    
    public  void deleteItems(Integer [] ids) throws Exception;
}


