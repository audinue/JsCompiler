/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audinue.jscompiler.compiler;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 *
 * @author Audi Nugraha
 */
public class JsCompilerOptions {

    public String input;
    
    public ArrayList<String> js = new ArrayList<>();

    @SerializedName("js_output_file")
    public String jsOutputFile;

    @SerializedName("compilation_level")
    public String compilationLevel;

    @SerializedName("warning_level")
    public String warningLevel;

    @SerializedName("checks_only")
    public boolean checksOnly;

    @SerializedName("formatting")
    public String formatting;

    @SerializedName("assume_function_wrapper")
    public boolean assumeFunctionWrapper;
}
