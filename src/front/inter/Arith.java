package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class Arith extends Expr {
    public Expr expr1, expr2;

    public Arith(Type type, Token op, Expr expr1, Expr expr2) {
        super(type, op);
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public Expr gen() {
        return new Arith(type, op, expr1.reduce(), expr2.reduce());
    }

    public Expr reduce() {
        Expr expr = gen();
        Temp t = new Temp(this.type);
        emit( t.toString() + " = " + expr.toString());
        return t;
    }

    public static void main(String[] args) {
        Expr expr1 = new ID("a", Type.INT);
        Expr expr2 = new ID("b", Type.INT);
        Expr expr3 = new ID("c", Type.INT);
        Arith arith1 = new Arith(Type.INT, new Token("+", Tag.PLUS), expr1, expr2);
        Arith arith2 = new Arith(Type.INT, new Token("+", Tag.PLUS), arith1, expr3);

        arith2.reduce();
    }

    public String toString() {
        return expr1.toString() + " " + op.toString() + " " + expr2.toString();
    }
}
