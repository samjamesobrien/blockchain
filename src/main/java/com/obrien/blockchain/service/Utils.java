package com.obrien.blockchain.service;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Collection;

public class Utils {

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

    /**
     * Sign the input with the private key.
     */
    public static byte[] getSignature(final PrivateKey privateKey, final String data) {
        try {
            final Signature signature = Signature.getInstance("ECDSA", "BC");
            signature.initSign(privateKey);
            byte[] inputBytes = data.getBytes();
            signature.update(inputBytes);
            return signature.sign();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verify a signature with the public key.
     */
    public static boolean verifySignature(
            final PublicKey publicKey, final String data, final byte[] signatureToVerify) {

        try {
            final Signature signature = Signature.getInstance("ECDSA", "BC");
            signature.initVerify(publicKey);
            signature.update(data.getBytes());
            return signature.verify(signatureToVerify);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
