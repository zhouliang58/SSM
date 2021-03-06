package com.zl.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//@Component //监听器是web层的组件，它是tomcat实例化的，不是Spring实例化的。不能放到Spring中  
public class InitDataListener implements ServletContextListener {  
      
  private ProductTimerTask productTimerTask = null; //定义一个ProductTimerTask对象  
  private ApplicationContext context = null;  
    
  @Override  
  public void contextDestroyed(ServletContextEvent event) {  

  }  

  @Override  
  public void contextInitialized(ServletContextEvent event) {  

      context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());                 
      productTimerTask = (ProductTimerTask) context.getBean("productTimerTask");//从配置文件中获取ProductTimerTask对象  

      //把内置对象交给productTimerTask,因为productTimerTask里面是拿不到application的，只能通过监听器set给它  
      productTimerTask.setApplication(event.getServletContext());  

      //通过设置定时器，让首页的数据每个一小时同步一次（配置为守护线程）  
      new Timer(true).schedule(productTimerTask, 0, 1000*60*60);//每个一小时执行一次productTimerTask任务，即更新一下后台数据  
  }


}  
