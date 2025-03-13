package com.springtests.backend.compiler.lexer;

import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class RegexPatterns {
    private static final Map<TokenType, Pattern> patterns = new HashMap<>();

    static {
        // Espacios en blanco
        patterns.put(TokenType.WHITESPACE, Pattern.compile("^\\s+"));

        // Palabras clave y tipos de datos
        patterns.put(TokenType.DATA_TYPE, Pattern.compile("^(entero|real|booleano|caracter|cadena)"));
        patterns.put(TokenType.KEYWORD_CONTROL, Pattern.compile("^(if|else|switch)"));
        patterns.put(TokenType.KEYWORD_ITERATION, Pattern.compile("^(for|while|do)"));
        patterns.put(TokenType.KEYWORD, Pattern.compile("^(escribirlínea|longitud|acadena)"));

        // Identificadores
        patterns.put(TokenType.IDENTIFIER, Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*"));

        // Literales
        patterns.put(TokenType.ENTERO, Pattern.compile("^-?\\d+"));
        patterns.put(TokenType.REAL, Pattern.compile("^-?\\d+\\.\\d+"));
        patterns.put(TokenType.CADENA, Pattern.compile("^\"[^\"]*\""));

        // Operadores
        patterns.put(TokenType.OPERATOR_ARITHMETIC, Pattern.compile("^(\\+{1,2}|-{1,2}|\\*|/|\\^|#)"));
        patterns.put(TokenType.OPERATOR_ASSIGNMENT, Pattern.compile("^="));
        patterns.put(TokenType.OPERATOR_RELATIONAL, Pattern.compile("^(==|!=|>=|<=|>|<)"));
        patterns.put(TokenType.OPERATOR_LOGICAL, Pattern.compile("^(\\|\\||&&|!)"));

        // Separadores y agrupadores
        patterns.put(TokenType.SEPARATOR, Pattern.compile("^(,|;)"));
        patterns.put(TokenType.GROUPING, Pattern.compile("^(\\(|\\)|\\{|\\}|\\[|\\])"));

        // Comentarios
        patterns.put(TokenType.COMMENT_SINGLE, Pattern.compile("^//.*"));
        patterns.put(TokenType.COMMENT_MULTI, Pattern.compile("^/\\*.*?\\*/", Pattern.DOTALL));

        // Error
        //patterns.put(TokenType.ERROR, Pattern.compile("^."));
    }

    public static Pattern getPattern(TokenType type) {
        return patterns.get(type);
    }
}
