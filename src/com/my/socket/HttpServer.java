package com.my.socket;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
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
            MyRequest request = new MyRequest(inputStream);
            request.parse();
            MyResponse response = new MyResponse(outputStream);
            response.setRequest(request);

            response.sendStaticSource();
            socket.close();
            shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

        }
    }

    public static void main(String[] args) {
        try {
            new HttpServer().awit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

