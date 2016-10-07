package com.zl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zl.entity.Category;
import com.zl.entity.CategoryCustom;
import com.zl.entity.CategoryExample;
import com.zl.entity.ProductExample;
import com.zl.entity.ProductExample.Criteria;
import com.zl.mapper.CategoryCustomMapper;
import com.zl.mapper.CategoryMapper;
import com.zl.mapper.ProductMapper;
import com.zl.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Resource
	private CategoryCustomMapper categoryCustomMapper;
	
	@Resource
	private CategoryMapper categoryMapper;
	
	@Resource
	private ProductMapper productMapper;
	
	@Override
	public List<CategoryCustom> getAllCategoryCustom(String type) {
		if(type.trim().equals("")){
			type=null;
		}
		return categoryCustomMapper.getAllCategoryCustom(type);
	}

	@Override
	public void deleteByIds(String ids) {
		
		//级联删除,删除category则删除该类别下的所有product  
		//疑问？需不需要事务管理  经测试不需要，在spring配置中对delete*做了事务控制
		ProductExample productExample = new ProductExample();
		Criteria criteria = productExample.createCriteria();
		List<Integer> values = new ArrayList<Integer>();
		String[] idsStr = ids.split(",");
		for (String string : idsStr) {
			values.add(Integer.parseInt(string));
		}
		criteria.andCidIn(values);
		productMapper.deleteByExample(productExample);
		//删除类别
		categoryCustomMapper.deleteByIds(ids);		
	}

	@Override
	public void saveCategory(Category category) {
		categoryMapper.insert(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryMapper.updateByPrimaryKey(category);
	}

	@Override
	public List<Category> getAllCategory() {
		CategoryExample categoryExample = new CategoryExample();
		return categoryMapper.selectByExample(categoryExample);
	}

	@Override
	public List<Category> getByHot(boolean hot) {
		CategoryExample categoryExample = new CategoryExample();
		com.zl.entity.CategoryExample.Criteria criteria = categoryExample.createCriteria();
		criteria.andHotEqualTo(hot);
		return categoryMapper.selectByExample(categoryExample);
	}

}
