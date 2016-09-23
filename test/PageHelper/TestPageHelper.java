package PageHelper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.entity.User;
import com.zl.entity.UserExample;
import com.zl.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
public class TestPageHelper {
	@Resource
	private UserMapper mapper;
	
    @Test
    public void testPageHelper() {
        // 执行查询并分页,TbItemExample是逆向工程自动生成的，用来进行条件查询，这里不设置则表示无条件
    	UserExample example = new UserExample();
        //分页处理，显示第一页的10条数据
        PageHelper.startPage(1, 10);
        List<User> list = mapper.selectByExample(example);//查询
        // 取商品列表
        for(User item : list) {
            System.out.println(item.getName());
        }
        // 取分页信息
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        long total = pageInfo.getTotal(); //获取总记录数
        System.out.println("共有商品信息：" + total);
    }
}