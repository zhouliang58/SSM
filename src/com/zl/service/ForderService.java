package com.zl.service;

import com.zl.entity.Forder;
import com.zl.entity.ForderCustom;

public interface ForderService {
    //计算购物总价格  
    public double cluTotal(ForderCustom forderCustom);  
    
    public void save(Forder forder);
}
