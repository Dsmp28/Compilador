package com.springtests.backend.compiler.lexer;

import java.util.Arrays;
import java.util.List;

public enum TokenType {
    WHITESPACE,
    DATA_TYPE,
    KEYWORD_CONTROL,
    KEYWORD_ITERATION,
    KEYWORD,
    SEPARATOR,
    GROUPING,
    OPERATOR_ARITHMETIC,
    OPERATOR_ASSIGNMENT,
    OPERATOR_RELATIONAL,
    OPERATOR_LOGICAL,
    ENTERO,
    REAL,
    CADENA,
    IDENTIFIER,
    COMMENT_SINGLE,
    COMMENT_MULTI;
    //ERROR;

    public static List<TokenType> getPriorityOrder() {
        return Arrays.asList(
                WHITESPACE,
                COMMENT_MULTI,
                COMMENT_SINGLE,
                DATA_TYPE,
                KEYWORD_CONTROL,
                KEYWORD_ITERATION,
                KEYWORD,
                SEPARATOR,
                GROUPING,
                OPERATOR_ARITHMETIC,
                OPERATOR_RELATIONAL,
                OPERATOR_ASSIGNMENT,
                OPERATOR_LOGICAL,
                ENTERO,
                REAL,
                CADENA,
                IDENTIFIER
                //ERROR
        );
    }
}

