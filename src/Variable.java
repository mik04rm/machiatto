public class Variable extends Expr {
    private final char name;

    public Variable(char name) {
        this.name = name;
    }

    public int value(Block blockRef) {
        assert blockRef != null;
        return blockRef.getVarValue(name);
    }
}