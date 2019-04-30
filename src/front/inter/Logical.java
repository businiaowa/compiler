package front.inter;

import front.lexer.Token;
import front.symbols.Type;

public abstract class Logical extends Expr {

    public Expr expr1;

    public Expr expr2;

    Logical(Expr expr1, Expr expr2, Token op) {
        super(Type.BOOL, op);
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.checkType(expr1, expr2);
    }

    public void checkType(Expr expr1, Expr expr2) {
        if(expr1.type != Type.BOOL || expr2.type != Type.BOOL) {
            error("type error");
        }
    }


    @Override
    public Expr gen() {
        int f = newLable(), a = newLable();
        Temp t = new Temp(type);
        this.emitJumps(0, f);
        emit(t.toString() + " = true");
        emitGotoLable(a);
        emitLable(f);
        emit(t.toString() + " = false");
        emitLable(a);
        return t;
    }


    @Override
    public Expr reduce() {
        error("logical reduce should not use");
        return null;
    }

}
