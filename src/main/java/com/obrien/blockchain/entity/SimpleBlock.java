package com.obrien.blockchain.entity;

import lombok.Data;

@Data
public class SimpleBlock {

    private final int lastHash;

    private String data;
}
