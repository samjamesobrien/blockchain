package com.obrien.blockchain.entity;

import com.google.common.collect.ImmutableList;
import lombok.ToString;

/**
 * Once a block is solved, it should be immutable.
 * <p>Immutability is enforced by validation, but for communication & convenience the API for this object
 * enforces it also.</p>
 */
@ToString
public final class SolvedBlock extends AbstractBlock {

    private final String previousHash;

    private final Integer number;

    private final ImmutableList<String> data;


    public SolvedBlock(final MutableBlock mutableBlock) {
        this.previousHash = mutableBlock.getPreviousHash();
        this.number = mutableBlock.getNumber();
        this.data = ImmutableList.copyOf(mutableBlock.getData());
    }


    @Override
    public ImmutableList<String> getData() {
        return data;
    }

    @Override
    public String getPreviousHash() {
        return previousHash;
    }

    @Override
    public Integer getNumber() {
        return number;
    }
}
