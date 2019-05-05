package util;

import java.io.IOException;

public class IO {

    public static void stdout(String s) {
        System.out.print(s);
    }

    public static int stdin() throws IOException{
        int c = System.in.read();
        return c;
    }
}
