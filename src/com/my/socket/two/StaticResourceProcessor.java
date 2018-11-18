package com.my.socket.two;

public class StaticResourceProcessor {
    public void process(MyRequest1 request1,MyResponse1 response1){
        try {
            response1.sendStaticSource();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
