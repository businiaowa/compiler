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

    public void error(String s) {
        throw new Error("line " + line + " occur " + s);
    }

    public void emitJumps(String test, int t, int f) {
        if(t != 0 && f != 0) {
            emit("if " + test + " goto L" + t);
            emit("goto L" + f);
            return;
        }

        if(t != 0) {
            emit("if " + test + " goto L" + t);
        } else {
            emit("iffalse " + test + " goto L" + f);
        }
    }

    public void emitJumps(int t, int f) {
        String test = this.toString();
       this.emitJumps(test, t, f);
    }

    public void emit(String s) {
        String content = "\t" + s + "\n";
        IO.stdout(content);
    }

    public void emitLable(int lable) {
        String content = "L" + lable + ":\n";
        IO.stdout(content);
    }

    public void emitGotoLable(int lable) {
        String content = "\tgoto L" + lable + "\n";
        IO.stdout(content);
    }
}
