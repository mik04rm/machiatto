package expressions;

public class Division extends ExprTwoArg {
    public Division(Expr left, Expr right) {
        super(left, right);
    }

    protected int operation(int left, int right) {
        return left / right;
    }
}