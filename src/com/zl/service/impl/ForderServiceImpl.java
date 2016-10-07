package com.zl.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.zl.entity.ForderCustom;
import com.zl.entity.Sorder;
import com.zl.service.ForderService;

@Service("forderService")
public class ForderServiceImpl implements ForderService{

	@Override
	public double cluTotal(ForderCustom forderCustom) {
        double total = 0.0;  
        for(Sorder sorder : forderCustom.getSorders()) {  
        	BigDecimal number = new BigDecimal(sorder.getNumber());
            BigDecimal price = sorder.getPrice();
            total += price.multiply(number).doubleValue();
        }  
        return total;  
	}

}
