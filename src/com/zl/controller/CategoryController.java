package com.zl.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.entity.Category;
import com.zl.entity.CategoryCustom;
import com.zl.service.CategoryService;
import com.zl.util.DataGrid;
import com.zl.util.JsonUtils;

/**
 * 后台Controller
 * 
 * @author zhouliang
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	@Resource
	private CategoryService categoryService;

	/**
	 * @Description 查询分页
	 * @return
	 */
	@RequestMapping(value="/query", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	public @ResponseBody String  query( String type , Integer page,Integer rows,HttpServletResponse response) throws Exception {
		// 用来存储分页的数据
		DataGrid<CategoryCustom> dg = new DataGrid<CategoryCustom>();
		// 分页处理，显示第一页的10条数据
		PageHelper.startPage(page, rows);
		// 根据关键字和分页的参数查询相应的数据。
		List<CategoryCustom> categoryList = categoryService.getAllCategoryCustom(type);

		// 取分页信息
		PageInfo<CategoryCustom> pageInfo = new PageInfo<CategoryCustom>(categoryList);
		long total = pageInfo.getTotal(); // 获取总记录数
		if(categoryList!=null){
			dg.setRows(categoryList);
			dg.setTotal(total);
			return JsonUtils.objectToJson(dg);
		}	
		return null;
	}
	
	/**
	 * @Description 查询所有商品类别，无分页信息
	 * @return
	 */
	@RequestMapping(value="/queryCategory", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	public @ResponseBody String queryCategory() throws Exception {
		List<Category> categoryList = categoryService.getAllCategory();
		return JsonUtils.objectToJson(categoryList);
	}
	/**
	 * 跳转到query页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toquery")
	public  String toquery() throws Exception {
		return "category/query";
	}
	
	/**
	 * 根据id删除商品类别,会删除对应类别下的所有商品
	 * @param ids(id1,id2...)
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteByIds", method = RequestMethod.POST)
	public  ResponseEntity<byte[]> deleteByIds(String ids,HttpServletResponse response) throws Exception {
		categoryService.deleteByIds(ids);
        //如果删除成功就会往下执行，我们将"true"以流的形式传给前台  
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] srtbyte = "true".getBytes();
        return new ResponseEntity<byte[]>(srtbyte,
                headers, HttpStatus.CREATED);
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tosave")
	public  String tosave() throws Exception {
		return "category/save";
	}
	
	/**
	 * 添加商品类别
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public  String save(Category category) throws Exception {
		categoryService.saveCategory(category);
		return "category/query";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toupdate")
	public  String toupdate() throws Exception {
		return "category/update";
	}
	
	/**
	 * 更新商品类别
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public  String update(Category category) throws Exception {
		categoryService.updateCategory(category);
		return "category/query";
	}

}
