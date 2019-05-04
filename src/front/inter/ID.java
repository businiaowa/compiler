package front.inter;

import front.symbols.Type;

public class ID extends Expr {

    public String lexeme;

    public ID(String lexme, Type type) {
        super(type, null);
        this.lexeme = lexme;
    }

    public String toString() {
        return  this.lexeme;
    }

    public Expr gen() {
        return this;
    }

    public Expr reduce() {
        return this;
    }
}
