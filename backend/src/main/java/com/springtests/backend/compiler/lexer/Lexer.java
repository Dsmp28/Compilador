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
        String[] lines = sourceCode.split(";");
        int lineNumber = 1;

        for (String line : lines) {
            //Pasar a minuscúlas
            line = line.toLowerCase();
            line = line.trim();// Opcional: eliminar espacios al inicio y final
            line += "\n";
            tokenizeLine(line, lineNumber);
            lineNumber++;
        }
    }

    private void tokenizeLine(String line, int lineNumber) {
        int column = 0;
        while (column < line.length()) {
            boolean matched = false;

            // Verificar espacios en blanco primero
            Pattern whitespacePattern = RegexPatterns.getPattern(TokenType.WHITESPACE);
            Matcher whitespaceMatcher = whitespacePattern.matcher(line.substring(column));
            if (whitespaceMatcher.find() && whitespaceMatcher.start() == 0) {
                column += whitespaceMatcher.end();
                continue;
            }

            // Verificar otros tokens en orden de prioridad
            for (TokenType type : TokenType.getPriorityOrder()) {
                Pattern pattern = RegexPatterns.getPattern(type);
                Matcher matcher = pattern.matcher(line.substring(column));
                if (matcher.find() && matcher.start() == 0) {
                    String lexeme = matcher.group();
                    if (type != TokenType.WHITESPACE) {
                        tokens.add(new Token(type, lexeme, lineNumber, column + 1));
                        if (type == TokenType.IDENTIFIER) {
                            symbolTable.addSymbol(lexeme, type);
                        }
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