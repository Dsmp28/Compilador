package com.springtests.backend.compiler.lexer;

import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        String code = ""
          + "Var01 <- 10;\n"
          + "Resultado <- Var02 + 5;\n"
          + "X <- [1+2];\n";
        Lexer lexer = new Lexer(); 
        lexer.analyze(code);
        lexer.generateOutput();
    }
}
