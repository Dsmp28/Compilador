package com.springtests.backend.compiler.parser;

import java.util.List;

public class PythonCodeGenerator {

    public static String generatePythonCode(List<Quadruple> quadruples) {
        StringBuilder code = new StringBuilder();

        for (Quadruple q : quadruples) {
            String line;

            switch (q.op) {
                case "+", "-", "*", "/", "^" -> {
                    // Traducir potencia a Python (**) si es necesario
                    String operator = q.op.equals("^") ? "**" : q.op;
                    line = String.format("%s = %s %s %s", q.result, q.arg1, operator, q.arg2);
                }
                case "=" -> {
                    // Asignación directa
                    line = String.format("%s = %s", q.result, q.arg1);
                }
                default -> {
                    // Operación desconocida (por seguridad)
                    line = "# Operación desconocida: " + q.toString();
                }
            }

            code.append(line).append("\n");
        }

        return code.toString();
    }
}
