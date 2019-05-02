package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class Rel extends Logical {

    public Rel(Expr expr1, Expr expr2, Token op) {
        super(expr1, expr2, op);
    }

    public void checkType(Expr expr1, Expr expr2) {
        if(expr1.type != expr2.type)
            error("type not match");
    }

    @Override
    public Expr gen() {
        expr1 = expr1.reduce();
        expr2 = expr2.reduce();
        return super.gen();
    }

    @Override
    public void emitJumps(int t, int f) {
        expr1 = expr1.reduce();
        expr2 = expr2.reduce();
        super.emitJumps(t, f);
    }

    public String toString() {
        return expr1.toString() + " " + op.toString() + " " + expr2.toString();
    }

    public static void main(String[] args) {
        Expr expr1 = new ID("a", Type.INT);
        Expr expr2 = new ID("b", Type.INT);

        Expr expr3 = new ID("c", Type.INT);
        Expr expr4 = new ID("d", Type.INT);

        Arith arith1 = new Arith(Type.INT, new Token("+", Tag.PLUS), expr1, expr2);
        Arith arith2 = new Arith(Type.INT, new Token("+", Tag.PLUS), expr3, expr4);

        Rel rel = new Rel(arith1, arith2, new Token(Tag.GT.lexeme, Tag.GT));
        rel.gen();
    }
}
