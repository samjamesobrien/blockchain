package com.obrien.blockchain.entity;

import com.google.common.collect.ImmutableList;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@ToString
public class BlockChain {

    private LinkedList<SolvedBlock> blocks = new LinkedList<>();

    /**
     * Iterate over the blocks, and verify their hash passes the proof of work,
     * and the previous hash matches the hash of the previous block.
     */
    public boolean isValid() {
        boolean isValid = true;
        AbstractBlock previousBlock = null;
        for (final AbstractBlock block : getBlocks()) {
            isValid = previousBlock == null || previousBlock.hash() == block.getPreviousHash();
            previousBlock = block;
            if (!isValid) break;
        }
        return isValid;
    }

    /**
     * Return an immutable view of the blocks.
     */
    public synchronized List<SolvedBlock> getBlocks() {
        return ImmutableList.copyOf(blocks);
    }

    /**
     * Add a block to the chain.
     */
    public synchronized void addBlock(final SolvedBlock block) {
        blocks.add(block);
    }

    /**
     * Get the latest hash.
     */
    public String getLatestHash() {
        return blocks.isEmpty() ? "0" : blocks.getLast().hash();
    }
}
