package com.springtests.backend.compiler.lexer;

import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class RegexPatterns {

    private static final Map<TokenType, Pattern> patterns = new HashMap<>();

    static {
        patterns.put(TokenType.KEYWORD, Pattern.compile("^(if|else|for|while|return)"));
        patterns.put(TokenType.IDENTIFIER, Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*"));
        patterns.put(TokenType.NUMBER, Pattern.compile("^\\d+(\\.\\d+)?"));
        patterns.put(TokenType.STRING, Pattern.compile("^\".*?\""));
        patterns.put(TokenType.OPERATOR, Pattern.compile("^(\\+|-|\\*|/|=|==|!=|<=|>=|<|>)"));
        patterns.put(TokenType.SEPARATOR, Pattern.compile("^(\\(|\\)|\\{|\\}|,|;)"));
        patterns.put(TokenType.COMMENT, Pattern.compile("^(//.*|/\\*.*?\\*/)", Pattern.DOTALL));
        patterns.put(TokenType.DATA_TYPE, Pattern.compile("^(int|float|char|void)"));
    }

    public static Pattern getPattern(TokenType type) {
        return patterns.get(type);
    }
}