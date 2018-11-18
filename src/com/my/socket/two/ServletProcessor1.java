package com.my.socket.two;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor1 {
    public void process(MyRequest1 request,MyResponse1 response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        URL[] urls = new URL[1];
        File classPath = new File(Constans.CLASS_PATH);
        URLStreamHandler urlStreamHandler =null;
        URLClassLoader loader = null;
        try {
            //构建类加载的路径
            String repository = new URL("file",null,classPath.getCanonicalPath()+File.separator).toString();
            urls[0] = new URL(null,repository,urlStreamHandler);
            //创建类加载器
            loader = new URLClassLoader(urls);
            //加载class class要带包名的全名
            Class myclass = loader.loadClass("com.my.socket.two."+servletName);
            Servlet servlet  = (Servlet) myclass.newInstance();
            servlet.service(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
