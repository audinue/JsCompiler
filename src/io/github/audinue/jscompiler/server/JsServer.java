/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.audinue.jscompiler.server;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

/**
 *
 * @author Audi Nugraha
 */
public class JsServer {

    private String host = "127.0.0.1";
    private int port = 8080;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(host, port), 0);
        server.createContext("/", new JsServerHandler());
        server.start();
    }
}
