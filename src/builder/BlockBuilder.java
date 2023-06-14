package builder;

import expressions.Expr;
import instructions.*;

import java.util.ArrayList;

public class BlockBuilder {

    ArrayList<Declaration> declarSeq;
    ArrayList<Instruction> instrSeq;

    public BlockBuilder() {
        declarSeq = new ArrayList<>();
        instrSeq = new ArrayList<>();
    }

    public BlockBuilder declareVariable(char name, Expr expr) {
        declarSeq.add(new Declaration(name, expr));
        return this;
    }

    public BlockBuilder assign(char name, Expr expr) {
        instrSeq.add(new Assign(name, expr));
        return this;
    }

    public BlockBuilder print(Expr expr) {
        instrSeq.add(new Print(expr));
        return this;
    }

    public BlockBuilder block(Block block) {
        instrSeq.add(block);
        return this;
    }



    public BlockBuilder if_(Expr expr1, Expr expr2, String comp, Instruction instr1) {
        instrSeq.add(new If(expr1, expr2, comp, instr1));
        return this;
    }

    public BlockBuilder ifElse(Expr expr1, Expr expr2, String comp, Instruction instr1, Instruction instr2) {
        instrSeq.add(new IfElse(expr1, expr2, comp, instr1, instr2));
        return this;
    }

    public BlockBuilder declareProcedure(String name, char[] argNames, Instruction instr) {
        instrSeq.add(new ProcedureDeclaration(name, argNames, instr)); //TODO na razie do instrSeq
        return this;
    }

    public BlockBuilder callProcedure(String name, Expr[] argExpr) {
        instrSeq.add(new ProcedureCall(name, argExpr)); //TODO na razie do instrSeq
        return this;
    }
    

    public Block build() {
        /* copying ArrayLists to arrays as Block uses arrays */

        Declaration[] declarSeq_ = new Declaration[declarSeq.size()];
        Instruction[] instrSeq_ = new Instruction[instrSeq.size()];

        for (int i = 0; i < declarSeq.size(); i++) {
            declarSeq_[i] = declarSeq.get(i);
        }

        for (int i = 0; i < instrSeq.size(); i++) {
            instrSeq_[i] = instrSeq.get(i);
        }

        return new Block(declarSeq_, instrSeq_);
    }

}
