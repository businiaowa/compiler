package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class AND extends Logical {

    public AND(Expr expr1, Expr expr2) {
        super(expr1, expr2, new Token(Tag.AND.lexeme, Tag.AND));
    }

    @Override
    public void emitJumps(int t, int f) {
        int lable = f == 0 ? newLable() : f;
        expr1.emitJumps(0, lable);
        expr2.emitJumps(t, lable);
        if(f == 0)
            emitLable(lable);
    }

    public static void main(String[] args) {
        Expr expr1 = new ID("a", Type.BOOL);
        Expr expr2 = new ID("b", Type.BOOL);

        Expr and = new AND(expr1, expr2);
        and.gen();
    }
}
