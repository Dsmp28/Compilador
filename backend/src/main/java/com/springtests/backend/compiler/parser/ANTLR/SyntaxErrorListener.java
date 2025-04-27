package com.springtests.backend.compiler.parser.ANTLR;

import org.antlr.v4.runtime.*;
import java.util.*;

public class SyntaxErrorListener extends BaseErrorListener{
    private final List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?,?> recognizer,
                            Object offendingSymbol,
                            int line, int charPosInLine,
                            String msg,
                            RecognitionException e) {
        errors.add(String.format("Linea %d:%d – %s", line, charPosInLine, msg));
    }

    public List<String> getErrors() {
        return errors;
    }
}
