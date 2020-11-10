package cn.itcase.response;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class downLoadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取需要下载的文件名
        String filename = req.getParameter("filename");
        //得到servletcontext对象
        ServletContext servletContext = this.getServletContext();
        //获取文件在服务器的完整路径
        String realPath = servletContext.getRealPath("/img/" + filename);
        System.out.println(realPath);
        //将我们需要的文件加载成字节流
        FileInputStream fileInputStream = new FileInputStream(realPath);
        //得到文件的类型
        String mimeType = servletContext.getMimeType(filename);
        //告诉客户端文件类型，以便于显示。
        //告诉客户端，以附件的形式下载，filename是下载时的文件名
        resp.setHeader("content-type",mimeType);
        resp.setHeader("content-disposition","attachment;filename="+filename);
        //创建字节输出流对象
        ServletOutputStream outputStream = resp.getOutputStream();
        //创建字节
        byte[] buff=new byte[1024*8];
        int len=0;
        while ((len=fileInputStream.read(buff))!=-1){
            //将字节输入流以字节形式进行输出，即传送给客户端
            outputStream.write(buff,0,len);
        }
        //关闭字节输入流
        fileInputStream.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
