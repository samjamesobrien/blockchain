package com.obrien.blockchain.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Block {

    private final Integer seed;

    private final Integer previousHash;

    // todo - make this usable
    private final List<String> data = new LinkedList<>();


    /**
     * Is the hashcode some multiple of 1000.
     */
    public boolean isValid() {
        return hashCode() % 1000 == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(seed, previousHash, data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equal(seed, block.seed) &&
                Objects.equal(previousHash, block.previousHash) &&
                Objects.equal(data, block.data);
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
