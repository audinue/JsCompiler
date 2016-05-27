/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.audinue.jscompiler.compiler;

import java.util.ArrayList;

/**
 *
 * @author Audi Nugraha
 */
public class JsCompilerResult {

    public ArrayList<String> warnings = new ArrayList<>();
    public ArrayList<String> errors = new ArrayList<>();
    public String source;
}
