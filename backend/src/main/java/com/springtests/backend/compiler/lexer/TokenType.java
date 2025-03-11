package com.springtests.backend.compiler.lexer;

public enum TokenType {
    // Identificadores y palabras clave
    IDENTIFIER,            // Variables, funciones, etc.
    KEYWORD,               // Palabras reservadas del lenguaje

    // Tipos de datos
    DATA_TYPE,             // int, double, boolean, char, string

    // Literales
    INTEGER,
    DOUBLE,// Números enteros y decimales
    STRING,                // Cadenas de texto

    // Operadores
    OPERATOR_ARITHMETIC,   // +, -, *, /, ^, #, ++, --
    OPERATOR_RELATIONAL,   // ==, !=, >, <, >=, <=
    OPERATOR_LOGICAL,      // &&, ||, !

    // Signos especiales
    SEPARATOR,             // Coma, punto y coma (;)
    GROUPING,              // Paréntesis (), llaves {}, corchetes []

    // Sentencias de control
    KEYWORD_CONTROL,       // if, else, switch

    // Sentencias de iteración
    KEYWORD_ITERATION,     // for, while, do-while

    // Comentarios
    COMMENT_SINGLE,        // Comentario en una sola línea (//)
    COMMENT_MULTI,         // Comentario en múltiples líneas (/* */)

    // Otros
    ERROR                  // Para caracteres no reconocidos
}

