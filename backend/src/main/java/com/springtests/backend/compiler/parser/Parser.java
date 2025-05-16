package com.springtests.backend.compiler.parser;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springtests.backend.compiler.lexer.SymbolTable;
import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gLexer;
import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gParser;
import com.springtests.backend.compiler.parser.ANTLR.SyntaxErrorListener;
import com.springtests.backend.compiler.parser.ANTLR.SemanticVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;

import java.util.*;


public class Parser {
    private final SymbolTable symbolTable = new SymbolTable();

    public AnalysisResult analyze(String code) {
        // 1) Cada vez creamos una tabla limpia
        SymbolTable symbolTable = new SymbolTable();

        // 2) Lex + Parse con ANTLR
        CharStream cs = CharStreams.fromString(code);
        gLexer lexer = new gLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        gParser parser = new gParser(tokens);

        // 3) Captura errores de sintaxis
        SyntaxErrorListener syntaxErrors = new SyntaxErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(syntaxErrors);

        // 4) Árbol de análisis
        ParseTree tree = parser.prog();

        // 5) Evaluación semántica
        SemanticVisitor semVisitor = new SemanticVisitor(symbolTable);
        for (gParser.StatContext stat : ((gParser.ProgContext)tree).stat()) {
          try {
            semVisitor.visit(stat);
          } catch (Exception ex) {
            semVisitor.addSemanticError(
              String.format("Línea %d:%d – Excepción al evaluar: %s",
                stat.start.getLine(),
                stat.start.getCharPositionInLine(),
                ex.getMessage())
            );
          }
        }

        // 6) Acumular errores
        List<String> errors = new ArrayList<>();
        errors.addAll(syntaxErrors.getErrors());
        errors.addAll(semVisitor.getSemanticErrors());

        // 7) Resultado final y memoria
        Double finalValue = semVisitor.getLastValue();
        Map<String, Double> memory   = semVisitor.getMemory();

        return new AnalysisResult(finalValue, symbolTable, errors, memory, tree, parser);
    }


    public record AnalysisResult(
        Double value,
        SymbolTable table,
        List<String> errors,
        Map<String, Double> memory,
        ParseTree tree,
        gParser parser
    ) {}
}
