package com.zl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zl.entity.CategoryCustom;
import com.zl.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
public class CategoryServiceImplTest {  
      
	@Resource
	private CategoryService categoryService;
      
    @Test 
    public void getAllCategoryCustom() {  
    	List<CategoryCustom> result = categoryService.getAllCategoryCustom(" ");
        System.out.println(result.size());  
    }  
    
    @Test 
    public void deleteByIds() {  
    	categoryService.deleteByIds("24,25");
    } 
}  