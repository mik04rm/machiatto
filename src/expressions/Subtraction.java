package expressions;

public class Subtraction extends ExprTwoArg {
    public Subtraction(Expr left, Expr right) {
        super(left, right);
    }

    protected int operation(int left, int right) {
        return left - right;
    }
}