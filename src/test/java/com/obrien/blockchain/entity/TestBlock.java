package com.obrien.blockchain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBlock {


    @Test
    public void testGenerateBlockChain() {
        final Block block1 = new Block();
        final Block block2 = block1.generateNextBlock();

        assertEquals(block1.getIndex(), 0);
        assertEquals(block2.getIndex(), 1);
    }

    @Test
    public void testHashcode() {
        assertEquals(new Block(), new Block());
        assertEquals(new Block().generateNextBlock(), new Block().generateNextBlock());
        assertNotEquals(new Block(), new Block().generateNextBlock());

        final Block block1 = new Block();
        block1.getTransactions().add("Transaction 1");

        final Block block2 = new Block();
        block2.getTransactions().add("Transaction 1");

        assertEquals(block1, block2);

        final Block block3 = block1.generateNextBlock();
        block3.getTransactions().add("Transaction 2");


        final Block block4 = block1.generateNextBlock();
        block4.getTransactions().add("Transaction 2");

        assertEquals(block3, block4);
    }
}
