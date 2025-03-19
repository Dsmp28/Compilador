package com.springtests.backend.service;

import java.util.List;

import com.springtests.backend.compiler.lexer.Lexer;
import com.springtests.backend.compiler.lexer.SymbolTable;
import com.springtests.backend.compiler.lexer.Token;

public class RespuestaService {
    private Lexer lexer;

    public RespuestaService() {
        lexer = new Lexer();
    }

    public void analizarCodigo(String codigo) {
        lexer.analyze(codigo);
    }

    public List<Token> obtenerTokens() {
        return lexer.getTokens();
    }

    public List<String> obtenerErrores() {
        return lexer.getErrors();
    }

    public SymbolTable obtenerTablaSimbolos() {
        return lexer.getSymbolTable();
    }
}
