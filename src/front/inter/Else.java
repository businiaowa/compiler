package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class Else extends Stmt {

    public Expr expr;

    public Stmt stmt1;

    public Stmt stmt2;

    public Else(Expr expr, Stmt stmt1, Stmt stmt2) {
        this.expr = expr;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    @Override
    public void gen(int b, int a) {
        int lable1 = newLable();
        int lable2 = newLable();
        expr.emitJumps(0, lable2);
        emitLable(lable1);
        stmt1.gen(lable1, a);
        emitGotoLable(a);
        emitLable(lable2);
        stmt2.gen(lable2, a);
    }

    public static void main(String[] args) {
        int b = newLable(), a = newLable();
        Constant cons1 = new Constant(1);
        Constant cons2 = new Constant(2);
        Expr expr1 = new ID("a", Type.INT);
        Expr expr2 = new ID("b", Type.INT);
        Arith arith = new Arith(Type.INT, new Token("+", Tag.PLUS), expr1, expr2);
        Rel rel = new Rel(arith, cons1, Token.reserves.get(Tag.GT.lexeme));
        Else elseStmt = new Else(rel, new Set(new ID("d", Type.INT), cons1), new Set(new ID("d", Type.INT), cons2));
        elseStmt.emitLable(b);
        elseStmt.gen(b,a);
        elseStmt.emitLable(a);
    }
}
