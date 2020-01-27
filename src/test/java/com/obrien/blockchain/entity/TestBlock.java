package com.obrien.blockchain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestBlock {

    @Test
    public void testGenerateBlockChain() {
        final AbstractBlock block1 = new AbstractBlock();
        final AbstractBlock block2 = block1.generateNextBlock(0);

        assertEquals(block1.getIndex(), 0);
        assertEquals(block2.getIndex(), 1);
    }

    @Test
    public void testHashcode() {
        assertEquals(new AbstractBlock(), new AbstractBlock());
        assertEquals(new AbstractBlock().generateNextBlock(123), new AbstractBlock().generateNextBlock(123));
        assertNotEquals(new AbstractBlock(), new AbstractBlock().generateNextBlock(123));
        assertNotEquals(new AbstractBlock().generateNextBlock(0), new AbstractBlock().generateNextBlock(123));

        final AbstractBlock block1 = new AbstractBlock();
        block1.getTransactions().add("Transaction 1");

        final AbstractBlock block2 = new AbstractBlock();
        block2.getTransactions().add("Transaction 1");

        assertEquals(block1, block2);

        final AbstractBlock block3 = block1.generateNextBlock(0);
        block3.getTransactions().add("Transaction 2");


        final AbstractBlock block4 = block1.generateNextBlock(0);
        block4.getTransactions().add("Transaction 2");

        assertEquals(block3, block4);
    }

    @Test
    public void testValidity() {

        AbstractBlock block = new AbstractBlock();
        for (int i = 0; i < 100; i++) {
            block.getTransactions().add("{\"i\":" + i + "}");
            block = block.generateNextBlock(0);
            assertTrue(block.isValid());
        }
        block.getPreviousBlock().getTransactions().clear();
        assertFalse(block.isValid());
    }
}
