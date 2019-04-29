package front.symbols;

import front.lexer.Tag;
import front.lexer.Token;

public class Type {
    public static final Token CHAR = new Token("char", Tag.CHAR),
                              INT = new Token("int", Tag.INT),
                              FLOAT = new Token("float", Tag.FLOAT),
                              BOOL = new Token("bool", Tag.BOOL);



    public static boolean isNumeric(Token type) {
        if(type.tag == Tag.INT || type.tag == Tag.CHAR || type.tag == Tag.FLOAT)
            return true;
        return false;
    }

    public static Token max(Token type1, Token type2) {
        if(type1.tag == Tag.FLOAT || type2.tag == Tag.FLOAT)
            return FLOAT;
        if(type1.tag == Tag.INT || type2.tag == Tag.INT)
            return INT;
        return CHAR;
    }
}
