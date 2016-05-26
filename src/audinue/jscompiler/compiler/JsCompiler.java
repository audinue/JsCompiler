/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audinue.jscompiler.compiler;

import com.google.javascript.jscomp.CommandLineRunner;
import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.JSError;
import com.google.javascript.jscomp.SourceFile;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Audi Nugraha
 */
public class JsCompiler {

    public JsCompilerResult compile(JsCompilerOptions jsOptions) throws Exception {
        JsCompilerResult jsResult = new JsCompilerResult();
        Compiler.setLoggingLevel(Level.OFF);
        Compiler compiler = new Compiler();
        List<SourceFile> externs = CommandLineRunner.getBuiltinExterns(CompilerOptions.Environment.BROWSER);
        ArrayList<SourceFile> inputs = new ArrayList<>();
        jsOptions.js.stream().forEach((file) -> {
            inputs.add(SourceFile.fromFile(file));
        });
        CompilerOptions options = new CompilerOptions();
        options.setLanguageIn(CompilerOptions.LanguageMode.ECMASCRIPT6);
        options.setLanguageOut(CompilerOptions.LanguageMode.ECMASCRIPT3);
        CompilationLevel.ADVANCED_OPTIMIZATIONS.setOptionsForCompilationLevel(options);
        compiler.compile(externs, inputs, options);
        for (JSError error : compiler.getErrors()) {
            jsResult.errors.add(error.toString());
        }
        for (JSError warning : compiler.getWarnings()) {
            jsResult.warnings.add(warning.toString());
        }
        jsResult.source = compiler.toSource();
        if (jsOptions.js_output_file != null) {
            try (FileWriter writer = new FileWriter(jsOptions.js_output_file)) {
                writer.write(jsResult.source);
            }
        }
        return jsResult;
    }
}
