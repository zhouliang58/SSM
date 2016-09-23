package com.zl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zl.entity.CategoryCustom;
import com.zl.mapper.CategoryCustomMapper;
import com.zl.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Resource
	private CategoryCustomMapper categoryCustomMapper;
	
	@Override
	public List<CategoryCustom> getAllCategoryCustom() {
		return categoryCustomMapper.getAllCategoryCustom();
	}

}
