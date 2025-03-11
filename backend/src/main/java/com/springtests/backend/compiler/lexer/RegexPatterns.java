package com.springtests.backend.compiler.lexer;

import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class RegexPatterns {

    private static final Map<TokenType, Pattern> patterns = new HashMap<>();

    static {
        // Palabras clave
        patterns.put(TokenType.KEYWORD_CONTROL, Pattern.compile("^(if|else|switch)"));
        patterns.put(TokenType.KEYWORD_ITERATION, Pattern.compile("^(for|while|do)"));
        patterns.put(TokenType.KEYWORD, Pattern.compile("^(EscribirLinea|Longitud|aCadena)"));

        // Identificadores y tipos de datos
        patterns.put(TokenType.IDENTIFIER, Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*"));
        patterns.put(TokenType.DATA_TYPE, Pattern.compile("^(int|double|boolean|char|string)"));

        // Literales
        patterns.put(TokenType.INTEGER, Pattern.compile("^-?\\d+"));
        patterns.put(TokenType.DOUBLE, Pattern.compile("^-?\\d+\\.\\d+"));
        patterns.put(TokenType.STRING, Pattern.compile("^\"[^\"]*\""));  // Corregida para evitar greedy matching

        // Operadores
        patterns.put(TokenType.OPERATOR_ARITHMETIC, Pattern.compile("^(\\+{1,2}|-{1,2}|\\*|/|\\^|#)"));  // ++, --, ^, #
        patterns.put(TokenType.OPERATOR_RELATIONAL, Pattern.compile("^(==|!=|>=|<=|>|<)"));
        patterns.put(TokenType.OPERATOR_LOGICAL, Pattern.compile("^(\\|\\||&&|!)"));

        // Signos especiales
        patterns.put(TokenType.SEPARATOR, Pattern.compile("^(,|;)"));
        patterns.put(TokenType.GROUPING, Pattern.compile("^(\\(|\\)|\\{|\\}|\\[|\\])"));

        // Comentarios
        patterns.put(TokenType.COMMENT_SINGLE, Pattern.compile("^//.*"));  // Comentarios de una sola línea
        patterns.put(TokenType.COMMENT_MULTI, Pattern.compile("^/\\*.*?\\*/", Pattern.DOTALL));  // Comentarios multilínea

        // Error para tokens no reconocidos
        patterns.put(TokenType.ERROR, Pattern.compile("^.+"));  // Cualquier otro carácter no reconocido
    }

    public static Pattern getPattern(TokenType type) {
        return patterns.get(type);
    }
}
