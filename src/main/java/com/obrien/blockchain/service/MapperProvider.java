package com.obrien.blockchain.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperProvider {

    static {
        MAPPER = new ObjectMapper();
    }

    private static final ObjectMapper MAPPER;

    public ObjectMapper getInstance() {
        return MAPPER;
    }
}
