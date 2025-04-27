package com.springtests.backend.compiler.parser;


import com.springtests.backend.compiler.lexer.Lexer;
import com.springtests.backend.compiler.lexer.SymbolTable;
import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gLexer;
import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gParser;
import com.springtests.backend.compiler.parser.ANTLR.SyntaxErrorListener;
import com.springtests.backend.compiler.parser.ANTLR.SemanticVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;


public class Parser {
    private final SymbolTable symbolTable = new SymbolTable();

    public AnalysisResult analyze(String code) {
        // 1) Lex + parse con listener de sintaxis
        CharStream cs = CharStreams.fromString(code);
        gLexer lexer = new gLexer(cs);
        CommonTokenStream ts = new CommonTokenStream(lexer);
        gParser parser = new gParser(ts);

        SyntaxErrorListener syntaxErrors = new SyntaxErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(syntaxErrors);

        ParseTree tree = parser.prog();

        // 2) Siempre corremos el pase semntico
        SemanticVisitor semVisitor = new SemanticVisitor(symbolTable);
        semVisitor.visit(tree);

        // 3) Juntamos errores de sintaxis + semántica
        List<String> errors = new ArrayList<>();
        errors.addAll(syntaxErrors.getErrors());
        errors.addAll(semVisitor.getSemanticErrors());

        // 4) Tomamos el ultimo valor (o null)
        Integer finalValue = semVisitor.getLastValue();

        return new AnalysisResult(finalValue, symbolTable, errors);
    }

    public record AnalysisResult(
      Integer value,
      SymbolTable table,
      List<String> errors
    ) {}
}
