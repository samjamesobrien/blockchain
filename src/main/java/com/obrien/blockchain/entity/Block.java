package com.obrien.blockchain.entity;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Block {

    private final Integer index;

    private final List<String> transactions;

    // Prevents the hashing of all previous blocks & transactions again
    private final Integer previousBlockHash;

    private final Block previousBlock;


    public Block() {
        this.index = 0;
        this.transactions = new LinkedList<>();
        this.previousBlockHash = 0;
        this.previousBlock = null;
    }

    @SuppressWarnings("IncompleteCopyConstructor")
    private Block(final Block previousBlock) {
        this.index = previousBlock.getIndex() + 1;
        this.transactions = new LinkedList<>();
        this.previousBlockHash = previousBlock.hashCode();
        this.previousBlock = previousBlock;
    }


    /**
     * Create the next block, and pass in the current one.
     */
    public Block generateNextBlock() {
        return new Block(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equal(index, block.index) &&
                Objects.equal(previousBlockHash, block.previousBlockHash) &&
                Objects.equal(transactions, block.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(index, previousBlockHash, transactions);
    }
}
