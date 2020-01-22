package com.obrien.blockchain;

import com.obrien.blockchain.entity.*;

public class Main {

    public static void main(final String[] args) {

        Block block = new Block();
        System.out.println(block.toString());

        for (int i = 0; i < 100; i++) {
            block = block.generateNextBlock(i);
            System.out.println(block.toString());
        }
    }
}
