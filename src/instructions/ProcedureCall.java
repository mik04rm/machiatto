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
        char[] procedureArgNames = blockRef.getProcedureArgNames(name);
        assert argExpr.length == procedureArgNames.length;
        //TODO wypadałoby jakiś wyjątek rzucić?

        int argNum = argExpr.length;
        VarDeclaration[] argDeclarations = new VarDeclaration[argNum];
        for (int i = 0; i < argNum; i++) {
            argDeclarations[i] = new VarDeclaration(procedureArgNames[i], argExpr[i]);
        }
        Block retBlock = new Block(argDeclarations, new Instruction[]{ procInstr.clone() });
        retBlock.setVarBlockRefs(blockRef.varBlockRefs);
        retBlock.setProcedureBlockRefs(blockRef.procedureBlockRefs);
        return retBlock;
    }

    public ProcedureCall clone() {
        return new ProcedureCall(name, argExpr);
    }

}
