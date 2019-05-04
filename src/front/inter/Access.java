package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class Access extends Expr {

    public ID id;

    public Expr index;

    public Access(ID id, Expr index, Type type) {
        super(type, null);
        this.id = id;
        this.index = index;
        if(index.type != Type.INT)
            error("index type error");
    }

    public Expr gen() {
        return new Access(id, index.reduce(), type);
    }

    public Expr reduce() {
        return new Access(id, index.reduce(), type);
    }

    @Override
    public void emitJumps(int t, int f) {
        Expr access = gen();
        access.emitJumps(access.toString(), t, f);
    }

    public String toString() {
        return id.toString() + "[" + index.toString() + "]";
    }

    public static void main(String[] args) {
        //translate -> i > j || arr[i + j]
        ID expr = new ID("arr", Type.ARRAY);

        Expr expr1 = new ID("i", Type.INT);
        Expr expr2 = new ID("j", Type.INT);

        Access access = new Access(expr, new Arith(Type.INT, Token.reserves.get(Tag.PLUS.lexeme), expr1, expr2), Type.BOOL);

        Expr or = new OR(new Rel(expr1, expr2, Token.reserves.get(Tag.GT.lexeme)), access);
        or.gen();
    }
}
