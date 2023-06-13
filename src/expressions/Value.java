package expressions;

import instructions.Block;

public class Value extends Expr {
    private final int val;

    public Value(int val) {
        this.val = val;
    }

    static public Value of(int val) {
        return new Value(val);
    }

    public int value(Block blockRef) {
        return val;
    }
}