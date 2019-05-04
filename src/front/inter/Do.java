package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class Do extends Stmt {

    public Expr expr;

    public Stmt stmt;

    public Do(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
        if(expr.type != Type.BOOL)
            error("type error");
    }

    @Override
    public void gen(int b, int a) {
        int lable = newLable();
        stmt.gen(b, lable);
        emitLable(lable);
        expr.emitJumps(b, 0);
    }

    public static void main(String[] args) {
        int b = newLable(), a = newLable();
        Constant cons = new Constant(1);
        ID id = new ID("a", Type.INT);
        Expr expr2 = new ID("b", Type.INT);
        Arith arith = new Arith(Type.INT, new Token("+", Tag.PLUS), id, expr2);
        Rel rel = new Rel(id, cons, Token.reserves.get(Tag.LT.lexeme));
        Do doStmt = new Do(rel, new Set(id, arith));
        doStmt.emitLable(b);
        doStmt.gen(b, a);
        doStmt.emitLable(a);
    }
}
