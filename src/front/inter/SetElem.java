package front.inter;

import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Type;

public class SetElem extends Stmt {

    public ID id;

    public Expr index;

    public Expr value;

    public SetElem(Access access, Expr value) {
        this.id = access.id;
        this.index = access.index;
        this.value = value;
        if(access.type != value.type)
            error("type error");
    }

    @Override
    public void gen(int b, int a) {
        Access access = new Access(id, index, value.type);
        emit(access.reduce().toString() + " = " + value.reduce().toString());
    }

    public static void main(String[] args) {
        ID expr = new ID("arr", Type.ARRAY);

        Expr expr1 = new ID("i", Type.INT);
        Expr expr2 = new ID("j", Type.INT);


        Access access = new Access(expr, expr1, Type.INT);
        int b = newLable(), a = newLable();
        SetElem setElem = new SetElem(access, new Arith(Type.INT, Token.reserves.get(Tag.PLUS.lexeme), expr1, expr2));
        setElem.gen(b, a);
    }
}

