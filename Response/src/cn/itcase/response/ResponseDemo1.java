package cn.itcase.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
* 这是用于重定向的代码
* 两种方式：
* 1.设置setStatus和setHeader
* 2.通过sendRedirec
* */
@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo1");
        resp.setStatus(302);
        //注意这里需要加上虚拟路径
        resp.setHeader("location","/Response_war_exploded/responseDemo2");
        //resp.sendRedirect("/Response_war_exploded/responseDemo2");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
