// Generated from C:/Users/smora/IdeaProjects/Compilador/backend/src/main/java/com/springtests/backend/compiler/parser/ANTLR/g.g4 by ANTLR 4.13.2
package com.springtests.backend.compiler.parser.ANTLR;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link gParser}.
 */
public interface gListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link gParser#prule}.
	 * @param ctx the parse tree
	 */
	void enterPrule(gParser.PruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#prule}.
	 * @param ctx the parse tree
	 */
	void exitPrule(gParser.PruleContext ctx);
}