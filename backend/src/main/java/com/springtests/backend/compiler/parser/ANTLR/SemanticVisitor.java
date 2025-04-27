package com.springtests.backend.compiler.parser.ANTLR;

import com.springtests.backend.compiler.lexer.SymbolTable;
import com.springtests.backend.compiler.lexer.TokenType;
import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gBaseVisitor;
import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gParser;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class SemanticVisitor extends gBaseVisitor<Integer> {
    private final SymbolTable table;
    private final Map<String,Integer> memory = new HashMap<>();
    private final List<String> semanticErrors = new ArrayList<>();
    private Integer lastValue = null;

    public SemanticVisitor(SymbolTable table) {
        this.table = table;
    }

    @Override
    public Integer visitProg(gParser.ProgContext ctx) {
        Integer result = null;
        for (gParser.StatContext stat : ctx.stat()) {
            result = visit(stat);
        }
        lastValue = result;
        return result;
    }

    @Override
    public Integer visitAssignStat(gParser.AssignStatContext ctx) {
        String id  = ctx.IDENT().getText();
        Integer val = visit(ctx.expr());
        table.addSymbol(id, TokenType.IDENTIFIER,
                        ctx.start.getLine(),
                        ctx.start.getCharPositionInLine());
        memory.put(id, val);
        return val;
    }

    @Override
    public Integer visitNumberAtom(gParser.NumberAtomContext ctx) {
        return Integer.parseInt(ctx.NUMBER().getText());
    }

    @Override
    public Integer visitIdentAtom(gParser.IdentAtomContext ctx) {
        String id = ctx.IDENT().getText();
        if (!memory.containsKey(id)) {
            semanticErrors.add(String.format(
                "Línea %d:%d – Variable no declarada: %s",
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                id));
            return 0;  // valor por defecto para seguir analizando
        }
        return memory.get(id);
    }

    @Override
    public Integer visitBracketExpr(gParser.BracketExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitAdd(gParser.AddContext ctx) {
        return visit(ctx.expr(0)) + visit(ctx.expr(1));
    }

    @Override
    public Integer visitSub(gParser.SubContext ctx) {
        return visit(ctx.expr(0)) - visit(ctx.expr(1));
    }

    @Override
    public Integer visitMul(gParser.MulContext ctx) {
        return visit(ctx.expr(0)) * visit(ctx.expr(1));
    }

    @Override
    public Integer visitDiv(gParser.DivContext ctx) {
        return visit(ctx.expr(0)) / visit(ctx.expr(1));
    }

    @Override
    public Integer visitPower(gParser.PowerContext ctx) {
        int base = visit(ctx.expr(0));
        int exp  = visit(ctx.expr(1));
        return (int) Math.pow(base, exp);
    }

    /**
     * Devuelve la lista de errores semánticos acumulados.
     */
    public List<String> getSemanticErrors() {
        return semanticErrors;
    }

    /**
     * Devuelve el valor resultante de la última expresión visitada.
     */
    public Integer getLastValue() {
        return lastValue;
    }
}
