package front.inter;

public class Seq extends Stmt {

    public Stmt stmt1, stmt2;

    public Seq(Stmt stmt1, Stmt stmt2) {
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    @Override
    public void gen(int b, int a) {

        if(stmt1 == null) {
            stmt2.gen(b, a);
        } else if(stmt2 == null) {
            stmt1.gen(b, a);
        } else {
            int lable = newLable();
            stmt1.gen(b, lable);
            emitLable(lable);
            stmt2.gen(lable , a);
        }

    }
}
