package com.my.socket;

import java.io.*;

public class MyResponse {
    private static final int BUFFER_SIZE = 1024;
    private MyRequest request;
    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendStaticSource() throws Exception {
        byte[] bytes = new byte[BUFFER_SIZE];
        File file = new File(HttpServer.WEB_ROOT,request.getUri());
        if(file!=null&&file.exists()){
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                int ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    outputStream.write(bytes, 0, ch);
                    ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
                }
            }
        }else{
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            outputStream.write(errorMessage.getBytes());
        }

    }
    public MyRequest getRequest() {
        return request;
    }

    public void setRequest(MyRequest request) {
        this.request = request;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

}
