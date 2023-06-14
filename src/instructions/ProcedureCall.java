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

        Instruction procInstr = blockRef.procBlockRefs.get(name).getProcedureInstruction(name); //TODO brzydko
        char[] procArgNames = blockRef.procBlockRefs.get(name).getProcedureArgNames(name);
        assert argExpr.length == procArgNames.length;
        //TODO wypadałoby jakiś wyjątek rzucić?

        int argNum = argExpr.length;
        Declaration[] argDeclarations = new Declaration[argNum];
        for (int i = 0; i < argNum; i++) {
            argDeclarations[i] = new Declaration(procArgNames[i], argExpr[i]);
        }
        Block retBlock = new Block(argDeclarations, new Instruction[]{ procInstr.clone() });
        retBlock.copyVarBlockRefs(blockRef.varBlockRefs); //TODO no brzydko chyba
        retBlock.copyProcBlockRefs(blockRef.procBlockRefs); //TODO j.w.
        return retBlock;
    }

    public ProcedureCall clone() {
        return new ProcedureCall(name, argExpr);
    }

}
