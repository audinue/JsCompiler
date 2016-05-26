/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audinue.jscompiler.server;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

/**
 *
 * @author Audi Nugraha
 */
public class JsServer {

    public void start() {
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress("127.0.0.1", 80), 0);
            server.createContext("/", new JsServerHandler());
            server.start();
        } catch (Exception e) {
        }
    }
}
