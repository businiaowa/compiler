package front.inter;

import front.lexer.Token;

public class ID extends Expr {

    public String lexme;

    public ID(String lexme, Token type) {
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
