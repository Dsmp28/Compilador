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
        int pos = 0;
        int line = 1;
        int col = 1;
        int length = sourceCode.length();

        //Todo a minúscuulas
        sourceCode = sourceCode.toLowerCase(Locale.ROOT);

        while (pos < length) {
            char current = sourceCode.charAt(pos);

            // Saltar espacios en blanco y actualizar contadores
            if (Character.isWhitespace(current)) {
                if (current == '\n') {
                    line++;
                    col = 1;
                } else {
                    col++;
                }
                pos++;
                continue;
            }

            // Detectar comentario de una línea (//)
            if (current == '/' && pos + 1 < length && sourceCode.charAt(pos + 1) == '/') {
                int commentStartCol = col;
                int commentStartLine = line;
                StringBuilder commentContent = new StringBuilder();

                while (pos < length && sourceCode.charAt(pos) != '\n') {
                    commentContent.append(sourceCode.charAt(pos));
                    pos++;
                    col++;
                }
                tokens.add(new Token(TokenType.COMMENT_SINGLE, commentContent.toString(), commentStartLine, commentStartCol));
                continue;
            }

            // Detectar comentario multilínea (/* ... */)
            if (current == '/' && pos + 1 < length && sourceCode.charAt(pos + 1) == '*') {
                int commentStartLine = line;
                int commentStartCol = col;
                StringBuilder commentContent = new StringBuilder();
                commentContent.append("/*");
                pos += 2;
                col += 2;
                boolean closed = false;

                // Recorrer el contenido del comentario
                while (pos < length) {
                    // Si se detecta el cierre del comentario
                    if (sourceCode.charAt(pos) == '*' && pos + 1 < length && sourceCode.charAt(pos + 1) == '/') {
                        commentContent.append("*/");
                        pos += 2;
                        col += 2;
                        closed = true;
                        break;
                    } else {
                        char c = sourceCode.charAt(pos);
                        commentContent.append(c);
                        pos++;
                        if (c == '\n') {
                            line++;
                            col = 1;
                        } else {
                            col++;
                        }
                    }
                }
                if (!closed) {
                    errors.add("Error: Comentario multilínea iniciado en la línea " + commentStartLine + " no se cerró.");
                } else {
                    tokens.add(new Token(TokenType.COMMENT_MULTI, commentContent.toString(), commentStartLine, commentStartCol));
                }
                continue;
            }

            // Intentar emparejar otros tokens utilizando las expresiones regulares definidas
            boolean matched = false;
            for (TokenType type : TokenType.getPriorityOrder()) {
                Pattern pattern = RegexPatterns.getPattern(type);
                Matcher matcher = pattern.matcher(sourceCode.substring(pos));
                if (matcher.find() && matcher.start() == 0) {
                    String lexeme = matcher.group(); //Pendiente
                    tokens.add(new Token(type, lexeme, line, col));
                    if (type == TokenType.IDENTIFIER) { // Si tpo es igual a un ID se coloca en la tabla de símbolos
                        symbolTable.addSymbol(lexeme, type);
                    }
                    // Actualizar pos, línea y columna según el contenido del lexema
                    for (int i = 0; i < lexeme.length(); i++) {
                        char ch = lexeme.charAt(i);
                        if (ch == '\n') {
                            line++;
                            col = 1;
                        } else {
                            col++;
                        }
                    }
                    pos += lexeme.length();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                errors.add("Error léxico en línea " + line + " columna " + col + ": carácter inválido '" + current + "'");
                pos++;
                col++;
            }
        }
    }

    public void generateOutput() {
        LexerOutput.generate(tokens, symbolTable, errors);
    }
}
