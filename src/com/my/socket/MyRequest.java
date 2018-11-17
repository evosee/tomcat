package com.my.socket;

import java.io.IOException;
import java.io.InputStream;

public class MyRequest {
    private InputStream in;
    private String uri;

    public MyRequest(InputStream in) {
        this.in = in;
    }

    public InputStream getIn() {
        return in;
    }

    public void setIn(InputStream in) {
        this.in = in;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void parse(){
        StringBuilder builder = new StringBuilder(2048);
        byte[] bytes = new byte[2048];
        int i;
        try {
             i = in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            i=-1;
        }
        for(int j=0;j<bytes.length;j++){
            builder.append((char)bytes[j]);

        }
        System.out.println(builder.toString());
        uri = parseUri(builder.toString());
    }

    private String parseUri(String s) {
        int index1,index2;
        index1 = s.indexOf(' ');
        if(index1!=-1){
            index2 = s.indexOf(' ',index1+1);
            if(index2>index1){
                return s.substring(index1+1,index2);
            }
        }
        return null;
    }
}
