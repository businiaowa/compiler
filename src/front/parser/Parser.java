package front.parser;

import front.inter.*;
import front.lexer.Lexer;
import front.lexer.Tag;
import front.lexer.Token;
import front.symbols.Array;
import front.symbols.Env;
import front.symbols.Type;

import java.io.IOException;

public class Parser {

    public Lexer lexer;

    public Token look;

    public Env top;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        look = lexer.scan();
        top = null;
    }

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(new Lexer());
        parser.parse();
    }

    public void parse() throws IOException{
        program();
    }

    public void program() throws IOException{// program -> block
        Stmt s = block();
        int b = s.newLable();
        int a = s.newLable();
        s.emitLable(b);
        s.gen(b, a);
        s.emitLable(a);
    }

    public Stmt block() throws IOException{ // block -> {decls stmts}
        match(Tag.LBRACE);
        Env savedEnv = top;
        top = new Env(savedEnv);
        decls();

        Stmt stmts = stmts();
        match(Tag.RBRACE);
        return stmts;
    }

    public Stmt stmts() throws IOException {
        if(look.tag == Tag.RBRACE)
            return new Null();
        return new Seq(stmt(), stmts());
    }

    public Stmt stmt() throws IOException {
        Expr expr;Stmt stmt1, stmt2, stmt;
        switch (look.tag) {
            case SEMI:
                match(Tag.SEMI);
            case RBRACE :
                return new Null();
            case IF :
                match(Tag.IF);
                match(Tag.LPAREN);
                expr = bool();
                match(Tag.RPAREN);
                stmt1 = stmt();
                if(look.tag != Tag.ELSE) {
                    return  new If(expr, stmt1);
                }
                match(Tag.ELSE);
                stmt2 = stmt();
                return new Else(expr, stmt1, stmt2);
            case WHILE:
                match(Tag.WHILE);
                match(Tag.LPAREN);
                expr = bool();
                match(Tag.RPAREN);
                stmt = stmt();
                return new While(expr, stmt);
            case DO:
                match(Tag.DO);
                stmt = stmt();
                match(Tag.WHILE);
                match(Tag.LPAREN);
                expr = bool();
                match(Tag.RPAREN);
                return new Do(expr, stmt);
            case LBRACE:
                return block();
            default:
                return assign();

        }

    }

    public Stmt assign() throws IOException{
        Token token = look;
        match(Tag.ID);
        ID id = top.get(token.lexeme);
        if(!(id.type instanceof Array)) {
            match(Tag.ASSIGN);
            Expr value = bool();
            match(Tag.SEMI);
            return new Set(id, value);
        } else {
            match(Tag.LARRAY);
            Expr index = bool();
            match(Tag.RARRAY);
            match(Tag.ASSIGN);
            Expr value = bool();
            match(Tag.SEMI);
            Access access = new Access(id, index, value.type);
            return new SetElem(access, value);
        }

    }

    public Expr bool() throws IOException{
        Expr bool = join();
        while(look.tag == Tag.OR) {
            match(Tag.OR);
            bool = new OR(bool, join());

        }
        return bool;

    }

    public Expr join() throws IOException{
        Expr join = equality();
        while(look.tag == Tag.AND) {
            match(Tag.AND);
            join = new AND(join, equality());
        }
        return join;
    }

    public Expr equality() throws IOException{
        Expr equality = rel();
        while(look.tag == Tag.EQ || look.tag == Tag.NOTEQ) {
            Token op = look;
            match(look.tag);
            Expr rel = rel();
            equality = new Rel(equality, rel, op);
        }
        return equality;
    }

    public Expr rel() throws IOException{
        Expr expr = expr();
        while(look.tag == Tag.LT || look.tag == Tag.LE || look.tag == Tag.GT || look.tag == Tag.GE) {
            Token op = look;
            match(look.tag);
            expr = new Rel(expr, expr(), op);
        }
        return expr;
    }

    public Expr expr() throws IOException{
        Expr term = term();
        while(look.tag == Tag.PLUS || look.tag == Tag.MINUS) {
            Token op = look;
            match(look.tag);
            term = new Arith(term.type, op, term, term());
        }
        return term;
    }

    public Expr term() throws IOException{
        Expr unary = unary();
        while(look.tag == Tag.STAR || look.tag == Tag.SLASH) {
            Token op = look;
            match(look.tag);
            unary = new Arith(unary.type, op, unary, unary());
        }
        return unary;
    }

    public Expr unary() throws IOException{
        if(look.tag == Tag.NOT || look.tag == Tag.MINUS) {
            Token op = look;
            match(look.tag);
            switch (op.tag) {
                case NOT: return new NOT(unary());
                case MINUS: return new Unary(unary(), op);
            }
        }
        return factor();
    }

    public Expr factor() throws IOException{
        Expr factor = null;
        Token token;
        switch (look.tag) {
            case LPAREN:
                match(Tag.LPAREN); factor = bool(); match(Tag.RPAREN);break;
            case ID:
                token = look;
                match(Tag.ID);
                if(look.tag != Tag.LARRAY) {
                    return new ID(token.lexeme, top.get(token.lexeme).type);
                } else {
                    ID id = new ID(token.lexeme, top.get(token.lexeme).type);
                    match(Tag.LARRAY);
                    Expr i = bool();
                    match(Tag.RARRAY);
                    factor = new Access(id, i, ((Array)id.type).type);
                }
                break;
            case INT:
            case CHAR:
                token = look;
                match(look.tag);
                factor = new Constant(Integer.valueOf(token.lexeme));
                break;
            case FLOAT:
                token = look;
                match(look.tag);
                factor = new Constant(token, Type.FLOAT);
                break;
            case TRUE:
            case FALSE:
                token = look;
                match(look.tag);
                factor = new Constant(token, Type.BOOL);
                break;

        }
        return factor;
    }

    public void decls() throws IOException {
        Type type = type();
        if(type != null) {
            top.put(new ID(look.lexeme, type));
            match(Tag.ID);
            match(Tag.SEMI);
            decls();
        }

    }

    public Type type() throws IOException{// 只支持一维数组
        Type type = Type.get(look.lexeme);
        if(type == null) return null;
        move();
        if(look.lexeme == "[") {
            match(Tag.LARRAY);
            int size = Integer.valueOf(look.lexeme);
            match(Tag.INT);
            match(Tag.RARRAY);
            return new Array(type, size);
        }

        return type;
    }

    public void move() throws IOException{
        look = lexer.scan();
    }

    public void error(String error) {
        throw new Error("near line : " + lexer.line + " " + error);
    }

    public void match(Tag tag) throws IOException{
        if(look.tag == tag) {
            move();
        } else {
            error("syntax error expected : " + tag);
        }

    }


//    public static void test() {
//        {
//            int a;
//            {
//                int b;
//                a = a + b;
//            }
//        }
//    }
}


