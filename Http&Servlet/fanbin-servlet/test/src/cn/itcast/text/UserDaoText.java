package cn.itcast.text;
import cn.itcast.dao.userDao;
import cn.itcast.domain.user;
import org.junit.Test;

public class UserDaoText {
    /*
    * 白盒测试，如果测试成功会返回成功
    * */
    /*
    * test注解，可以在一个类中测试多个方法，不用写main方法
    * 一般使用断言操作处理结果
    * */
    @Test
    public void textLogin(){
        user loginUser= new user();
        loginUser.setUsername("fanbin");
        loginUser.setPassword("234");
        userDao dao = new userDao();
        user us = dao.login(loginUser);
        System.out.println(us);
    }
}
