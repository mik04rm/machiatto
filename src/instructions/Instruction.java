package instructions;

import runtime.Debugger;

import java.util.HashMap;

public abstract class Instruction {

    protected boolean isCompleted = false;

    /* An array of 26 references to blocks where corresponding variables were declared */
    /* mapping name -> block where variable of this name was delared */
    protected Block[] varBlockRefs;

    /* mapping name -> block where procedure of this name has been declared */
    protected HashMap<String, Block> procBlockRefs;

    public boolean isCompleted() {
        return isCompleted;
    }

    /*
        Passes a copy of blockRefs
     */
    protected void copyVarBlockRefs(Block[] varBlockRefs) {
        this.varBlockRefs = varBlockRefs.clone();
    }

    protected void copyProcBlockRefs(HashMap<String, Block> procBlockRefs) {
        /* cloning using HashMap constructor */
        this.procBlockRefs = new HashMap<>(procBlockRefs);
    }

    public void pushSelf(Debugger dbg) {
        dbg.stackPush(this);
    }

    /*
        If the instruction is a simple instruction (Declaration, Print, or Assign) then
            executes the instruction and returns null.
        Else
            returns next sub-instruction of the instruction

        blockRef is reference to a block where instruction is executed
     */
    public abstract Instruction exec(Block blockRef);

    /*
        Returns a copy of the instruction
     */
    public abstract Instruction clone();


}
















