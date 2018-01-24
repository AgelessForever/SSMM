package com.guigu.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guigu.ssm.exception.CustomException;
import com.guigu.ssm.mapper.ItemsMapper;
import com.guigu.ssm.mapper.ItemsMapperCustom;
import com.guigu.ssm.po.Items;
import com.guigu.ssm.po.ItemsCustom;
import com.guigu.ssm.po.ItemsExample;
import com.guigu.ssm.po.ItemsQueryVO;
import com.guigu.ssm.service.ItemsService;

/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.service.impl.ItemsServiceImpl</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月18日下午2:03:21 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */
@Service("itemsService")
@Transactional
public class ItemsServiceImpl implements ItemsService {
    
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;
    
    @Autowired
    private ItemsMapper itemsMapper;
    

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVO itemsQueryVO) throws Exception {
        return itemsMapperCustom.findItemsList(itemsQueryVO);
    }


    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        //必须指定id  如果id为空则报错
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }


    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        Items items =itemsMapper.selectByPrimaryKey(id);
        
        if(items==null) {
            throw new CustomException("修改的商品信息不存在...11");
        }
//        
        
        //完成属性的拷贝
        ItemsCustom itemsCustom =new ItemsCustom();
        //Spring的方式实现属性的拷贝
         BeanUtils.copyProperties(items, itemsCustom);
        return itemsCustom;
    }
    

    @Override
    public void deleteItems(Integer [] ids) throws Exception {
        
        List<Integer> list =new ArrayList<>();
        
        for (Integer id : ids) {
            list.add(id);
        }
        
        ItemsExample itemsExample =new ItemsExample();
        itemsExample.createCriteria().andIdIn(list);
        
        itemsMapper.deleteByExample(itemsExample);
        
    }
    
}


