package expressions;

public class Multiplication extends ExprTwoArg {
    public Multiplication(Expr left, Expr right) {
        super(left, right);
    }

    static public Multiplication of(Expr left, Expr right) {
        return new Multiplication(left, right);
    }

    protected int operation(int left, int right) {
        return left * right;
    }
}