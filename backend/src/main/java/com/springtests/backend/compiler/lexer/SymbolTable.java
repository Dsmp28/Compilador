package com.springtests.backend.compiler.lexer;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, TokenType> table;

    public SymbolTable() {
        table = new HashMap<>();
    }

    public void addSymbol(String identifier, TokenType type) {
        table.put(identifier, type);
    }

    public boolean exists(String identifier) {
        return table.containsKey(identifier);
    }

    public void printTable() {
        System.out.println("Tabla de Símbolos:");
        table.forEach((key, value) -> System.out.println("Identificador: " + key + ", Tipo: " + value));
    }
}
