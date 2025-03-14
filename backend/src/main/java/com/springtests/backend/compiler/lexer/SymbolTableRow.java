package com.springtests.backend.compiler.lexer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SymbolTableRow {
    private String identifier;
    private TokenType tokenType;
    private int line;
    private int column;

    public SymbolTableRow(String identifier, TokenType tokenType, int line, int column) {
        this.identifier = identifier;
        this.tokenType = tokenType;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("Identifier: %s | TokenType: %s | Line: %d | Column: %d",
                identifier, tokenType, line, column);
    }
}
