package instructions;

import runtime.Debugger;

public class Block extends Instruction {
    private final Declaration[] declarSeq;
    private final Instruction[] instrSeq;
    private final int[] varValues;

    public int getVarValue(char name) {
        return blockRefs[name - 'a'].varValues[name - 'a'];
    }

    public void setVarValue(char name, int value) {
        blockRefs[name - 'a'].varValues[name - 'a'] = value;
    }

    //index of the block sub-instruction which will be returned next by 'exec'
    private int iter;


    public Instruction exec(Block blockRef) {
        assert iter < declarSeq.length + instrSeq.length;
        Instruction ret;
        if (iter < declarSeq.length) {
            ret = declarSeq[iter];
        } else {
            ret = instrSeq[iter - declarSeq.length];
        }
        iter++;
        if (iter == declarSeq.length + instrSeq.length) {
            isCompleted = true;
        }
        ret.setBlockRefs(blockRefs);

        return ret;
    }

    public Block(Declaration[] declarSeq, Instruction[] instrSeq) {
        blockRefs = new Block[26];
        varValues = new int[26];
        this.declarSeq = declarSeq;
        this.instrSeq = instrSeq;
        iter = 0;
        if (declarSeq.length + instrSeq.length == 0) {
            isCompleted = true;
        }
    }

    public void pushSelf(Debugger dbg) {
        dbg.stackPush(this);
    }

    public Block clone() {
        Declaration[] declarSeq_clone = new Declaration[declarSeq.length];
        for (int i = 0; i < declarSeq.length; i++) {
            declarSeq_clone[i] = declarSeq[i].clone();
        }
        Instruction[] instrSeq_clone = new Instruction[instrSeq.length];
        for (int i = 0; i < instrSeq.length; i++) {
            instrSeq_clone[i] = instrSeq[i].clone();
        }
        return new Block(declarSeq_clone, instrSeq_clone);
    }
}
