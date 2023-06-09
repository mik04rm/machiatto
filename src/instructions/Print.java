package instructions;

import expressions.Expr;

public class Print extends Instruction {
    private final Expr expr;

    public Print(Expr expr) {
        this.expr = expr;
    }

    public Instruction exec(Block blockRef) {
        isCompleted = true;
        System.out.println(expr.value(blockRef));
        return null;
    }

    public Print clone() {
        return new Print(expr);
    }
}