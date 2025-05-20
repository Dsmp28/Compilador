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
    private final Map<gParser.ExprContext, String> exprPlaces = new HashMap<>();


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
            String place = exprPlaces.get(ctx.expr());

            if (val != null) {
                memory.put(id, val);
                table.addSymbol(id, TokenType.IDENTIFIER, ctx.start.getLine(), ctx.start.getCharPositionInLine());

                if (place != null && !place.isBlank()) {
                    intermediateCode.add(new Quadruple("=", place, "", id));
                }
            }

        } catch (Exception ex) {
            semanticErrors.add(String.format("Línea %d:%d – Error al evaluar '%s': %s",
                    ctx.start.getLine(), ctx.start.getCharPositionInLine(), id, ex.getMessage()));
            val = 0.0;
        }

        return val != null ? val : 0.0;
    }

    @Override
    public Double visitDiv(gParser.DivContext ctx) {
        Double left = safeVisit(ctx.expr(0));
        Double right = safeVisit(ctx.expr(1));

        if (right == 0.0) {
            semanticErrors.add(String.format("Línea %d:%d – División por cero",
                    ctx.start.getLine(), ctx.start.getCharPositionInLine()));
            return 0.0;
        }

        String leftPlace = exprPlaces.get(ctx.expr(0));
        String rightPlace = exprPlaces.get(ctx.expr(1));

        if (leftPlace == null || rightPlace == null) return 0.0;

        String temp = newTemp();
        intermediateCode.add(new Quadruple("/", leftPlace, rightPlace, temp));
        exprPlaces.put(ctx, temp);
        return left / right;
    }

    @Override
    public Double visitNumberAtom(gParser.NumberAtomContext ctx) {

        String literal = ctx.NUMBER().getText();
        exprPlaces.put((gParser.ExprContext) ctx.getParent(), literal);
        return Double.parseDouble(literal);
    }

    @Override
    public Double visitIdentAtom(gParser.IdentAtomContext ctx) {
        String id = ctx.IDENT().getText();

        if (!memory.containsKey(id)) {
            semanticErrors.add(String.format(
                    "Línea %d:%d – Variable no declarada: %s",
                    ctx.start.getLine(), ctx.start.getCharPositionInLine(), id));
            exprPlaces.put((gParser.ExprContext) ctx.getParent(), "0");
            return 0.0;
        }

        exprPlaces.put((gParser.ExprContext) ctx.getParent(), id);
        return memory.get(id);
    }

    @Override
    public Double visitBracketExpr(gParser.BracketExprContext ctx) {

        Double value = visit(ctx.expr());

        exprPlaces.put((gParser.ExprContext) ctx.getParent(), exprPlaces.get(ctx.expr()));

        return value;
    }

    @Override
    public Double visitAdd(gParser.AddContext ctx) {

        Double left = safeVisit(ctx.expr(0));
        Double right = safeVisit(ctx.expr(1));
        String leftPlace = exprPlaces.get(ctx.expr(0));
        String rightPlace = exprPlaces.get(ctx.expr(1));

        if (leftPlace == null || rightPlace == null) return 0.0;

        String temp = newTemp();
        intermediateCode.add(new Quadruple("+", leftPlace, rightPlace, temp));
        exprPlaces.put(ctx, temp);
        return left + right;
    }

    @Override
    public Double visitSub(gParser.SubContext ctx) {

        Double left = safeVisit(ctx.expr(0));
        Double right = safeVisit(ctx.expr(1));
        String leftPlace = exprPlaces.get(ctx.expr(0));
        String rightPlace = exprPlaces.get(ctx.expr(1));

        if (leftPlace == null || rightPlace == null) return 0.0;

        String temp = newTemp();
        intermediateCode.add(new Quadruple("-", leftPlace, rightPlace, temp));
        exprPlaces.put(ctx, temp);
        return left - right;
    }

    @Override
    public Double visitMul(gParser.MulContext ctx) {
        Double left = safeVisit(ctx.expr(0));
        Double right = safeVisit(ctx.expr(1));
        String leftPlace = exprPlaces.get(ctx.expr(0));
        String rightPlace = exprPlaces.get(ctx.expr(1));

        if (leftPlace == null || rightPlace == null) return 0.0;

        String temp = newTemp();
        intermediateCode.add(new Quadruple("*", leftPlace, rightPlace, temp));
        exprPlaces.put(ctx, temp);
        return left * right;
    }

    @Override
    public Double visitPower(gParser.PowerContext ctx) {
        Double base = safeVisit(ctx.expr(0));
        Double exponent = safeVisit(ctx.expr(1));
        String basePlace = exprPlaces.get(ctx.expr(0));
        String exponentPlace = exprPlaces.get(ctx.expr(1));

        if (basePlace == null || exponentPlace == null) return 0.0;

        String temp = newTemp();
        intermediateCode.add(new Quadruple("^", basePlace, exponentPlace, temp));
        exprPlaces.put(ctx, temp);
        return Math.pow(base, exponent);
    }

    private Double safeVisit(gParser.ExprContext expr) {
        try {
            return visit(expr);
        } catch (Exception e) {
            return null;
        }
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
