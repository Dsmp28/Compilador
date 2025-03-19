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

        // Convertir todo a minúsculas
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

            // Comentario de una línea
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

            // Comentario multilínea
            if (current == '/' && pos + 1 < length && sourceCode.charAt(pos + 1) == '*') {
                int commentStartLine = line;
                int commentStartCol = col;
                StringBuilder commentContent = new StringBuilder();
                commentContent.append("/*");
                pos += 2;
                col += 2;
                boolean closed = false;

                while (pos < length) {
                    char c = sourceCode.charAt(pos);

                    // Verificar si se cierra el comentario con */
                    if (c == '*' && pos + 1 < length && sourceCode.charAt(pos + 1) == '/') {
                        commentContent.append("*/");
                        pos += 2;
                        col += 2;
                        closed = true;
                        break;
                    } else {
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
                    // No se encontró el cierre, reportar error y tomar todo el resto del archivo como parte del comentario
                    errors.add("Error: Comentario multilínea iniciado en la línea " + commentStartLine
                            + " columna " + commentStartCol + " no se cerró. Se toma todo el resto del archivo como comentario.");
                    // Añadimos el token de comentario con todo el contenido leído (hasta fin de archivo)
                    tokens.add(new Token(TokenType.COMMENT_MULTI, commentContent.toString(),
                                         commentStartLine, commentStartCol));
                    // Salimos del while principal, para que no se analice nada más
                    break;
                } else {
                    // Comentario cerrado correctamente
                    tokens.add(new Token(TokenType.COMMENT_MULTI, commentContent.toString(),
                                         commentStartLine, commentStartCol));
                }
                // Continuar con el siguiente carácter en el bucle while (si se cerró el comentario)
                continue;
            }


            // Intentar emparejar otros tokens
            boolean matched = false;
            for (TokenType type : TokenType.getPriorityOrder()) {
                Pattern pattern = RegexPatterns.getPattern(type);
                Matcher matcher = pattern.matcher(sourceCode.substring(pos));
                if (matcher.find() && matcher.start() == 0) {
                    String lexeme = matcher.group();

                    // Si el token es un IDENTIFIER, almacenamos el índice en la tabla de símbolos
                    if (type == TokenType.IDENTIFIER) {
                        int symbolIndex = symbolTable.addSymbol(lexeme, type, line, col);
                        tokens.add(new Token(type, String.valueOf(symbolIndex), line, col));
                    } else {
                        tokens.add(new Token(type, lexeme, line, col));
                    }

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

    public List<Token> getTokens() {
        return tokens;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public List<String> getErrors() {
        return errors;
    }
}
