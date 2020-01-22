package com.obrien.blockchain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Block {

    private final Integer seed;

    private final Integer index;

    private final List<String> transactions;

    // Prevents the hashing of all previous blocks & transactions again
    private final Integer previousBlockHash;

    @JsonIgnore
    private final Block previousBlock;


    public Block() {
        this.seed = 0;
        this.index = 0;
        this.transactions = new LinkedList<>();
        this.previousBlockHash = 0;
        this.previousBlock = null;
    }

    private Block(final Integer seed, final Block previousBlock) {
        this.seed = seed;
        this.index = previousBlock.getIndex() + 1;
        this.transactions = new LinkedList<>();
        this.previousBlockHash = previousBlock.hashCode();
        this.previousBlock = previousBlock;
    }


    /**
     * Create the next block, and pass in the current one.
     */
    public Block generateNextBlock(final Integer seed) {
        return new Block(seed, this);
    }

    /**
     * If the previous block is null, this is the genesis block.
     * Otherwise verify the previous block, which in turn verifies its previous etc.
     */
    @JsonIgnore
    public boolean isValid() {
        return previousBlock == null || (previousBlockHash == previousBlock.hashCode() && previousBlock.isValid());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equal(seed, block.seed) &&
                Objects.equal(index, block.index) &&
                Objects.equal(previousBlockHash, block.previousBlockHash) &&
                Objects.equal(transactions, block.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(seed, index, previousBlockHash, transactions);
    }
}
