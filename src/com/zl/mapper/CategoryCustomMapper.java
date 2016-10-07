package com.zl.mapper;

import java.util.List;

import com.zl.entity.CategoryCustom;

/**
 * Category扩展类
 * @author zhouliang
 *
 */
public interface CategoryCustomMapper {
    //查询类别信息，级联管理员  
	public List<CategoryCustom> getAllCategoryCustom(String type);
	
    //根据ids删除多条记录  
    public void deleteByIds(String ids); 
}
