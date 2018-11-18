package com.my.socket.two;

import com.my.socket.one.MyRequest;
import com.my.socket.one.MyResponse;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;

    public void awit() throws Exception {
        ServerSocket serverSocket;
        int port = 8080;
        serverSocket = new ServerSocket(port, 1, InetAddress.getByName("localhost"));
        while (!shutdown) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            MyRequest1 request = new MyRequest1(inputStream);
            request.parse();
            MyResponse1 response = new MyResponse1(outputStream);
            response.setRequest(request);
            if (request.getUri().startsWith("/servlet/")) {
                ServletProcessor1 processor = new ServletProcessor1();
                processor.process(request, response);
            } else {
                StaticResourceProcessor s = new StaticResourceProcessor();
                s.process(request,response);
            }
            socket.close();
            shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

        }
    }

    public static void main(String[] args) {
        try {
            new HttpServer1().awit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

