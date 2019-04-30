package front.symbols;

import front.lexer.Tag;
import front.lexer.Token;

public enum Type {

    CHAR(1, new Token("char", Tag.CHAR)),
    INT(4, new Token("int", Tag.INT)),
    FLOAT(4, new Token("float", Tag.FLOAT)),
    BOOL(1, new Token("bool", Tag.BOOL))
    ;

    public final int width;

    public final Token token;

    Type(int width, Token token) {
        this.width = width;
        this.token = token;
    }


}
