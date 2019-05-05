package front.symbols;

import front.lexer.Tag;
import front.lexer.Token;

import java.util.HashMap;
import java.util.Map;

public class Type {

    public static final Type
    CHAR = new Type(1, new Token("char", Tag.CHAR)),
    INT = new Type(4, new Token("int", Tag.INT)),
    FLOAT = new Type(4, new Token("float", Tag.FLOAT)),
    BOOL = new Type(1, new Token("bool", Tag.BOOL))

    ;

    public int width;

    public final Token token;

    public static final Map<String, Type> types;

    Type(int width, Token token) {
        this.width = width;
        this.token = token;
    }

    static {
        types = new HashMap<>();
        types.put(CHAR.token.lexeme, CHAR);
        types.put(INT.token.lexeme, INT);
        types.put(FLOAT.token.lexeme, FLOAT);
        types.put(BOOL.token.lexeme, BOOL);
    }

    public static Type get(String lexeme) {
        return types.get(lexeme);
    }

}
