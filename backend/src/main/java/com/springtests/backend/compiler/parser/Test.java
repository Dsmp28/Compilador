package com.springtests.backend.compiler.parser;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String code = ""
          + "Var01 <- 10;\n"
          + "Resultado <- Var02 + 5;\n"    // Var02 no existe
          + "X <- [1+*2];\n";              // error de sintaxis

        Parser.AnalysisResult result = new Parser().analyze(code);

        System.out.println("Valor final = " + result.value());
        System.out.println("\nTabla de Símbolos:");
        result.table().printTable();

        Integer i = 1;
        if (!result.errors().isEmpty()) {
            System.out.println("\nErrores detectados:");
            for (String err : result.errors()) {
                System.out.println( i + "). " + err);
                i++;
            }
        }
    }
}
