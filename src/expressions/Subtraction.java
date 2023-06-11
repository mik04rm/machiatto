package expressions;

public class Subtraction extends ExprTwoArg {
    public Subtraction(Expr left, Expr right) {
        super(left, right);
    }

    static public Subtraction of(Expr left, Expr right) {
        return new Subtraction(left, right);
    }

    protected int operation(int left, int right) {
        return left - right;
    }
}