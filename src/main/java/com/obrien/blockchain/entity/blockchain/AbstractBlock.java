package com.obrien.blockchain.entity.blockchain;

import com.google.common.hash.Hashing;

import java.util.List;

import static com.obrien.blockchain.service.HashingService.hashCollection;

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
                .putUnencodedChars(hashCollection(getData()))
                .hash()
                .toString();
    }
}
