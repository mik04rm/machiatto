public abstract class ExprTwoArg extends Expr {
    private final Expr exprLeft;
    private final Expr exprRight;

    public ExprTwoArg(Expr left, Expr right) {
        this.exprLeft = left;
        this.exprRight = right;
    }

    protected abstract int operation(int left, int right);

    public int value(Block blockRef) {
        //We pass block reference down to children expressions
        return operation(exprLeft.value(blockRef), exprRight.value(blockRef));
    }
}