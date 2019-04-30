package front.inter;

import front.symbols.Type;

public class Access extends Expr {

    public ID array;

    public Expr index;

    public Access(ID array, Expr index, Type type) {
        super(type, null);
        this.array = array;
        this.index = index;
    }

    public Expr gen() {
        return new Access(array, index.reduce(), type);
    }

    public Expr reduce() {
        return this;
    }

    public String toString() {
        return array.toString() + "[" + index.reduce().toString() + "]";
    }

}
