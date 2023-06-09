package instructions;

import expressions.Expr;
import expressions.Value;

public class For extends Instruction {

    private final char iteratorName;
    private final Expr exprIterNum;
    private final Instruction instr;

    public For(char iteratorName, Expr exprIterNum, Instruction instr) {
        this.iteratorName = iteratorName;
        this.exprIterNum = exprIterNum;
        this.instr = instr;
    }

    private int iterNum;

    //curIter is an iteration which will be returned next by exec (a block with declared iterator and copy of the instruction 'instr')
    private int curIter = 0;

    public Instruction exec(Block blockRef) {
        if (curIter == 0) {
            iterNum = exprIterNum.value(blockRef);
        }

        //If for has 0 iterations in total we set isCompleted to true and return empty block
        if (curIter >= iterNum) {
            isCompleted = true;
            return new Block(new Declaration[]{}, new Instruction[]{});
        }

        //Iterator declaration and instruction are wrapped in block
        Block retBlock = new Block(
                new Declaration[]{new Declaration(iteratorName, new Value(curIter))},
                new Instruction[]{instr.clone()}
        );
        retBlock.setBlockRefs(blockRefs);
        curIter++;
        return retBlock;
    }

    public For clone() {
        return new For(iteratorName, exprIterNum, instr);
    }
}