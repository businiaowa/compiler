package front.lexer;

public enum Tag {

    AND("&&"),
    OR("||"),
    NOT("!"),
    NUM(null),
    IF("if"),
    WHILE("while"),
    DO("do"),
    LT("<"),
    LE("<="),
    GT(">"),
    GE(">="),
    EQ("=="),
    NOTEQ("!="),
    REAL(null),
    TRUE("true"),
    FALSE("false"),
    ID(null),
    INT("int"),
    FLOAT("float"),
    BOOL("bool"),
    CHAR("char"),
    LARRAY("["),
    RARRAY("]"),
    TEMP("t"),
    PLUS("+"),
    SUB("-"),
    STAR("*"),
    SLASH("/"),
    AMP("&"),
    BAR("|"),
    ASSIGN("="),
    SEMI(";"),
    MINUS("-"),
    ARRAY("[]"),
    ;

    public String lexeme;

    Tag(String lexeme) {
        this.lexeme = lexeme;
    }

}
