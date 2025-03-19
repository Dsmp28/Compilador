package com.springtests.backend.dto;

import java.util.List;

import com.springtests.backend.compiler.lexer.SymbolTable;
import com.springtests.backend.compiler.lexer.Token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeResponseDTO {
    private List<Token> tokens;
    private SymbolTable symbolTable;
    private List<String> errors;
}
