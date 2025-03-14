package com.springtests.backend.compiler.lexer;

import java.util.List;

public class LexerOutput {
    public static void generate(List<Token> tokens, SymbolTable symbolTable, List<String> errors) {
        System.out.println("Lista de Tokens:");
        for (Token token : tokens) {
            System.out.println(token);
        }

        System.out.println("\nTabla de Símbolos:");
        symbolTable.printTable();

        if (!errors.isEmpty()) {
            System.out.println("\nLista de Errores:");
            ErrorHandler.printErrors(errors);
        }
    }
}
