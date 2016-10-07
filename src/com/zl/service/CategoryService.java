package com.zl.service;

import java.util.List;

import com.zl.entity.Category;
import com.zl.entity.CategoryCustom;

public interface CategoryService {
    //查询类别信息，级联管理员  
	public List<CategoryCustom> getAllCategoryCustom(String type);
	
    //查询所有类别信息，不级联查询
	public List<Category> getAllCategory();
	
    //根据ids删除多条记录  
    public void deleteByIds(String ids); 
    
    public void saveCategory(Category category);
    
    public void updateCategory(Category category);
    
    public List<Category> getByHot(boolean hot);
   
}
