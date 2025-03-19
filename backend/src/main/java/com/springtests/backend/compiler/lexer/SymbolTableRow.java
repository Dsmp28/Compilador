package com.springtests.backend.compiler.lexer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SymbolTableRow {
    public static int counter = 0; // Contador global para asignar índices
    private int index = 0;  // Índice único
    private String identifier;
    private TokenType tokenType;
    private int line;
    private int column;

    public SymbolTableRow(String identifier, TokenType tokenType, int line, int column) {
        this.index = counter++;
        this.identifier = identifier;
        this.tokenType = tokenType;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("Index: %s | Identifier: %s | TokenType: %s | Line: %d | Column: %d",
                index, identifier, tokenType, line, column);
    }
}
