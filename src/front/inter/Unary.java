package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class Unary extends Expr{

    public Expr expr;

    public Unary(Expr expr, Token op) {
        super(expr.type, op);
        this.expr = expr;

    }

    public String gen() {
        return op.tag.lexeme + expr.reduce();
    }

    public Expr reduce() {
        String gen = gen();
        Temp t = new Temp(expr.type);
        emit( t.gen() + " = " + gen);
        return t;
    }

    public static void main(String[] args) {
        Expr expr1 = new ID("a", Type.INT);
        Expr expr2 = new ID("b", Type.INT);
        Expr expr3 = new ID("c", Type.INT);
        Arith arith1 = new Arith(Type.INT, new Token("+", Tag.PLUS), expr1, expr2);
        Arith arith2 = new Arith(Type.INT, new Token("+", Tag.PLUS), arith1, expr3);
        Unary unary = new Unary(arith2, new Token(Tag.MINUS.lexeme, Tag.MINUS));
        unary.reduce();
    }
}
