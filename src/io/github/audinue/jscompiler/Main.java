/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.audinue.jscompiler;

import io.github.audinue.commander.Command;
import io.github.audinue.commander.Commander;
import io.github.audinue.commander.Option;
import io.github.audinue.jscompiler.server.JsServer;

/**
 *
 * @author Audi Nugraha
 */
public class Main {

    private final JsServer server = new JsServer();

    @Option(name = "-h", alias = "--host", description = "Set the server host.")
    void host(String host) {
        server.setHost(host);
    }

    @Option(name = "-p", alias = "--port", description = "Set the server port.")
    void port(String port) {
        server.setPort(Integer.parseInt(port));
    }

    @Command(usage = "java -jar io.github.audinue.jscompiler.jar [options]")
    void start(String... args) throws Exception {
        server.start();
        System.out.printf("Server started on %s:%s\n", server.getHost(), server.getPort());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Commander c = new Commander(new Main());
        try {
            c.execute(args);
        } catch (Exception e) {
            c.printException(e);
        }
    }

}
