package front.inter;

import front.lexer.Token;
import front.symbols.Type;

public abstract class Expr extends Node{

    public Type type;

    public Token op;

    public Expr(Type type, Token op) {
        this.type = type;
        this.op = op;
    }


    public abstract Expr reduce();
}
