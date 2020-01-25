package com.obrien.blockchain;

import com.obrien.blockchain.entity.*;
import com.obrien.blockchain.service.Miner;

import java.util.Arrays;

public class Main {

    public static void main(final String[] args) {

        final Miner miner = new Miner();

        final BlockChain blockChain = new BlockChain();

        // mine 10 blocks
        for (int i = 0; i < 10; i++) {
            miner.mine(blockChain, Arrays.asList("Data: " + i));
        }

        System.out.println(blockChain);
        System.out.println("Chain is valid: " + blockChain.isValid());
    }
}
