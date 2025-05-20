package com.springtests.backend.compiler.parser;

import com.springtests.backend.compiler.parser.Quadruple;
import com.springtests.backend.compiler.parser.PythonCodeGenerator;
import java.io.IOException;
import java.util.Map;
import java.util.Locale;

import java.io.IOException;
import java.util.List;

public class AlgebraicEvaluator {
    /**
     * Ejecuta el análisis y formatea la salida:
     * - Si hay errores de sintaxis: muestra la lista y detiene.
     * - Si hay errores semánticos: lista errores y luego evaluación parcial.
     * - Si no hay errores: muestra "Mensaje final:" y los valores.
     */
    public static void evaluate(String code) throws IOException {
        Parser.AnalysisResult result = new Parser().analyze(code);
        List<String> errors = result.errors();
        Map<String, Double> memory = result.memory();

        if (!errors.isEmpty()) {
            System.out.println("Errores detectados:");
            for (int i = 0; i < errors.size(); i++) {
                System.out.printf("%d) %s%n", i+1, errors.get(i));
            }
            System.out.println();
            System.out.println("Evaluación parcial:");
        } else {
            System.out.println("Mensaje final:");
        }

        // Mostrar valores de variables calculados (los que no fallaron)
        for (Map.Entry<String, Double> entry : memory.entrySet()) {
            System.out.printf(Locale.US, "%s = %.6f%n",
                              entry.getKey(), entry.getValue());
        }

        // Si no hubo errores, también podemos mostrar el valor de la última expresión
        if (errors.isEmpty() && result.value() != null) {
            System.out.printf(Locale.US,
                              "Resultado final: %.6f%n",
                              result.value());
        }
        List<Quadruple> intermediateCode = result.intermediateCode();

        List<Quadruple> validCode = intermediateCode.stream()
                .filter(AlgebraicEvaluator::isValidQuadruple)
                .toList();

        if (!validCode.isEmpty()) {
            System.out.println("\nCódigo intermedio (cuádruplas):");
            for (Quadruple q : validCode) {
                System.out.println(q);
            }

            System.out.println("\nCódigo Python generado:");
            String pythonCode = PythonCodeGenerator.generatePythonCode(validCode);
            System.out.println(pythonCode);
        }
    }

    private static boolean isValidQuadruple(Quadruple q) {
        return isSafe(q.arg1) && isSafe(q.result) &&
                (q.op.equals("=") || isSafe(q.arg2));
    }

    private static boolean isSafe(String s) {
        if (s == null || s.isBlank()) return false;

        String[] illegalPatterns = {"[", "]", "<-", "++", "+-", "--"};
        for (String pattern : illegalPatterns) {
            if (s.contains(pattern)) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) throws IOException {
        // Ejemplo 1
        System.out.println("Ejemplo 1:");
        String code1 = ""
          + "Var01 <- 10;\n"
          + "Resultado <- Var01 + 5;\n"
          + "X <- [1<-+2];\n";
        evaluate(code1);

        // Ejemplo 2
        System.out.println("\nEjemplo 2:");
        String code2 = "A <- 30;\n B <- A +++ 50;";
        evaluate(code2);
    }

}
