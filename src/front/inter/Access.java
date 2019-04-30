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

    public String gen() {
        return array.gen() + "[" + index.reduce().gen() + "]";
    }

    public Expr reduce() {
        return this;
    }

}
