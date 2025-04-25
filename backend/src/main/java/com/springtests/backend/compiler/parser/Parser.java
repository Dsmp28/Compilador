package com.springtests.backend.compiler.parser;

import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gLexer;
import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gParser;
import com.springtests.backend.compiler.parser.ANTLR.Visitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Parser {
    private gLexer lexer;
    private gParser parser;
    private Visitor visitor;
    private ParseTree tree;

    public void analyze(String SourceCode) throws IOException {
        CharStream input =  CharStreams.fromString(SourceCode);
        lexer = new gLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new gParser(tokens);
        tree = parse();

        visitor = new Visitor();
        visit(tree);
    }

    public ParseTree parse() {
        return parser.prule();
    }

    public void visit(ParseTree tree) {
        visitor.visit(tree);
    }
}
