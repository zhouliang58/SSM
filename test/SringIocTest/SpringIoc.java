package SringIocTest;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/** 
 * @Description TODO(采用Spring的注解调试，仅仅支持Spring3.1及以上) 
 */  
/* 
 * Spring3.1后多了个spring-test-4.2.4.RELEASE.jar包，这个jar包专门用来支持JUnit基于注解的测试的，该jar包在spring-4.2.4-core中 
 * 该jar包里有个SpringJUnit4ClassRunner.class，用@RunWith注解加进来即可 
 *  
 * 注解@ContextConfiguration表示将ApplicationContext对象注入进来，就不用像以往那样在测试程序里先new了，直接使用 
 */  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
public class SpringIoc {  
      
    @Resource  
    private Date date;  
      
    @Test //测试Spring IOC的开发环境  
    public void springIoc() {  
        System.out.println(date);  
    }  
}  