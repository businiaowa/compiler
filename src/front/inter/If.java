package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class If extends Stmt {

    public Expr expr;

    public Stmt stmt;

    public If(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    @Override
    public void gen(int b, int a) {
        int lable = newLable();
        expr.emitJumps(0, a);
        emitLable(lable);
        stmt.gen(lable, a);
    }

    public static void main(String[] args) {
        int b = newLable(), a = newLable();
        Constant cons = new Constant(1);
        Expr expr1 = new ID("a", Type.INT);
        Expr expr2 = new ID("b", Type.INT);
        Arith arith = new Arith(Type.INT, new Token("+", Tag.PLUS), expr1, expr2);
        Rel rel = new Rel(arith, cons, Token.reserves.get(Tag.GT.lexeme));
        If ifStmt = new If(rel, new Set(new ID("d", Type.INT), cons));
        ifStmt.emitLable(b);
        ifStmt.gen(b, a);
        ifStmt.emitLable(a);
    }
}
