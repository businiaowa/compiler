package front.lexer;

import util.IO;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Lexer {

    public static int line;

    public char peek = ' ';

    private boolean EOF = false;


    public Map<String, Token> tokens = new HashMap<>();

    public Lexer() {

    }

    public void error(String error) {
        throw new Error("line " + line + " " + error);
    }

    public char next() throws IOException{
        int i = IO.stdin();
        if(i == -1) {
            EOF = true;
        }
        return peek = (char)i;
    }


    public void handleSpace() throws IOException{
        for(;;) {
            if(peek == ' ' || peek == '\t') {
                next();
                continue;
            }
            if(peek == '\n') {
                line++;
                next();
                continue;
            }
            break;
        }
    }

    public boolean eof() throws IOException{
        int i = IO.stdin();
        if(i == -1) {
            return true;
        }
        peek = (char)i;
        return false;
    }



    public void resetPeek() {
        peek = ' ';
    }

    public Token scan() throws IOException {


        handleSpace();

        if(peek == '<') {
            if(next() == '=') {
                resetPeek();
                return Token.reserves.get(Tag.LE.lexeme);
            } else {
                return Token.reserves.get(Tag.LT.lexeme);
            }
        }

        if(peek == '>') {
            if(next() == '=') {
                resetPeek();
                return Token.reserves.get(Tag.GE.lexeme);
            } else {
                return Token.reserves.get(Tag.GT.lexeme);
            }
        }

        if(peek == '=') {
            if(next() == '=') {
                resetPeek();
                return Token.reserves.get(Tag.EQ.lexeme);
            } else {
                return Token.reserves.get(Tag.ASSIGN.lexeme);
            }
        }

        if(peek == '&') {
            if(next() == '&') {
                resetPeek();
                return Token.reserves.get(Tag.AND.lexeme);
            } else {
                return Token.reserves.get(Tag.AMP.lexeme);
            }
        }

        if(peek == '|') {
            if(next() == '|') {
                resetPeek();
                return Token.reserves.get(Tag.OR.lexeme);
            } else {
                return Token.reserves.get(Tag.BAR.lexeme);
            }
        }

        if(peek == '!') {
            if(next() == '=') {
                resetPeek();
                return Token.reserves.get(Tag.NOTEQ.lexeme);
            } else {
                return Token.reserves.get(Tag.NOT.lexeme);
            }
        }

        if(peek == '+' || peek == '-' || peek == '*' || peek == '/') {
            String lexeme = peek + "";
            resetPeek();
            return Token.reserves.get(lexeme);
        }

        if(Character.isDigit(peek)) {
            int v = Character.digit(peek, 10);
            while(Character.isDigit(next())) {
                v = v * 10 + Character.digit(peek, 10);

            }
            if(peek == '.') {
                float x = v, d = 10;
                while(Character.isDigit(next())) {
                    x = x + Character.digit(peek, 10) / d;
                    d = d * 10;
                }
                return new Token(x + "", Tag.FLOAT);
            } else {
                return new Token(v + "", Tag.INT);
            }
        }

        if(Character.isLetter(peek)) {
            String s = peek + "";
            while(Character.isLetterOrDigit(next())) {
                s += peek;
            }
            Token token = null;
            if((token = Token.reserves.get(s)) != null)
                return token;

            if((token = tokens.get(s)) != null)
                return token;

            token = new Token(s, Tag.ID);
            tokens.put(s, token);
            return token;
        }

        Token token = Token.reserves.get("" + peek);
        if(token != null) {
            resetPeek();
            return token;
        }
        if(!EOF)
            error("unexpected token.");
        return null;
    }



}
