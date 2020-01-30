package com.obrien.blockchain.entity.transactions;

import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.HashSet;
import java.util.Set;

import static com.obrien.blockchain.service.HashingService.hashCollection;

@Getter
@Setter
@RequiredArgsConstructor
public class Transaction {

    private final Integer index;

    private final PublicKey sender;

    private final PublicKey receiver;

    private final BigDecimal amount;

    private final Set inputs = new HashSet<>();

    private final Set outputs = new HashSet<>();

    /**
     * Effectively the transaction id.
     */
    public String hash() {
        return Hashing.sha256()
                .newHasher()
                .putInt(index)
                .putBytes(sender.getEncoded())
                .putBytes(receiver.getEncoded())
                .putFloat(amount.floatValue())
                .putUnencodedChars(hashCollection(inputs))
                .putUnencodedChars(hashCollection(outputs))
                .hash()
                .toString();
    }
}
