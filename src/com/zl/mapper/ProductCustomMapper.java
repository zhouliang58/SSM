package com.zl.mapper;

import java.util.List;

import com.zl.entity.ProductCustom;

/**
 * Category扩展类
 * @author zhouliang
 *
 */
public interface ProductCustomMapper {
    //查询类别信息，级联管理员  
	public List<ProductCustom> getAllProductCustom(String name);
	
	public List<ProductCustom> querByCategoryId(int cid);
	
}
