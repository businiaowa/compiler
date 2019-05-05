package front.symbols;

import front.inter.ID;

import java.util.HashMap;
import java.util.Map;

public class Env {

    public Map<String, ID> table;

    public Env pre;

    public Env(Env pre) {
        this.pre = pre;
        this.table = new HashMap<>();
    }

    public Env() {
        this.pre = null;
        this.table = new HashMap<>();
    }

    public ID get(String lexeme) {
        for(Env env = this; env != null; env = env.pre) {
            ID id = env.table.get(lexeme);
            if(id != null)
                return id;
        }
        return null;
    }

    public void put(ID id) {
        this.table.put(id.lexeme, id);
    }
}
