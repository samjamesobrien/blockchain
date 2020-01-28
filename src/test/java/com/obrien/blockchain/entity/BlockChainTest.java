package com.obrien.blockchain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BlockChainTest {

    private BlockChain blockChain;

    @BeforeEach
    void setUp() {
        blockChain = new BlockChain();
    }

    @Test
    void emptyChainIsValid() {
        assertTrue(blockChain.isValid());
    }

    @Test
    void singleBlockChainIsValid() {
        blockChain.addBlock(new SolvedBlock(new MutableBlock("0")));
        assertTrue(blockChain.isValid());
    }

    @Test
    void multiBlockChain() {
        MutableBlock block1 = new MutableBlock("0");
        block1.setNumber(0);

        MutableBlock block2 = new MutableBlock(block1.hash());
        block2.setNumber(0);

        MutableBlock block3 = new MutableBlock(block2.hash());
        block3.setNumber(0);

        blockChain.addBlock(new SolvedBlock(block1));
        blockChain.addBlock(new SolvedBlock(block2));
        blockChain.addBlock(new SolvedBlock(block3));

        assertTrue(blockChain.isValid());

        blockChain.addBlock(new SolvedBlock(block1));
        assertFalse(blockChain.isValid());
    }
}
