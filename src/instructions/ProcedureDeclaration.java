package instructions;

import java.util.ArrayList;

public class ProcedureDeclaration extends Instruction {

    private final String name;
    private final char[] argNames;
    private final Instruction instr;

    public ProcedureDeclaration(String name, char[] argNames, Instruction instr) {
        this.name = name;
        this.argNames = argNames;
        this.instr = instr;
    }

    @Override
    public Instruction exec(Block blockRef) {
        isCompleted = true;
        blockRef.procedureBlockRefs.put(name, blockRef);
        blockRef.setProcedureInstruction(name, instr);
        return null;
    }

    @Override
    public Instruction clone() {
        return null;
    }
}
