package expressions;

import instructions.Block;

public class Variable extends Expr {
    private final char name;

    public Variable(char name) {
        this.name = name;
    }

    static public Variable named(char name) {
        return new Variable(name);
    }

    public int value(Block blockRef) {
        assert blockRef != null;
        return blockRef.getVarValue(name);
    }
}