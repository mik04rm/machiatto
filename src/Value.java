public class Value extends Expr {
    private final int val;

    public Value(int val) {
        this.val = val;
    }

    public int value(Block blockRef) {
        return val;
    }
}