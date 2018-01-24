package com.guigu.ssm.po;
/**       
 * <p>project_name:SpringMVCSpringMyBatis</p>
 * <p>package_name:com.guigu.ssm.po.ItemsQueryVO</p>
 * <p>description：</p>
 * <p>@author：刘老师<p>   
 * <p> date:2018年1月18日下午1:50:51 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 980991634@qq.com All Rights Reserved. </p>    
 */

import java.util.List;

public class ItemsQueryVO {

    // 商品信息
    private Items items;

    // 为了系统的可扩展性 对原始的POJO进行扩展
    private ItemsCustom itemsCustom;

    private List<ItemsCustom> itemsList;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public ItemsCustom getItemsCustom() {
        return itemsCustom;
    }

    public void setItemsCustom(ItemsCustom itemsCustom) {
        this.itemsCustom = itemsCustom;
    }

    public List<ItemsCustom> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemsCustom> itemsList) {
        this.itemsList = itemsList;
    }

}
