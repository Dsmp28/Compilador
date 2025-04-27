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
        // Sustituye <EOF> por $ en el texto del mensaje
        String visualMsg = msg.replace("<EOF>", "$");
        errors.add(String.format("Linea %d:%d – %s",
                                 line, charPosInLine, visualMsg));
    }

    public List<String> getErrors() {
        return errors;
    }
}
