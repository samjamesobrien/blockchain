package com.obrien.blockchain.entity;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;

import java.util.List;

public abstract class AbstractBlock {

    /**
     * What data does this block contain?
     */
    public abstract List<String> getData();

    /**
     * What is the previous blocks hash.
     */
    public abstract String getPreviousHash();

    /**
     * What is the "number only used once" that informs the hash.
     */
    public abstract Number getNumber();


    /**
     * Differs from {@link Object#hashCode()} as we return a {@link String}.
     */
    public String hash() {
        return Hashing.sha256()
                .newHasher()
                .putUnencodedChars(getPreviousHash())
                .putLong(getNumber().longValue())
                .putUnencodedChars(hashData())
                .hash()
                .toString();
    }

    /**
     * Hash the data only.
     */
    private String hashData() {
        return Hashing.sha256()
                .hashObject(getData(), Funnels.sequentialFunnel(
                        (input, sink) -> sink.putInt(input.hashCode())
                ))
                .toString();
    }
}
