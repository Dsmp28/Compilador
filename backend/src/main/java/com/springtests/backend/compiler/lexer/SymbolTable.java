package com.springtests.backend.compiler.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    private List<SymbolTableRow> rows;

    public SymbolTable() {
        rows = new ArrayList<>();
    }

    public void addSymbol(String identifier, TokenType tokenType, int line, int column) {
        if (getSymbolRow(identifier) == null) {
            rows.add(new SymbolTableRow(identifier, tokenType, line, column));
        }
    }

    public SymbolTableRow getSymbolRow(String identifier) {
        for (SymbolTableRow row : rows) {
            if (row.getIdentifier().equals(identifier)) {
                return row;
            }
        }
        return null;
    }

    public void printTable() {
        for (SymbolTableRow row : rows) {
            System.out.println(row);
        }
    }

    public List<SymbolTableRow> getRows() {
        return rows;
    }
}
