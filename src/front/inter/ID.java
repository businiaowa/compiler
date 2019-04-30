package front.inter;

import front.symbols.Type;

public class ID extends Expr {

    public String lexme;

    public ID(String lexme, Type type) {
        super(type, null);
        this.lexme = lexme;
    }

    public String toString() {
        return  this.lexme;
    }

    public String gen() {
        return this.lexme;
    }

    public Expr reduce() {
        return this;
    }
}
