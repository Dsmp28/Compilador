package com.springtests.backend.dto;

import java.util.List;
import java.util.Map;

import com.springtests.backend.compiler.lexer.SymbolTable;
import com.springtests.backend.compiler.lexer.Token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.tree.ParseTree;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeResponseDTO {
    private List<String> errors;
    private Map<String, Double> memory;
    private ParseTreeNodeDTO tree;
}
