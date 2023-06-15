package instructions.test;

import instructions.Block;
import instructions.Instruction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    @Test
    void test() {
        Block block1 = new Block(new Instruction[]{}, new Instruction[]{});
        Block block2 = new Block(new Instruction[]{}, new Instruction[]{});
        Block block3 = new Block(new Instruction[]{}, new Instruction[]{ block2, block1 });
        assertEquals(block3.exec(block3), block2);
        assertEquals(block3.exec(block3), block1);
        assertTrue(block3.isCompleted());
    }
}