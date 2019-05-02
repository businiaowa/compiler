package front.inter;

public class Set extends Stmt {

    public ID id;

    public Expr expr;

    public Set(ID id, Expr expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public void gen(int b, int a) {
        emit( id.toString() + " = " + expr.reduce().toString());
    }
}
