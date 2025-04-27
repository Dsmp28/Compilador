// Generated from C:/Users/smora/IdeaProjects/Compilador/backend/src/main/java/com/springtests/backend/compiler/parser/ANTLR/g.g4 by ANTLR 4.13.2
package com.springtests.backend.compiler.parser.ANTLR.OutputANTLR;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link gParser}.
 */
public interface gListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link gParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(gParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(gParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStat}
	 * labeled alternative in {@link gParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(gParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStat}
	 * labeled alternative in {@link gParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(gParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Div}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDiv(gParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDiv(gParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Add}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdd(gParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdd(gParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Sub}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSub(gParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Sub}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSub(gParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMul(gParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMul(gParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(gParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(gParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Power}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPower(gParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Power}
	 * labeled alternative in {@link gParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPower(gParser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberAtom}
	 * labeled alternative in {@link gParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNumberAtom(gParser.NumberAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberAtom}
	 * labeled alternative in {@link gParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNumberAtom(gParser.NumberAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentAtom}
	 * labeled alternative in {@link gParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterIdentAtom(gParser.IdentAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentAtom}
	 * labeled alternative in {@link gParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitIdentAtom(gParser.IdentAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BracketExpr}
	 * labeled alternative in {@link gParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterBracketExpr(gParser.BracketExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BracketExpr}
	 * labeled alternative in {@link gParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitBracketExpr(gParser.BracketExprContext ctx);
}