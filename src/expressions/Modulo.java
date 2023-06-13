package expressions;

public class Modulo extends ExprTwoArg {
    public Modulo(Expr left, Expr right) {
        super(left, right);
    }

    static public Modulo of(Expr left, Expr right) {
        return new Modulo(left, right);
    }

    protected int operation(int left, int right) {
        return left % right;
    }
}