package com.springtests.backend.compiler.lexer;

import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        String code = """
            entero contador;
            Contador = 1;
            if (contador == 1) { return = 1; } else { return = 0; }
            """;

        Lexer lexer = new Lexer();
        lexer.analyze(code);
        lexer.generateOutput();

    }
}
