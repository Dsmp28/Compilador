package com.springtests.backend.compiler.parser;

import com.springtests.backend.compiler.lexer.Lexer;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String code = "Hello a";
        Parser parser = new Parser();
        parser.analyze(code);
    }
}
