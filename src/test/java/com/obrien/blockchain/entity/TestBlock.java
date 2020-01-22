package com.obrien.blockchain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestBlock {

    @Test
    public void testGenerateBlockChain() {
        final Block block1 = new Block();
        final Block block2 = block1.generateNextBlock(0);

        assertEquals(block1.getIndex(), 0);
        assertEquals(block2.getIndex(), 1);
    }

    @Test
    public void testHashcode() {
        assertEquals(new Block(), new Block());
        assertEquals(new Block().generateNextBlock(123), new Block().generateNextBlock(123));
        assertNotEquals(new Block(), new Block().generateNextBlock(123));
        assertNotEquals(new Block().generateNextBlock(0), new Block().generateNextBlock(123));

        final Block block1 = new Block();
        block1.getTransactions().add("Transaction 1");

        final Block block2 = new Block();
        block2.getTransactions().add("Transaction 1");

        assertEquals(block1, block2);

        final Block block3 = block1.generateNextBlock(0);
        block3.getTransactions().add("Transaction 2");


        final Block block4 = block1.generateNextBlock(0);
        block4.getTransactions().add("Transaction 2");

        assertEquals(block3, block4);
    }

    @Test
    public void testValidity() {

        Block block = new Block();
        for (int i = 0; i < 100; i++) {
            block.getTransactions().add("{\"i\":" + i + "}");
            block = block.generateNextBlock(0);
            assertTrue(block.isValid());
        }
        block.getPreviousBlock().getTransactions().clear();
        assertFalse(block.isValid());
    }
}
