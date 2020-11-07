package cn.itcast.web.servlet;

import cn.itcast.domain.user;
import org.apache.commons.beanutils.BeanUtils;
import sun.net.httpserver.HttpServerImpl;
import cn.itcast.dao.userDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpCookie;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //设置编码
        req.setCharacterEncoding("utf-8");
        /*//获取请求参数
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        //封装user对象
        user loginUser = new user();
        loginUser.setUsername(username);
        loginUser.setPassword(password);*/
        Map<String, String[]> map = req.getParameterMap();
        user loginUser=new user();
        try {
            /*要求：javaBean是标准的Java类
                该类必须被public修饰
                必须提供空参构造器
                成员变量必须被private修饰
                提供公共的setter和gettter
            *功能：用于封装数据

            *概念：成员变量
            属性：setter和getter方法截取后的产物，去掉set和get前缀，JavaBean操作的是属性
             */
            /*popultion会自动遍历操作属性，然后进行复赋值*/
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        userDao log = new userDao();
        user us = log.login(loginUser);
        if(us==null){
            req.getRequestDispatcher("/FailServlet").forward(req,resp);
        }
        else
        {
            /*将参数放到request域中*/
            req.setAttribute("user",us);
            req.getRequestDispatcher("/SuccessServlet").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
