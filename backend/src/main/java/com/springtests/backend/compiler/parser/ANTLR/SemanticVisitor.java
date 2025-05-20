package com.springtests.backend.compiler.parser.ANTLR;

import com.springtests.backend.compiler.lexer.SymbolTable;
import com.springtests.backend.compiler.lexer.TokenType;
import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gBaseVisitor;
import com.springtests.backend.compiler.parser.ANTLR.OutputANTLR.gParser;
import com.springtests.backend.compiler.parser.Quadruple;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class SemanticVisitor extends gBaseVisitor<Double> {
    private final SymbolTable table;
    private final Map<String,Double> memory = new HashMap<>();
    private final List<String> semanticErrors = new ArrayList<>();
    private Double lastValue = null;
    private final List<Quadruple> intermediateCode = new ArrayList<>();
    private int tempCounter = 0;

    private String newTemp() {
        return "t" + (tempCounter++);
    }

    public SemanticVisitor(SymbolTable table) {
        this.table = table;
    }

    @Override
    public Double visitProg(gParser.ProgContext ctx) {
        Double result = null;
        for (gParser.StatContext stat : ctx.stat()) {
            result = visit(stat);
        }
        lastValue = result;
        return result;
    }

    @Override
    public Double visitAssignStat(gParser.AssignStatContext ctx) {
        String id = ctx.IDENT().getText();
        Double val;
        try {
            val = visit(ctx.expr());
            memory.put(id, val);
            table.addSymbol(id, TokenType.IDENTIFIER, ctx.start.getLine(), ctx.start.getCharPositionInLine());

            intermediateCode.add(new Quadruple("=", ctx.expr().getText(), "", id));
        } catch (Exception ex) {
            semanticErrors.add(String.format("Línea %d:%d – Error al evaluar '%s': %s",
                    ctx.start.getLine(), ctx.start.getCharPositionInLine(), id, ex.getMessage()));
            val = 0.0;
        }
        return val;
    }

    @Override
    public Double visitDiv(gParser.DivContext ctx) {
        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        if (right == 0.0) {
            semanticErrors.add(String.format("Línea %d:%d – División por cero", ctx.start.getLine(), ctx.start.getCharPositionInLine()));
            return 0.0;
        }
        String temp = newTemp();
        intermediateCode.add(new Quadruple("/", ctx.expr(0).getText(), ctx.expr(1).getText(), temp));
        return left / right;
    }

    @Override
    public Double visitNumberAtom(gParser.NumberAtomContext ctx) {
        return Double.parseDouble(ctx.NUMBER().getText());
    }

    @Override
    public Double visitIdentAtom(gParser.IdentAtomContext ctx) {
        String id = ctx.IDENT().getText();
        if (!memory.containsKey(id)) {
            semanticErrors.add(String.format(
                "Línea %d:%d – Variable no declarada: %s",
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                id));
            return 0.0;  // valor por defecto para seguir analizando
        }
        return memory.get(id);
    }

    @Override
    public Double visitBracketExpr(gParser.BracketExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitAdd(gParser.AddContext ctx) {

        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        String temp = newTemp();
        intermediateCode.add(new Quadruple("+", ctx.expr(0).getText(), ctx.expr(1).getText(), temp));
        return left + right;
    }

    @Override
    public Double visitSub(gParser.SubContext ctx) {

        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        String temp = newTemp();
        intermediateCode.add(new Quadruple("-", ctx.expr(0).getText(), ctx.expr(1).getText(), temp));
        return left - right;
    }

    @Override
    public Double visitMul(gParser.MulContext ctx) {
        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        String temp = newTemp();
        intermediateCode.add(new Quadruple("*", ctx.expr(0).getText(), ctx.expr(1).getText(), temp));
        return left * right;
    }

    @Override
    public Double visitPower(gParser.PowerContext ctx) {
        Double base = visit(ctx.expr(0));
        Double exp = visit(ctx.expr(1));
        String temp = newTemp();
        intermediateCode.add(new Quadruple("^", ctx.expr(0).getText(), ctx.expr(1).getText(), temp));
        return Math.pow(base, exp);
    }

    public void addSemanticError(String error) {
        semanticErrors.add(error);
    }

    public List<String> getSemanticErrors() {
        return semanticErrors;
    }

    public Double getLastValue() {
        return lastValue;
    }

    public Map<String, Double> getMemory() {
        return memory;
    }

    public List<Quadruple> getIntermediateCode() { return intermediateCode; }
}
