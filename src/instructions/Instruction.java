package instructions;

import runtime.Debugger;

import java.util.HashMap;

public abstract class Instruction {

    protected boolean isCompleted = false;

    /* An array of 26 references to blocks where corresponding variables were declared */
    /* name -> block where variable of this name was delared */
    public Block[] blockRefs;

    /* name -> block where procedure of this name has been declared */
    public HashMap<String, Block> procedureBlockRefs;

    public boolean isCompleted() {
        return isCompleted;
    }

    /*
        Passes a copy of blockRefs
     */
    protected void setBlockRefs(Block[] blockRefs) {
        this.blockRefs = blockRefs.clone();
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
















