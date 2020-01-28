package com.obrien.blockchain.entity;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractBlockTest {

    private TestBlock block1;

    private TestBlock block2;

    @BeforeEach
    void setUp() {
        block1 = new TestBlock();
        block1.setData(ImmutableList.of("data 1"));
        block1.setPreviousHash("previousHash");
        block1.setNumber(1);

        block2 = new TestBlock();
        block2.setData(ImmutableList.of("data 1"));
        block2.setPreviousHash("previousHash");
        block2.setNumber(1);
    }

    @Test
    void hash() {
        assertEquals(block1.hash(), block2.hash());

        block2.setNumber(2);
        assertNotEquals(block1.hash(), block2.hash());

        setUp();
        block2.setPreviousHash("anotherHash");
        assertNotEquals(block1.hash(), block2.hash());

        setUp();
        block2.setData(ImmutableList.of("data 2"));
        assertNotEquals(block1.hash(), block2.hash());
    }


    @Getter
    @Setter
    private static class TestBlock extends AbstractBlock {
        private String previousHash;
        private List<String> data;
        private Integer number;
    }
}
