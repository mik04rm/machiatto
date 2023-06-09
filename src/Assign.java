public class Assign extends Instruction {
    private final char varName;
    private final Expr expr;

    public Assign(char varName, Expr expr) {
        this.varName = varName;
        this.expr = expr;
    }

    public Instruction exec(Block blockRef) {
        isCompleted = true;
        blockRef.setVarValue(varName, expr.value(blockRef));
        return null;
    }

    public Assign clone() {
        return new Assign(varName, expr);
    }
}