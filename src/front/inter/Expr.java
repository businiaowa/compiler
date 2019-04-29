package front.inter;

import front.lexer.Token;

public class Expr extends Node{

    public Token type;

    public Token op;

    public Expr(Token type, Token op) {
        this.type = type;
        this.op = op;
    }

    public String gen() {
        return null;
    }

    public Expr reduce() {
        return this;
    }
}
