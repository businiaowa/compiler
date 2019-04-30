package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class NOT extends Logical {

    public NOT(Expr expr) {
        super(expr, expr, new Token(Tag.NOT.lexeme, Tag.NOT));
    }

    @Override
    public void emitJumps(int t, int f) {
        expr1.emitJumps(f, t);
    }

    public static void main(String[] args) {
        Expr expr = new ID("a", Type.BOOL);

        Expr not = new NOT(expr);
        not.gen();
    }
}
