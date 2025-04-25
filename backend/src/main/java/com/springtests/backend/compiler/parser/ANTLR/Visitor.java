package com.springtests.backend.compiler.parser.ANTLR;

import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.*;

public class Visitor extends gBaseVisitor<Object> {

    @Override public Object visitPrule(gParser.PruleContext ctx) {
        System.out.println("Visiting prule: " + ctx.getText());
        return visitChildren(ctx);
    }
}
