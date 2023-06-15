package instructions;

public class ProcedureDeclaration extends Instruction {

    private final String name;
    private final char[] argNames;
    private final Instruction instr;

    public ProcedureDeclaration(String name, char[] argNames, Instruction instr) {
        this.name = name;
        this.argNames = argNames;
        this.instr = instr;
    }

    public Instruction exec(Block blockRef) {
        isCompleted = true;
        blockRef.procedureBlockRefs.put(name, blockRef);
        blockRef.setProcedure(name, instr, argNames);
        return null;
    }

    public ProcedureDeclaration clone() {
        return new ProcedureDeclaration(name, argNames, instr);
    }
}
