package front.inter;

import front.lexer.Tag;
import front.symbols.Type;

public class Temp extends Expr{

    public static int index;

    public int id;

    public Temp(Type type) {
        super(type, null);
        this.id = ++index;
    }

    public String gen() {
        return Tag.TEMP.lexeme + id;
    }

    public String toString() {
        return Tag.TEMP.lexeme + id;
    }
}
