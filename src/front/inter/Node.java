package front.inter;


import front.lexer.Lexer;
import util.IO;

public class Node {

    public static int line;

    public static int lable;

    public Node () {
        this.line = Lexer.line;
    }

    public int newLable() {
        return ++lable;
    }

    public String error(String s) {
        throw new Error("line " + line + " occur " + s);
    }

    public String emit(String s) {
        String content = "\t" + s + "\n";
        IO.stdout(content);
        return content;
    }

    public String emitLable(int lable) {
        String content = "L" + lable + "\n";
        IO.stdout(content);
        return content;
    }

    public String emitGotoLable(int lable) {
        String content = "goto L" + lable + "\n";
        IO.stdout(content);
        return content;
    }
}
