package com.obrien.blockchain.service;

import com.obrien.blockchain.entity.Block;
import com.obrien.blockchain.entity.BlockChain;

import java.util.List;

public class Miner {

    /**
     * Mine the valid next block in the chain.
     */
    // todo - use the argument block
    public Block mine(final BlockChain chain, final List<String> data) {
        final Integer latestHash = chain.getLatestHash();

        int seed = 0;
        Block block;
        do {
            block = new Block(seed++, latestHash, data);

        } while (!block.isValid());

        return chain.addBlock(block);
    }
}
