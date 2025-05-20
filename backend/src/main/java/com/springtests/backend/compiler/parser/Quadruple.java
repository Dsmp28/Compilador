package com.springtests.backend.compiler.parser;

public class Quadruple {
    public final String op;
    public final String arg1;
    public final String arg2;
    public final String result;

    public Quadruple(String op, String arg1, String arg2, String result) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }
    public String getOp() {
        return op;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s, %s)", op, arg1, arg2, result);
    }
}
