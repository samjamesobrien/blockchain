package com.obrien.blockchain.service;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;

import java.util.Collection;

public class HashingService {

    /**
     * Hash a collection.
     */
    public static <T> String hashCollection(final Collection<T> collection) {
        return Hashing.sha256()
                .hashObject(collection, Funnels.sequentialFunnel(
                        (input, sink) -> sink.putInt(input.hashCode())
                ))
                .toString();
    }
}
