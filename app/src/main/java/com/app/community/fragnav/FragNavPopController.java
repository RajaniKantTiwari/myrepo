package com.app.community.fragnav;

public interface FragNavPopController {
    int tryPopFragments(int popDepth, FragNavTransactionOptions transactionOptions) throws UnsupportedOperationException;
}
