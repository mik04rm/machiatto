package instructions;

import java.util.ArrayList;

//TODO Każda konstrukcja składniowa języka Macchiato 1.1 powinna być uzupełniona jednym testem.
//TODO Deklaracje procedur powinny być w ciągu deklaracji? To znaczy jakoś na początku bloku.

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
        blockRef.procBlockRefs.put(name, blockRef); //TODO jakos brzydko
        blockRef.setProcedure(name, instr, argNames);
        return null;
    }

    public ProcedureDeclaration clone() {
        return new ProcedureDeclaration(name, argNames, instr);
    }
}
