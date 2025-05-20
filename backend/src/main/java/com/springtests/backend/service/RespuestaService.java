package com.springtests.backend.service;

import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gParser;
import com.springtests.backend.compiler.parser.Parser;
import com.springtests.backend.compiler.parser.Quadruple;
import com.springtests.backend.dto.CodeResponseDTO;
import com.springtests.backend.dto.ParseTreeNodeDTO;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;

import java.util.ArrayList;
import java.util.List;

public class RespuestaService {
    private Parser parser;

    public RespuestaService() {
        parser = new Parser();
    }

    public CodeResponseDTO analizarCodigo(String codigo) {
        Parser.AnalysisResult result = parser.analyze(codigo);
        CodeResponseDTO codeResponseDTO = new CodeResponseDTO();

        List<String> errors = result.errors();
        codeResponseDTO.setErrors(errors);
        codeResponseDTO.setMemory(result.memory());
        codeResponseDTO.setTree(toParseTreeDto(result.tree(), result.parser()));
        codeResponseDTO.setIntermediateCode(result.intermediateCode());

        if (errors.isEmpty()) {
            List<String> formatted = new ArrayList<>();
            List<String> python = new ArrayList<>();

            for (Quadruple q : result.intermediateCode()) {
                formatted.add(String.format("(%s, %s, %s, %s)",
                        safe(q.getOp()), safe(q.getArg1()), safe(q.getArg2()), safe(q.getResult())));

                if (q.getArg2() == null || q.getArg2().isBlank()) {
                    python.add(q.getResult() + " = " + q.getArg1());
                } else {
                    String op = q.getOp().equals("^") ? "**" : q.getOp();
                    python.add(q.getResult() + " = " + q.getArg1() + " " + op + " " + q.getArg2());
                }
            }

            codeResponseDTO.setFormattedQuadruples(formatted);
            codeResponseDTO.setPythonCode(python);
        } else {

            codeResponseDTO.setFormattedQuadruples(List.of());
            codeResponseDTO.setPythonCode(List.of());
        }
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
    private String safe(String s) {
        return (s == null || s.isBlank()) ? "" : s;
    }
}
