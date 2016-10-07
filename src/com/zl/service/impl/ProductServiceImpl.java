package com.zl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zl.entity.Product;
import com.zl.entity.ProductCustom;
import com.zl.entity.ProductExample;
import com.zl.entity.ProductWithBLOBs;
import com.zl.entity.SorderExample;
import com.zl.mapper.CategoryMapper;
import com.zl.mapper.ProductCustomMapper;
import com.zl.mapper.ProductMapper;
import com.zl.mapper.SorderMapper;
import com.zl.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Resource
	private ProductCustomMapper productCustomMapper;
	
	@Resource
	private ProductMapper productMapper;
	
	@Resource
	private CategoryMapper categoryMapper;
	
	@Resource
	private SorderMapper sorderMapper;
	
	@Override
	public List<ProductCustom> getAllProductCustom(String name) {	
		return productCustomMapper.getAllProductCustom(name);
	}

	@Override
	public void deleteByIds(String ids) {
		// 级联删除sorder表中的信息
		SorderExample sorderExample = new SorderExample();
		SorderExample.Criteria criteria1 = sorderExample.createCriteria();
		List<Integer> values1 = new ArrayList<Integer>();
		String[] idsStr1 = ids.split(",");
		for (String string : idsStr1) {
			values1.add(Integer.parseInt(string));
		}
		criteria1.andPidIn(values1);
		sorderMapper.deleteByExample(sorderExample);
		
		ProductExample productExample = new ProductExample();
		ProductExample.Criteria criteria = productExample.createCriteria();
		List<Integer> values = new ArrayList<Integer>();
		String[] idsStr = ids.split(",");
		for (String string : idsStr) {
			values.add(Integer.parseInt(string));
		}
		criteria.andIdIn(values);
		productMapper.deleteByExample(productExample);
	}


	@Override
	public void saveProduct(ProductWithBLOBs product) {
		productMapper.insert(product);
	}

	@Override
	public void updateProduct(ProductWithBLOBs product) {
		productMapper.updateByPrimaryKeyWithBLOBs(product);
	}

	
	/**
	 * 先从数据库中查询类别是热点的项，然后再从数据库中根据热点类别级联查询该类别的商品，取前面四个（按时间排序）
	 */
	@Override
	public List<ProductCustom> querByCategoryId(int cid) {	
		return productCustomMapper.querByCategoryId(cid);
	}

	@Override
	public Product getById(Integer id) {
		return productMapper.selectByPrimaryKey(id);
	}




}
