package instructions;

import expressions.Expr;

public class ProcedureCall extends Instruction {


    private final String name;
    private final Expr[] argExpr;

    public ProcedureCall(String name, Expr[] argExpr) {
        this.name = name;
        this.argExpr = argExpr;
    }

    public Instruction exec(Block blockRef) {
        isCompleted = true;

        Instruction procInstr = blockRef.getProcedureInstruction(name);
        char[] procArgNames = blockRef.getProcedureArgNames(name);
        assert argExpr.length == procArgNames.length;
        //TODO wypadałoby jakiś wyjątek rzucić?

        int argNum = argExpr.length;

        Declaration[] argDeclarations = new Declaration[argNum];
        for (int i = 0; i < argNum; i++) {
            argDeclarations[i] = new Declaration(procArgNames[i], argExpr[i]);
        }

        return new Block(argDeclarations, new Instruction[]{ procInstr }) ;
    }

    public ProcedureCall clone() {
        return new ProcedureCall(name, argExpr);
    }

}
