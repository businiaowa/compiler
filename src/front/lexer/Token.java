package front.lexer;

public class Token {

    public String lexeme;

    public Tag tag;

    public Token(String lexeme, Tag tag) {
        this.lexeme = lexeme;
        this.tag = tag;
    }

    public String toString() {
        return lexeme;
    }
}
