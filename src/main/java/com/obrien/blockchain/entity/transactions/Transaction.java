package com.obrien.blockchain.entity.transactions;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.obrien.blockchain.service.Utils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashSet;
import java.util.Set;

import static com.obrien.blockchain.service.Utils.hashCollection;

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

    private byte[] signature;

    /**
     * Set the signature using the given key.
     */
    public void signTransaction(final PrivateKey key) {
        this.signature = Utils.getSignature(key, hash());
    }

    /**
     * Verify the transaction using the specified keys.
     */
    public boolean isValid() {
        Preconditions.checkState(signature != null, "signature cannot be null");
        return Utils.verifySignature(sender, hash(), signature);
    }

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("index", index)
                .add("sender", sender)
                .add("receiver", receiver)
                .add("amount", amount)
                .add("inputs", inputs)
                .add("outputs", outputs)
                .add("signature", signature)
                .toString();
    }
}
