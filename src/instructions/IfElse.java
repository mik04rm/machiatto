package instructions;

import expressions.Expr;

public class IfElse extends Instruction {
    private final Expr expr1;
    private final Expr expr2;
    private final String comp;
    private final Instruction instr1;
    private final Instruction instr2;

    public IfElse(Expr expr1, Expr expr2, String comp, Instruction instr1, Instruction instr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.comp = comp;
        this.instr1 = instr1;
        this.instr2 = instr2;
    }

    public Instruction exec(Block blockRef) {
        isCompleted = true;
        boolean compResult;
        int ev1 = expr1.value(blockRef);
        int ev2 = expr2.value(blockRef);
        switch (comp) {
            case "=" -> compResult = (ev1 == ev2);
            case "<>" -> compResult = (ev1 != ev2);
            case "<" -> compResult = (ev1 < ev2);
            case ">" -> compResult = (ev1 > ev2);
            case "<=" -> compResult = (ev1 <= ev2);
            case ">=" -> compResult = (ev1 >= ev2);
            default -> {
                System.out.println("Nie ma takiego porównania");
                compResult = false;
            }
        }

        //After calculating a boolean value of the condition we choose which if-else branch to return
        if (compResult) {
            instr1.setVarBlockRefs(varBlockRefs);
            instr1.setProcedureBlockRefs(procedureBlockRefs);
            return instr1;
        } else {
            instr2.setVarBlockRefs(varBlockRefs);
            instr2.setProcedureBlockRefs(procedureBlockRefs);
            return instr2;
        }
    }

    public IfElse clone() {
        return new IfElse(expr1, expr2, comp, instr1.clone(), instr2.clone());
    }
}