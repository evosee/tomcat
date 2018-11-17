package com.my.socket;

import java.io.*;
import java.net.Socket;
import java.nio.file.OpenOption;

public class TSocket {
    public static void connection() throws Exception {
        Socket socket = new Socket("localhost",8080);
        OutputStream outputStream = socket.getOutputStream();
        boolean flush = true;
        PrintWriter out = new PrintWriter(outputStream,flush);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("GET /index.html HTTP/1.1");
        out.println("Host: localhost:8080");
        out.println("Connection: Close");
        out.println();
        boolean loop = true;
        StringBuilder builder = new StringBuilder(10000);

        while (loop){
            if(reader.ready()){
                int i =0;
                while (i!=-1){
                    i = reader.read();
                    builder.append((char) i);
                }
            }
            loop = false;
        }
        Thread.currentThread().sleep(50);
        System.out.println(builder.toString());
        socket.close();
    }
}
