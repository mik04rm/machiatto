package expressions;

public class Addition extends ExprTwoArg {
    public Addition(Expr left, Expr right) {
        super(left, right);
    }

    protected int operation(int left, int right) {
        return left + right;
    }
}