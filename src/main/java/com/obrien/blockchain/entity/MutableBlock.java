package com.obrien.blockchain.entity;

import lombok.ToString;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Mutable block, ie. it is not yet solved and the generated hash may not meet the requirements.
 */
@ToString
public class MutableBlock extends AbstractBlock {

    private final String previousHash;

    private final List<String> data;

    private Integer number;


    public MutableBlock(final String previousHash) {
        this.previousHash = previousHash;
        this.data = Collections.synchronizedList(new LinkedList<>());
        this.number = null;
    }

    public MutableBlock(final MutableBlock other) {
        this.previousHash = other.previousHash;
        this.data = other.data;
        this.number = other.number;
    }

    @Override
    public List<String> getData() {
        return data;
    }

    public void addData(final String datum) {
        data.add(datum);
    }

    @Override
    public String getPreviousHash() {
        return previousHash;
    }

    @Override
    public Integer getNumber() {
        return number;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }
}
