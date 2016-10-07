package com.zl.service;

import java.util.List;

import com.zl.entity.Product;
import com.zl.entity.ProductCustom;
import com.zl.entity.ProductWithBLOBs;

public interface ProductService {
    //查询商品信息，级联查询商品类别
	public List<ProductCustom> getAllProductCustom(String name);
	
    //根据ids删除多条记录  
    public void deleteByIds(String ids); 
    
    public void saveProduct(ProductWithBLOBs product );
    
    public void updateProduct(ProductWithBLOBs product);
    
    public List<ProductCustom> querByCategoryId(int cid);  
    
    public Product getById(Integer id);
}
