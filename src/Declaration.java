public class Declaration extends Instruction {
    private final char name;
    private final Expr expr;

    public Declaration(char name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }
    Instruction exec(Block blockRef) {
        isCompleted = true;
        assert blockRef != null;
        blockRef.blockRefs[name - 'a'] = blockRef;
        blockRef.setVarValue(name, expr.value(blockRef));
        return null;
    }

    public Declaration clone() {
        return new Declaration(name, expr);
    }
}