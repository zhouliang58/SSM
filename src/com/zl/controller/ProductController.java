package com.zl.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.entity.ProductCustom;
import com.zl.entity.ProductWithBLOBs;
import com.zl.service.ProductService;
import com.zl.util.DataGrid;
import com.zl.util.FileUploadUtil;
import com.zl.util.JsonUtils;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Resource
	private ProductService productService;
	
	/**
	 * @Description 查询分页
	 * @return
	 */
	@RequestMapping(value="/query", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	public @ResponseBody String  query( String name , Integer page,Integer rows,HttpServletResponse response) throws Exception {
		// 用来存储分页的数据
		DataGrid<ProductCustom> dg = new DataGrid<ProductCustom>();
		// 分页处理，显示第一页的10条数据
		PageHelper.startPage(page, rows);
		// 根据关键字和分页的参数查询相应的数据。
		List<ProductCustom> productList = productService.getAllProductCustom(name);

		// 取分页信息
		PageInfo<ProductCustom> pageInfo = new PageInfo<ProductCustom>(productList);
		long total = pageInfo.getTotal(); // 获取总记录数
		if(productList!=null){
			dg.setRows(productList);
			dg.setTotal(total);
			return JsonUtils.objectToJson(dg);
		}	
		return null;
	}
	
	/**
	 * 跳转到query页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toquery")
	public String toquery() throws Exception {
		return "product/query";
	}
	
	/**
	 * 根据id删除商品信息,会级联删除对应的sorder表中的信息
	 * @param ids(id1,id2...)
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteByIds", method = RequestMethod.POST)
	public ResponseEntity<byte[]> deleteByIds(String ids,HttpServletResponse response) throws Exception {
		productService.deleteByIds(ids);
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
	public String tosave() throws Exception {
		return "product/save";
	}
	
	/**
	 * 添加商品信息
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(ProductWithBLOBs product ,MultipartFile fileImage) throws Exception {
	    // 处理上传的单个图片    
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		fileUploadUtil.setFilePath("F:\\JavaCPP\\SSM\\pic\\");
		String newFileName = fileUploadUtil.uploadFile(fileImage);
		
		if(newFileName!=null&&!"".equals(newFileName)){
			product.setPic(newFileName);// 将新图片名称写到itemsCustom中
	    } else {
	        //如果用户没有选择图片就上传了，还用原来的图片
	    }

		product.setDate(new Date());
		productService.saveProduct(product);
		return "product/query";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toupdate")
	public String toupdate() throws Exception {
		return "product/update";
	}
	
	/**
	 * 更新商品信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(ProductWithBLOBs product,MultipartFile fileImage) throws Exception {
	    // 处理上传的单个图片    
	    String originalFileName = fileImage.getOriginalFilename();// 原始名称
	    // 上传图片
	    if (fileImage != null && originalFileName != null && originalFileName.length() > 0) {
	        // 存储图片的物理路径，实际中是要写到配置文件中的，不能在这写死
	        String pic_path = "F:\\JavaCPP\\SSM\\pic\\";
	        // 新的图片名称
	        String newFileName = UUID.randomUUID()
	                + originalFileName.substring(originalFileName
	                        .lastIndexOf("."));     
	        File newFile = new File(pic_path + newFileName);//新图片   
	        fileImage.transferTo(newFile);// 将内存中的数据写入磁盘
	        product.setPic(newFileName);// 将新图片名称写到itemsCustom中
	    } else {
	        //如果用户没有选择图片就上传了，还用原来的图片
	    }

		product.setDate(new Date());
		productService.updateProduct(product);
		return "product/query";
	}

}
