package instructions;

import expressions.Expr;

public class If extends IfElse {
    public If(Expr expr1, Expr expr2, String comp, Instruction instr1) {
        super(expr1, expr2, comp, instr1, new Block(new VarDeclaration[]{}, new Instruction[]{}));
        // ^ empty block
    }
}