package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class Constant extends Expr {

    public Constant(Token token, Type type) {
        super(type, token);
    }

    public Constant(int i) {
        super(Type.INT, new Token("" + i, Tag.INT));
    }

    @Override
    public Expr gen() {

        return  this;
    }

    @Override
    public Expr reduce() {
        return  this;
    }

    public String toString() {
        return this.op.lexeme;
    }
}
