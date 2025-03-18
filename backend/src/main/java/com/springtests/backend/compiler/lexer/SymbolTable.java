package com.springtests.backend.compiler.lexer;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    private List<SymbolTableRow> rows;

    public SymbolTable() {
        rows = new ArrayList<>();
    }

    public int addSymbol(String identifier, TokenType tokenType, int line, int column) {
        SymbolTableRow row = getSymbolRow(identifier);
        if (row == null) {
            row = new SymbolTableRow(identifier, tokenType, line, column);
            rows.add(row);
        }
        return row.getIndex(); // Retornar el índice del símbolo
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
        System.out.println("Índice\tIdentificador\tTipo de Token\tLínea\tColumna");
        for (SymbolTableRow row : rows) {
            System.out.println(row);
        }
    }

    public List<SymbolTableRow> getRows() {
        return rows;
    }
}
