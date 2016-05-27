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
import com.google.javascript.jscomp.WarningLevel;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Formattable;
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
        if (jsOptions.input != null) {
            inputs.add(SourceFile.fromCode("input", jsOptions.input));
        } else {
            jsOptions.js.stream().forEach((file) -> {
                inputs.add(SourceFile.fromFile(file));
            });
        }
        CompilerOptions options = new CompilerOptions();
        options.setLanguageIn(CompilerOptions.LanguageMode.ECMASCRIPT6);
        options.setLanguageOut(CompilerOptions.LanguageMode.ECMASCRIPT3);
        CompilationLevel level = null;
        if (jsOptions.compilationLevel == null) {
            level = CompilationLevel.SIMPLE_OPTIMIZATIONS;
        } else {
            switch (jsOptions.compilationLevel) {
                case "WHITESPACE_ONLY":
                    level = CompilationLevel.WHITESPACE_ONLY;
                    break;
                case "SIMPLE":
                    level = CompilationLevel.SIMPLE_OPTIMIZATIONS;
                    break;
                case "ADVANCED":
                    level = CompilationLevel.ADVANCED_OPTIMIZATIONS;
                    break;
            }
        }
        level.setOptionsForCompilationLevel(options);
        if (jsOptions.warningLevel == null) {
            WarningLevel.DEFAULT.setOptionsForWarningLevel(options);
        } else {
            switch (jsOptions.warningLevel) {
                case "QUIET":
                    WarningLevel.QUIET.setOptionsForWarningLevel(options);
                    break;
                case "DEFAULT":
                    WarningLevel.DEFAULT.setOptionsForWarningLevel(options);
                    break;
                case "VERBOSE":
                    WarningLevel.VERBOSE.setOptionsForWarningLevel(options);
                    break;
            }
        }
        options.setChecksOnly(jsOptions.checksOnly);
        if (jsOptions.formatting != null && jsOptions.formatting.equals("PRETTY_PRINT")) {
            options.setPrettyPrint(true);
        }
        if (jsOptions.assumeFunctionWrapper) {
            level.setWrappedOutputOptimizations(options);
        }
        compiler.compile(externs, inputs, options);
        for (JSError error : compiler.getErrors()) {
            jsResult.errors.add(error.toString());
        }
        for (JSError warning : compiler.getWarnings()) {
            jsResult.warnings.add(warning.toString());
        }
        if (!jsOptions.checksOnly) {
            jsResult.source = compiler.toSource();
            if (jsOptions.jsOutputFile != null) {
                try (FileWriter writer = new FileWriter(jsOptions.jsOutputFile)) {
                    writer.write(jsResult.source);
                }
            }
        }
        return jsResult;
    }
}
