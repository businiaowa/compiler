package front.inter;

import front.lexer.Token;

public class Arith extends Expr {
    public Expr expr1, expr2;

    public Arith(Token type, Token op, Expr expr1, Expr expr2) {
        super(type, op);
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public String gen() {
        return expr1.reduce().toString() + " " + op.toString() + " " + expr2.reduce().toString();
    }

    public Expr reduce() {
        Temp t = new Temp(this.type);
        emit( t.toString() + " = " + gen());
        return t;
    }
}
