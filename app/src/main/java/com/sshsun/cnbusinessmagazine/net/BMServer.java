package com.sshsun.cnbusinessmagazine.net;

import com.shsunframework.net.NanoHTTPD;

import java.io.IOException;


/**
 * Created by shsun on 17/1/9.
 */
public class BMServer extends NanoHTTPD {

    private final static int PORT = 8080;

    public BMServer() throws IOException {
        super(PORT);
        start();
        System.out.println("\nRunning! Point your browers to http://localhost:" + PORT + "/ \n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello server</h1>\n";
        msg += "<p>We serve " + session.getUri() + " !</p>";
        return newFixedLengthResponse(msg + "</body></html>\n");
    }
}