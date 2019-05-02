package front.inter;

public abstract class Stmt extends Node {

    public Stmt() {}

    public Stmt ENCLOSING = null;

    public abstract void gen(int b, int a);


}
