package com.zl.service;

import com.zl.entity.ForderCustom;
import com.zl.entity.Product;
import com.zl.entity.SorderCustom;

public interface SorderService {
    //添加购物项，返回新的购物车  
    public ForderCustom addSorder(ForderCustom forderCustom, Product product);  
    //把商品数据转化为购物项  
    public SorderCustom productToSorder(Product product);  
    
    public ForderCustom updateSorder(Integer id , Integer number, ForderCustom forderCustom);
}
