package com.obrien.blockchain;

import com.obrien.blockchain.entity.*;
import com.obrien.blockchain.service.Miner;


public class Main {

    public static void main(final String[] args) {

        final Miner miner = new Miner(5);

        final BlockChain blockChain = new BlockChain();

        // mine 10 blocks
        for (int i = 0; i < 10; i++) {
            final MutableBlock block = new MutableBlock(blockChain.getLatestHash());

            final SolvedBlock solved = miner.solveBlock(block);

            blockChain.addBlock(solved);
        }

        System.out.println(blockChain);
        System.out.println("Chain is valid: " + blockChain.isValid());
    }
}
