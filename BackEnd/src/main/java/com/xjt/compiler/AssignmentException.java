package com.xjt.compiler;
public class AssignmentException extends Exception {
	public Token currentToken;
	public AssignmentException(Token currentToken) {
		super(error1(currentToken));
		this.currentToken = currentToken;
	}

	public AssignmentException(Token currentToken, int i) {
		super(error2(currentToken));
		this.currentToken = currentToken;
	}

	private static String error1(Token currentToken) {
		return "\"" + currentToken.image + "\"" + " at line " + currentToken.beginLine + ", colum "
				+ currentToken.beginColumn + " can not been assigned";
	}

	private static String error2(Token currentToken) {
		return "\"" + currentToken.image + "\"" + " at line " + currentToken.beginLine + ", colum "
				+ currentToken.beginColumn + " assignment type error";
	}
}
