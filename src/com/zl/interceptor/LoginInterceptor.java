package com.zl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zl.entity.User;


public class LoginInterceptor implements HandlerInterceptor {

	// 进入Handler方法之前执行
	// 可以用于身份认证、身份授权。如果认证没有通过表示用户没有登陆，需要此方法拦截不再往下执行，否则就放行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 获取请求的url
		String url = request.getRequestURI();
		// 判断url是否公开地址（实际使用时将公开地址配置到配置文件中）
		// 这里假设公开地址是否登陆提交的地址
		if (url.indexOf("login.html") > 0||url.indexOf("index.html") > 0) {
			// 如果进行登陆提交，放行
			return true;
		}
     
		// 判断session
		HttpSession session = request.getSession();
		// 从session中取出用户身份信息
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return true;
		}

		// 执行到这里表示用户身份需要验证，跳转到登陆页面
        //保存当前客户想要去的url地址  
        String goURL = request.getServletPath();//获得用户想要去的地址  
        String param = request.getQueryString(); //获得地址中携带的参数  
        if(param != null) {  
            goURL = goURL + "?" + param; //重新拼好请求地址+参数  
        }  
        //把当前客户想要访问的地址，存储到session中  
        request.getSession().setAttribute("goURL", goURL);  
          
        //非法请求，跳转到登陆页面  
        request.getSession().setAttribute("error", "非法请求，请登录！");  
		request.getRequestDispatcher("/ulogin.jsp").forward(request, response);
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}
}
