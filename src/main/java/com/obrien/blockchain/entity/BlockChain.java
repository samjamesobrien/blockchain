package com.obrien.blockchain.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class BlockChain {

    private LinkedList<Block> blocks = new LinkedList<>();

    /**
     * Iterate over the blocks, and verify their hash passes the proof of work,
     * and the previous hash matches the hash of the previous block.
     */
    public boolean isValid() {
        boolean isValid = true;
        Block previousBlock = null;
        for (final Block block : getBlocks()) {
            isValid = block.isValid() &&
                    (previousBlock == null || previousBlock.hashCode() == block.getPreviousHash());

            previousBlock = block;
        }
        return isValid;
    }

    /**
     * Return an immutable view of the blocks.
     */
    public synchronized List<Block> getBlocks() {
        return ImmutableList.copyOf(blocks);
    }

    /**
     * What is the hash of the latest block?
     */
    public Integer getLatestHash() {
        return blocks.isEmpty() ? 0 : getLatestBlock().hashCode();
    }

    /**
     * Get the newest block in the chain.
     */
    public Block getLatestBlock() {
        return blocks.getLast();
    }

    /**
     * Try to add a block to the chain, it must be valid.
     */
    public synchronized Block addBlock(final Block block) {
        if (block.isValid()
                && getLatestHash().equals(block.getPreviousHash())
                && blocks.add(block)) {

            return block;
        } else {
            throw new IllegalArgumentException("Bad block!");
        }
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
