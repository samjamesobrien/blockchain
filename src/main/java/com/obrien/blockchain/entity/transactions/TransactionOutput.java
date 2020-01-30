package com.obrien.blockchain.entity.transactions;

import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.security.PublicKey;

@Getter
@RequiredArgsConstructor
public class TransactionOutput {

    private final String transactionHash;
    private final PublicKey recipient;
    private final BigDecimal amount;

    public String hash() {
        return Hashing.sha256()
                .newHasher()
                .putUnencodedChars(transactionHash)
                .putBytes(recipient.getEncoded())
                .putFloat(amount.floatValue())
                .hash()
                .toString();
    }
    
    public boolean belongsTo(final PublicKey publicKey) {
        return recipient.equals(publicKey);
    }
}
