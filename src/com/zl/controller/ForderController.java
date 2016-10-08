package com.zl.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zl.entity.Forder;
import com.zl.entity.ForderCustom;
import com.zl.entity.Sorder;
import com.zl.entity.User;
import com.zl.service.ForderService;
import com.zl.service.SorderService;

@Controller
@RequestMapping("forder")
public class ForderController {
	@Resource
	private ForderService forderService;
	
	@Resource
	private SorderService sorderSerice;
	
	@RequestMapping(value="save",method = RequestMethod.POST)
	public String save(Forder forder , HttpSession session){
		//为订单设置用户id
		User user = (User)session.getAttribute("user");
		forder.setUid(user.getId());
		
		//取订单
        ForderCustom forderCustom = (ForderCustom)session.getAttribute("forderCustom");  
        
        //取购物项并存
        List<Sorder> sorders = forderCustom.getSorders();   
        sorderSerice.saveSorders(sorders);
        
        //存订单
        double total = forderService.cluTotal(forderCustom);
        forder.setTotal(new BigDecimal(total));
        forderService.save(forder);

        //清空购物车
        session.setAttribute("forderCustom", null);     
		return "redirect:/index.html";
	}
}
