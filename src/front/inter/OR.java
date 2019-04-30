package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class OR extends Logical {

    public OR(Expr expr1, Expr expr2) {
        super(expr1, expr2, new Token(Tag.OR.lexeme, Tag.OR));
    }

    @Override
    public void emitJumps(int t, int f) {
        int lable = t == 0 ? newLable() : t;
        expr1.emitJumps(lable, 0);
        expr2.emitJumps(lable, f);
        if(t == 0)
            emitLable(lable);
    }

    public static void main(String[] args) {
        Expr expr1 = new ID("a", Type.BOOL);
        Expr expr2 = new ID("b", Type.BOOL);

        Expr or = new OR(expr1, expr2);
        or.gen();
    }
}
