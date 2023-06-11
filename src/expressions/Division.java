package expressions;

public class Division extends ExprTwoArg {
    public Division(Expr left, Expr right) {
        super(left, right);
    }

    static public Division of(Expr left, Expr right) {
        return new Division(left, right);
    }

    protected int operation(int left, int right) {
        return left / right;
    }
}