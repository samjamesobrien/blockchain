package com.obrien.blockchain;

import com.google.common.base.Preconditions;
import com.obrien.blockchain.entity.blockchain.BlockChain;
import com.obrien.blockchain.entity.blockchain.MutableBlock;
import com.obrien.blockchain.entity.blockchain.SolvedBlock;
import com.obrien.blockchain.entity.transactions.Transaction;
import com.obrien.blockchain.entity.transactions.Wallet;
import com.obrien.blockchain.service.Miner;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.security.Security;


public class Main {

    public static void main(final String[] args) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        final Miner miner = new Miner(3);
        final BlockChain blockChain = new BlockChain();

        final Wallet wallet1 = new Wallet();

        final Wallet wallet2 = new Wallet();

        // mine 10 blocks
        for (int i = 0; i < 10; i++) {
            // Create the next block
            final MutableBlock block = new MutableBlock(blockChain.getLatestHash());

            // Add some data
            final Transaction transaction = buildTransaction(
                    i,
                    wallet1,
                    wallet2.getPublicKey(),
                    BigDecimal.valueOf(100L)
            );
            Preconditions.checkState(transaction.isValid(), "Transaction must be valid");
            block.addData(transaction.toString());


            // Solve the new block
            final SolvedBlock solved = miner.solveBlock(block);

            // Add it to the blockchain
            blockChain.addBlock(solved);
        }

        System.out.println(blockChain);
        System.out.println("Chain is valid: " + blockChain.isValid());
    }


    /**
     * Create a transaction.
     */
    private static Transaction buildTransaction(
            final Integer index,
            final Wallet from,
            final PublicKey to,
            final BigDecimal amount) {

        final Transaction transaction = new Transaction(
                index,
                from.getPublicKey(),
                to,
                amount
        );
        transaction.signTransaction(from.getPrivateKey());

        return transaction;
    }
}
