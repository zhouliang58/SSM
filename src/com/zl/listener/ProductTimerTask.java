package com.zl.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import com.zl.entity.Category;
import com.zl.entity.ProductCustom;
import com.zl.service.CategoryService;
import com.zl.service.ProductService;

@Component("productTimerTask") // 把该对象交给Spring管理
public class ProductTimerTask extends TimerTask {

	@Resource
	private ProductService productService = null; // 注入productService
	@Resource
	private CategoryService categoryService = null; // 注入categoryService

	private ServletContext application = null; // 定义一个ServletContext对象，因为我们更新了后台数据后，需要存入application域里面

	public void setApplication(ServletContext application) {
		this.application = application; // 通过监听器将这个application对象set进来，因为这里是无法拿application对象的
	}

	@Override
	public void run() {
		List<List<ProductCustom>> bigList = new ArrayList<List<ProductCustom>>(); // bigList中存放一个装有Category类的list
		// 1. 查询出热点类别
		for (Category category : categoryService.getByHot(true)) {
			// 根据热点类别id获取推荐商品信息
			List<ProductCustom> lst = productService.querByCategoryId(category.getId());
			bigList.add(lst); // 将装有category的list放到bigList中
		}
		// 2. 把查询的bigList交给application内置对象
		application.setAttribute("bigList", bigList); // 假设我们已经拿到了application对象
	}

}
