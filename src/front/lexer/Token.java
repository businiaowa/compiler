package front.lexer;

import java.util.HashMap;
import java.util.Map;

public class Token {

    public String lexeme;

    public Tag tag;

    public Token(String lexeme, Tag tag) {
        this.lexeme = lexeme;
        this.tag = tag;
    }

    public final static Map<String, Token> reserves = new HashMap<>();

    static {
         for(Tag tag: Tag.values()) {
             if (tag.lexeme != null) {
                 reserves.put(tag.lexeme, new Token(tag.lexeme, tag));
             }
         }
    }

    public String toString() {
        return lexeme;
    }
}
