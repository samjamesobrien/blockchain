package com.obrien.blockchain.entity.transactions;

import lombok.Getter;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

@Getter
public class Wallet {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public Wallet() {
        try {
            final SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            final ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
            keyGen.initialize(ecSpec, random);   //256 bytes provides an acceptable security level

            final KeyPair keyPair = keyGen.generateKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();

        } catch (final GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public Wallet(final KeyPair keyPair) {
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public Wallet(final PrivateKey privateKey, final PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
}
