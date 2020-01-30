package com.obrien.blockchain.entity.transactions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TransactionInput {

    private final String transactionOutputId;

}
