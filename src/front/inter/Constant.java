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

    @Override
    public void emitJumps(int t, int f) {
        if(this.type == Type.BOOL) {
            if(this.op.tag == Tag.TRUE) {
                if(t != 0)
                    emitGotoLable(t);
            } else {
                if(f != 0)
                    emitGotoLable(f);
            }

        }
    }

    public String toString() {
        return this.op.lexeme;
    }


}
