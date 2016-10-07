package com.zl.service.impl;

import org.springframework.stereotype.Service;

import com.zl.entity.ForderCustom;
import com.zl.entity.Product;
import com.zl.entity.Sorder;
import com.zl.entity.SorderCustom;
import com.zl.service.SorderService;

@Service("sorderService")
public class SorderServiceImpl implements SorderService{


	@Override
	public ForderCustom addSorder(ForderCustom forderCustom, Product product) {
        boolean isHave = false; //用来标记有没有重复购物项  
        //拿到当前的购物项  
        Sorder sorder = productToSorder(product);  
        //判断当前购物项是否重复，如果重复，则添加数量即可  
        for(Sorder old : forderCustom.getSorders()) {  
            if(old.getPid().equals(product.getId())) {  
                //购物项有重复，添加数量即可  
                old.setNumber(old.getNumber() + sorder.getNumber());  
                isHave = true;  
                break;  
            }  
        }  
        //当前购物项在购物车中不存在，新添加即可  
        if(!isHave) {  
        	forderCustom.getSorders().add(sorder);  
        }  
        return forderCustom;  
	}

	@Override
	public SorderCustom productToSorder(Product product) {
		SorderCustom sorder = new SorderCustom();  
        sorder.setName(product.getName());  
        sorder.setNumber(1);  
        sorder.setPrice(product.getPrice());  
        sorder.setPid(product.getId());
        sorder.setProduct(product);
        return sorder;  
	}

	@Override
	public ForderCustom updateSorder(Integer id, Integer number, ForderCustom forderCustom) {
		for(Sorder temp : forderCustom.getSorders()) {
			if(temp.getPid().equals(id)){
				temp.setNumber(number);
			}
		}
		return forderCustom;
	}

}
