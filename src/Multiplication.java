public class Multiplication extends ExprTwoArg {
    public Multiplication(Expr left, Expr right) {
        super(left, right);
    }

    protected int operation(int left, int right) {
        return left * right;
    }
}