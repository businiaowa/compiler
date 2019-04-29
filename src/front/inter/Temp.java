package front.inter;

import front.lexer.Tag;
import front.lexer.Token;

public class Temp extends Expr{

    public static int index;

    public int id;

    public Temp(Token type) {
        super(type, null);
        this.id = ++index;
    }

    public String toString() {
        return Tag.TEMP.lexeme + id;
    }
}
