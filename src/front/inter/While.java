package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class While extends Stmt {

    public Expr expr;

    public Stmt stmt;

    public While(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    @Override
    public void gen(int b, int a) {
        int lable = newLable();
        expr.emitJumps(0, a);
        emitLable(lable);
        stmt.gen(lable, b);
        emitGotoLable(b);
    }

    public static void main(String[] args) {
        int b = newLable(), a = newLable();
        Constant cons = new Constant(1);
        ID id = new ID("a", Type.INT);
        Expr expr2 = new ID("b", Type.INT);
        Arith arith = new Arith(Type.INT, new Token("+", Tag.PLUS), id, expr2);
        Rel rel = new Rel(id, cons, Token.reserves.get(Tag.LT.lexeme));
        While whileStmt = new While(rel, new Set(id, arith));
        whileStmt.emitLable(b);
        whileStmt.gen(b, a);
        whileStmt.emitLable(a);
    }
}
