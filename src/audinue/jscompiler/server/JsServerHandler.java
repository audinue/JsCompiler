/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audinue.jscompiler.server;

import audinue.jscompiler.compiler.JsCompiler;
import audinue.jscompiler.compiler.JsCompilerOptions;
import audinue.jscompiler.compiler.JsCompilerResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author Audi Nugraha
 */
public class JsServerHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        try (OutputStream out = he.getResponseBody()) {
            JsCompilerOptions options = new Gson().fromJson(new InputStreamReader(he.getRequestBody()), JsCompilerOptions.class);
            if (options == null) {
                return;
            }
            JsCompiler compiler = new JsCompiler();
            try {
                JsCompilerResult result = compiler.compile(options);
                String response = new GsonBuilder().setPrettyPrinting().create().toJson(result);
                he.sendResponseHeaders(200, response.length());
                out.write(response.getBytes());
            } catch (Exception e) {
                he.sendResponseHeaders(400, 0);
            }
        }
    }

}
