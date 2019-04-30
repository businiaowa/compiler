package front.symbols;

public class Array {

    public Type type;

    public int size;

    public int width;

    public Array(Type type, int size) {
        this.type = type;
        this.size = size;
        this.width = size * type.width;
    }

    public String toString() {
        return type.token.lexeme + "[" + size + "]";
    }
}
