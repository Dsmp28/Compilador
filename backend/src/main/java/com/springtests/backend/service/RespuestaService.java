package com.springtests.backend.service;

import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gParser;
import com.springtests.backend.compiler.parser.Parser;
import com.springtests.backend.dto.CodeResponseDTO;
import com.springtests.backend.dto.ParseTreeNodeDTO;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;

public class RespuestaService {
    private Parser parser;

    public RespuestaService() {
        parser = new Parser();
    }

    public CodeResponseDTO analizarCodigo(String codigo) {
        Parser.AnalysisResult result = parser.analyze(codigo);
        CodeResponseDTO codeResponseDTO = new CodeResponseDTO();

        codeResponseDTO.setErrors(result.errors());
        codeResponseDTO.setMemory(result.memory());
        codeResponseDTO.setTree(toParseTreeDto(result.tree(), result.parser()));

        return codeResponseDTO;
    }

    private ParseTreeNodeDTO toParseTreeDto(ParseTree tree, gParser parser) {
        String nodeText = Trees.getNodeText(tree, parser);
        ParseTreeNodeDTO node = new ParseTreeNodeDTO(nodeText);

        for (int i = 0; i < tree.getChildCount(); i++) {
            node.getChildren().add(toParseTreeDto(tree.getChild(i), parser));
        }

        return node;
    }
}
