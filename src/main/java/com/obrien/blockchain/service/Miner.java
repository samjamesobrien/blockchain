package com.obrien.blockchain.service;

import com.obrien.blockchain.entity.MutableBlock;
import com.obrien.blockchain.entity.SolvedBlock;

import java.util.Optional;

public class Miner {

    private final String start;


    public Miner(final int difficulty) {

        this.start = new String(new char[difficulty]).replace('\0', '0');
    }


    /**
     * Permute through {@link MutableBlock#number} values until a valid solution is found.
     * todo parallelize
     */
    public SolvedBlock solveBlock(final MutableBlock block) {

        Optional<SolvedBlock> solution = Optional.empty();
        int i = 0;

        while (!solution.isPresent() && i < Integer.MAX_VALUE) {
            solution = solveInRange(block, i, i + 1000);
            i += 1000;
        }

        return solution.orElseThrow(() -> new RuntimeException("Failed to solve"));
    }

    /**
     * See if a solution exists in the given range.
     */
    private Optional<SolvedBlock> solveInRange(final MutableBlock block, final int start, final int end) {

        MutableBlock potentialSolution = new MutableBlock(block);
        for (int i = start; i < end; i++) {
            potentialSolution.setNumber(i);

            if (isValidSolution(potentialSolution)) {
                final SolvedBlock solution = new SolvedBlock(potentialSolution);
                System.out.println("Solved a block with number: " + i);
                return Optional.of(solution);
            }
        }
        return Optional.empty();
    }

    /**
     * Is the hash of the block suitable, ie. does it begin with N 0's.
     */
    private boolean isValidSolution(final MutableBlock potentialSolution) {
        final String hash = potentialSolution.hash();
        return hash.startsWith(start);
    }
}
