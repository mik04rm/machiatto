package instructions;

import expressions.Expr;

public class VarDeclaration extends Instruction {
    private final char name;
    private final Expr expr;

    public VarDeclaration(char name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }
    public Instruction exec(Block blockRef) {
        isCompleted = true;
        assert blockRef != null;
        blockRef.varBlockRefs[name - 'a'] = blockRef;
        blockRef.setVarValue(name, expr.value(blockRef));
        return null;
    }

    public VarDeclaration clone() {
        return new VarDeclaration(name, expr);
    }
}