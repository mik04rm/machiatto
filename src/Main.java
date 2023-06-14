import expressions.*;
import instructions.*;
import runtime.Debugger;
import builder.BlockBuilder;

public class Main {

    static Block prog1() {
        Declaration[] dseq = new Declaration[3];
        for (int i = 0; i < 3; i++) {
            dseq[i] = new Declaration((char) ('a' + i), new Value(i));
        }
        Instruction[] iseq = new Instruction[8];

        for (int i = 0; i < 4; i++) {
            iseq[i] = new Print(new Variable((char)('a' + i)));
        }
        iseq[4] = new Assign('d', new Variable('a'));
        iseq[5] = new Print(new Variable('d'));

        iseq[6] = new IfElse(new Variable('a'),
                new Variable('d'),
                ">=",
                new Print(new Value(111)),
                new Print(new Value(100)));

        iseq[7] = new IfElse(new Variable('a'),
                new Variable('a'),
                "<=",
                new Print(new Value(111)),
                new Print(new Value(100)));

        Block b = new Block(dseq, iseq);
        For forek = new For('i',
                new Addition(
                        new Variable('d'),
                        new Value(3)),
                new Print(new Variable('i')));

        Declaration[] p_dseq = { new Declaration('d', new Value(10)) };
        Instruction[] p_iseq = { new Print(new Variable('d')), b, forek };

        return new Block(p_dseq, p_iseq);

    }

    static Block prog_pierwsze() {
        Declaration dn = new Declaration('n', new Value(30));

        Assign assk = new Assign('k', new Addition(new Variable('k'), new Value(2)));

        Block emptyBlock1 = new Block(new Declaration[]{}, new Instruction[]{});
        Block emptyBlock2 = new Block(new Declaration[]{}, new Instruction[]{});
        Assign assi = new Assign('i', new Addition(new Variable('i'), new Value(2)));
        Assign assp = new Assign('p', new Value(0));
        IfElse mif = new IfElse(new Modulo(new Variable('k'), new Variable('i')),
                                new Value(0),
                            "=",
                                assp,
                                emptyBlock1
        );
        IfElse dif = new IfElse(new Variable('p'), new Value(1), "=", new Print(new Variable('k')), emptyBlock2);
        Block b_mforek = new Block(new Declaration[]{}, new Instruction[]{ assi, mif });
        For mforek = new For('i', new Subtraction(new Variable('k'), new Value(2)), b_mforek);


        Block b_forek = new Block(
                new Declaration[]{ new Declaration('p', new Value(1)) },
                new Instruction[]{ assk, mforek, dif }
        );


        For forek = new For('k', new Subtraction(new Variable('n'), new Value(1)), b_forek);

        Block prog = new Block(
                new Declaration[]{ dn },
                new Instruction[]{ forek }
        );

        return prog;
    }

    static Block progWithBuilder() {
        return new BlockBuilder()
                .declareVariable('a', Value.of(3))
                .declareVariable('b', Addition.of(Variable.named('a'), Value.of(2)))
                .print(new Multiplication(Variable.named('a'), Variable.named('b')))
                .build();
    }

    static Block progWithBuilder2() {
        return new BlockBuilder()
                .declareVariable('x', Value.of(101))
                .declareVariable('y', Value.of(1))
                .declareProcedure("out", new char[]{'a'}, new BlockBuilder()
                        .print(Addition.of(Variable.named('a'), Variable.named('x')))
                        .build()
                )
                .assign('x', Subtraction.of(Variable.named('x'), Variable.named('y')))
                .callProcedure("out", new Expr[]{ Variable.named('x') })
                .callProcedure("out", new Expr[]{ Value.of(100) })
                .block(new BlockBuilder()
                        .declareVariable('x', Value.of(10))
                        .callProcedure("out", new Expr[]{ Value.of(100) })
                        .build()
                )
                .build();

    }

    public static void main(String[] args) {

//        Block prog_pierwsze = prog_pierwsze();
//        Debugger dbg = new Debugger(prog_pierwsze);
//        dbg.run();
//        dbg.continueSteps();

        Block progg = progWithBuilder2();
        Debugger dbg = new Debugger(progg);
        dbg.continueSteps();
    }
}