package cn.itcast.download;

import javax.xml.crypto.dsig.Transform;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static javax.xml.crypto.dsig.Transform.BASE64;


public class DownLoadUtils {

    public static String getFileName(String agent, String filename) throws UnsupportedEncodingException {
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
//             火狐浏览器
//            BASE64 base64 = new Transform.BASE64();
//            filename = "=?utf-8?B?" + base64.encode(filename.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
