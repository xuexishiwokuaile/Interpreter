package com.xjt.compiler;
public class DeclarationException extends Exception {
	public Token currentToken;

	public DeclarationException(){
		super();
	}

	public DeclarationException(Token currentToken) {
		super(error3(currentToken));
		this.currentToken = currentToken;
	}

	public DeclarationException(Token currentToken, int i) {
		super(error4(currentToken));
		this.currentToken = currentToken;
	}

	private static String error3(Token currentToken) {
		return "\"" + currentToken.image + "\"" + " at line " + currentToken.beginLine + ", colum "
				+ currentToken.beginColumn + " has not been declared";
	}

	private static String error4(Token currentToken) {
		return "\"" + currentToken.image + "\"" + " at line " + currentToken.beginLine + ", colum "
				+ currentToken.beginColumn + " has been declared";
	}
}
