package front.inter;


import front.lexer.Lexer;
import util.IO;

public class Node {

    public static int line;

    public Node () {
        this.line = Lexer.line;
    }



    public String error(String s) {
        throw new Error("line " + line + " occur " + s);
    }

    public String emit(String s) {
        IO.stdout("\t" + s);
        return s;
    }

    public void emitLable(int lable) {
        IO.stdout("L" + lable + "\n");
    }
}
