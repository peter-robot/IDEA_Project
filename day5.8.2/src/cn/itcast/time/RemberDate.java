package cn.itcast.time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/re")
public class RemberDate extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理中文乱码问题
        response.setContentType("text/html;charset=utf-8");

        //1.获取所有Cookies
        Cookie[] cookies = request.getCookies();
        //2.判断用户是否为第一次访问
        Cookie last_cookie = judgeFirst(cookies,"last");
        if(last_cookie == null){
            //2.1第一次访问：浏览器响应显示：亲，第一次访问！，同时记住第一次访问时间。
            response.getWriter().print("亲，第一次访问！");
        }else {
            //2.2不是第一次访问：取出上次保存的时间，显示到浏览器，同时记住当前的访问时间。
            String lasttime = last_cookie.getValue();
            response.getWriter().print("亲，你的上次访问时间是："+lasttime);
        }
        String time = String.format("%tF %<tT",new Date());
        time = time.replace(" ","日");
        Cookie c =  new Cookie("last",time);
        c.setMaxAge(3600);
        response.addCookie(c);

    }
    public Cookie judgeFirst(Cookie[] c,String cookiename){
        if(c==null){
            return null;
        }else {
            for (Cookie cookie:c) {
                String name = cookie.getName();
                if(cookiename.equals(name)){
                    return cookie;
                }
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
