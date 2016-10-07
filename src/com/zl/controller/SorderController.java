package com.zl.controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zl.entity.ForderCustom;
import com.zl.entity.Product;
import com.zl.entity.Sorder;
import com.zl.service.ForderService;
import com.zl.service.ProductService;
import com.zl.service.SorderService;

@Controller
@RequestMapping("/sorder")
public class SorderController {
	
	@Resource
	private ProductService productService;
	
	@Resource
	private SorderService sorderService;
	
	@Resource
	private ForderService forderService;
	
	@RequestMapping("/addSorder")
    public String addSorder(Integer id , HttpSession httpSession) {  
        
        //1. 根据product.id获取相应的商品数据  
        Product product = productService.getById(id);
        
        //2. 判断当前session是否有购物车，如果没有则创建  
        if(httpSession.getAttribute("forderCustom") == null) {  
            //创建新的购物车，存储到session中  
        	httpSession.setAttribute("forderCustom", new ForderCustom(new ArrayList<Sorder>()));  
        }   
  
        //3. 把商品信息转化为sorder,并且添加到购物车中（判断购物项是否重复）  
        ForderCustom forderCustom = (ForderCustom) httpSession.getAttribute("forderCustom");  
        forderCustom = sorderService.addSorder(forderCustom, product);  
          
        //4. 计算购物的总价格  
        BigDecimal total = new BigDecimal(forderService.cluTotal(forderCustom));
        forderCustom.setTotal(total) ;  
        //5. 把新的购物车存储到session中  
        httpSession.setAttribute("forderCustom", forderCustom);  
        return "user/showCart";  
    } 
	
	@RequestMapping(value="/updateSorder", method = RequestMethod.POST)
	public ResponseEntity<byte[]>  updateSorder(Integer number , Integer id,HttpSession session){
		ForderCustom forderCustom = (ForderCustom) session.getAttribute("forderCustom");
		forderCustom = sorderService.updateSorder(id , number , forderCustom);
		//计算新的总价格
		BigDecimal total = new BigDecimal(forderService.cluTotal(forderCustom));
		forderCustom.setTotal(total);
		session.setAttribute("forderCustom", forderCustom);
		//以流的形式返回新的总价格
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] srtbyte = forderCustom.getTotal().toString().getBytes();
        return new ResponseEntity<byte[]>(srtbyte,
                headers, HttpStatus.CREATED);
	}
}
