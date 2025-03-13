package com.springtests.backend.compiler.lexer;

import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        String code = """
                // Este es un comentario de una línea
                Entero contador = 0;  // Inicialización del contador
                /* Este es un comentario
                   multilínea */
                if (contador == 0) {
                    contador = 1;
                }
                """;
        Lexer lexer = new Lexer(); /*Buenas
        Tardes */ lexer.analyze(code);
        lexer.generateOutput();
    }
}
