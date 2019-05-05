package front.symbols;

public class Array extends Type{

    public Type type;

    public int size;

    public Array(Type type, int size) {
        super(size * type.width, type.token);
        this.type = type;
        this.size = size;
    }

    public String toString() {
        return type.token.lexeme + "[" + size + "]";
    }
}
