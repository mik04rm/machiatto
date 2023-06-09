package expressions;

import instructions.Block;

public abstract class Expr {

    //We may need block reference to calculate values of variables
    public abstract int value(Block blockRef);
}