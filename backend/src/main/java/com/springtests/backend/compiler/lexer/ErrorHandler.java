package com.springtests.backend.compiler.lexer;

import java.util.List;

public class ErrorHandler {
    public static void printErrors(List<String> errors) {
        System.out.println("Errores léxicos encontrados:");
        for (String error : errors) {
            System.out.println(error);
        }
    }
}