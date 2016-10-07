package com.zl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zl.entity.ProductCustom;
import com.zl.mapper.ProductCustomMapper;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
public class ProductServiceImplTest {  
      
	@Resource
	private ProductCustomMapper productCustomMapper;
      
    @Test 
    public void getAllProductCustom() {  
    	List<ProductCustom> productCustom= productCustomMapper.getAllProductCustom("");
		for (ProductCustom productCustom2 : productCustom) {
			System.out.println(productCustom2.toString());
		}
    }  
    
    @Test 
    public void querByCategoryId() {  
    	List<ProductCustom> productCustom= productCustomMapper.querByCategoryId(1);
		for (ProductCustom productCustom2 : productCustom) {
			System.out.println(productCustom2.toString());
		}
    }  
}  
