package runtime;

import instructions.Block;
import instructions.Instruction;

import java.util.Scanner;
import java.util.Stack;

/*
    If you want to just run the program
    call .continueSteps()
    If you want to run the program in debug mode
    call .run()
 */

public class Debugger {
    private final Scanner scanner;
    private final Stack<Instruction> stack;
    private final Stack<Block> blockStack;

    public Debugger(Block program) {
        scanner = new Scanner(System.in);
        stack = new Stack<>();
        stack.push(program);
        blockStack = new Stack<>();
        blockStack.push(program);
    }

    public void stackPush(Instruction i) {
        stack.push(i);
    }

    public void stackPush(Block b) {
        stack.push(b);
        blockStack.push(b);
    }


    /*
        Does 'stepsNum' steps
        Where a step is a one simple instruction:
        Declaration, Print, or Assign
     */
    private void doSteps(long stepsNum) {
        for (int i = 0; i < stepsNum; i++) {

            boolean stepDone = false;
            while (!stepDone && !stack.empty()) {
                if (stack.peek().isCompleted()) {
                    if (stack.peek() == blockStack.peek()) {
                        blockStack.pop();
                    }
                    stack.pop();
                    continue;
                }
                Block blockRef = blockStack.peek();
                Instruction newInstruction = stack.peek().exec(blockRef);

                if (newInstruction != null) {
                    newInstruction.pushSelf(this);
                } else {
                    stepDone = true;
                }
            }
            if (!stepDone) {
                System.out.printf("%d steps done and no step remained%n", i);
                return;
            }
        }
        System.out.println("All steps done. ");
    }

    /*
        Continues execution of a program to the end
     */
    public void continueSteps() {
        doSteps(Long.MAX_VALUE);
    }

    /*
        For k = 0
        Prints values of variables that are visible in a current block

        For k > 0
        Prints values of variables that are visible in a k-th block up
     */
    private void display(int k) {
        if (k >= blockStack.size()) {
            System.out.println("Number is bigger than current nesting level");
            return;
        }
        Block blockRef = blockStack.elementAt(blockStack.size() - 1 - k);
        for (char c = 'a'; c <= 'z'; c++) {
            if (blockRef.varExists(c)) {
                System.out.printf("%c: %d%n", c, blockRef.getVarValue(c));
            }
        }
    }

    public void run() {
        System.out.println("Debugger is running");
        while (true) {
            System.out.printf("> ");
            String command = scanner.next();
            if (command.length() > 1) {
                System.out.println("Name of a command needs to be one letter");
                continue;
            }
            char c = command.charAt(0);
            if (c == 'c') {
                continueSteps();
            } else if (c == 's') {
                int stepsNum = scanner.nextInt();
                doSteps(stepsNum);
            } else if (c == 'd') {
                int levelsUp = scanner.nextInt();
                display(levelsUp);
            } else if (c == 'e') {
                System.out.println("Exiting debugger");
                break;
            } else {
                System.out.println("Wrong command name");
            }
        }

    }
}