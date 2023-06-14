package instructions;

import runtime.Debugger;

import java.util.HashMap;
import java.util.Map;

public class Block extends Instruction {
    private final Instruction[] declarSeq;
    private final Instruction[] instrSeq;
    private final int[] varValues;

    private final Map<String, Instruction> procedureInstructions;

    private final Map<String, char[]> procedureArgNames;

    public boolean varExists(char name) {
        return varBlockRefs[name] != null;
    }

    public int getVarValue(char name) {
        return varBlockRefs[name - 'a'].varValues[name - 'a'];
    }

    protected void setVarValue(char name, int value) {
        varBlockRefs[name - 'a'].varValues[name - 'a'] = value;
    }

    protected void setProcedure(String name, Instruction instr, char[] argNames) {
        procedureInstructions.put(name, instr);
        procedureArgNames.put(name, argNames);
    }

    protected Instruction getProcedureInstruction(String name) {
        return procedureBlockRefs.get(name).procedureInstructions.get(name);
    }
    protected char[] getProcedureArgNames(String name) {
        return procedureBlockRefs.get(name).procedureArgNames.get(name);
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
        ret.setVarBlockRefs(varBlockRefs);
        ret.setProcedureBlockRefs(procedureBlockRefs);
        return ret;
    }

    public Block(Instruction[] declarSeq, Instruction[] instrSeq) {
        varBlockRefs = new Block[26];
        varValues = new int[26];
        procedureBlockRefs = new HashMap<>();
        procedureInstructions = new HashMap<>();
        procedureArgNames = new HashMap<>();
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
        Instruction[] declarSeq_clone = new VarDeclaration[declarSeq.length];
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
