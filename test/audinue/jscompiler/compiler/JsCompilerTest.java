/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audinue.jscompiler.compiler;

import io.github.audinue.jscompiler.compiler.JsCompilerOptions;
import io.github.audinue.jscompiler.compiler.JsCompiler;
import io.github.audinue.jscompiler.compiler.JsCompilerResult;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Audi Nugraha
 */
public class JsCompilerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        JsCompiler compiler = new JsCompiler();
        JsCompilerOptions options = new JsCompilerOptions();
        options.input = "var foo='foo';alert(foo);if(window){alert(foo);}alert(foo);";
        options.assumeFunctionWrapper = true;
        options.formatting = "PRETTY_PRINT";
        JsCompilerResult result = compiler.compile(options);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(result));
    }

}
