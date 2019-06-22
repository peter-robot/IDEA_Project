package cn.itcast.download;

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
public class downloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数，文件名称
        String filename = request.getParameter("filename");

        //2.使用字节输入流加载文件进内存。
            //2.1首先找到文件文件服务器路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("img/" + filename);
            //2.2用字节流关联
        FileInputStream fi = new FileInputStream(realPath);

        //3.设置response响应头
            //3.1设置响应头类型 content-type
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);
            //3.2设置响应头打开方式：content-disposition
        response.setHeader("content-disposition","attachment;filename="+filename);

        String h = request.getHeader("user-agent");
        String fileName = DownLoadUtils.getFileName(h, filename);


        //4.将输入流的数据写出到输出流中
        ServletOutputStream so = response.getOutputStream();
        byte[] byt = new byte[1024*8];
        int len =0;
        while ((len=fi.read(byt))!=-1){
            so.write(byt,0,len);
        }

        fi.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }
}
