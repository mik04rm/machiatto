public class Modulo extends ExprTwoArg {
    public Modulo(Expr left, Expr right) {
        super(left, right);
    }

    protected int operation(int left, int right) {
        return left % right;
    }
}