package com.springtests.backend.compiler.lexer;

import java.util.*;
import java.util.regex.*;

public class Lexer {
    private List<Token> tokens;
    private SymbolTable symbolTable;
    private List<String> errors;

    public Lexer() {
        tokens = new ArrayList<>();
        symbolTable = new SymbolTable();
        errors = new ArrayList<>();
    }

    public void analyze(String sourceCode) {
        String[] lines = sourceCode.split("\n");
        int lineNumber = 1;

        for (String line : lines) {
            tokenizeLine(line, lineNumber);
            lineNumber++;
        }
    }

    private void tokenizeLine(String line, int lineNumber) {
        int column = 0;
        while (column < line.length()) {
            boolean matched = false;

            for (TokenType type : TokenType.values()) {
                Pattern pattern = RegexPatterns.getPattern(type);
                Matcher matcher = pattern.matcher(line.substring(column));

                if (matcher.find() && matcher.start() == 0) {
                    String lexeme = matcher.group();
                    tokens.add(new Token(type, lexeme, lineNumber, column + 1));

                    if (type == TokenType.IDENTIFIER) {
                        symbolTable.addSymbol(lexeme, type);
                    }

                    column += lexeme.length();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                errors.add("Error léxico en línea " + lineNumber + " columna " + (column + 1) + ": carácter inválido '" + line.charAt(column) + "'");
                column++;
            }
        }
    }

    public void generateOutput() {
        LexerOutput.generate(tokens, symbolTable, errors);
    }
}
