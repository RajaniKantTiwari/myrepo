package com.app.community.fragnav.tabhistory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.app.community.fragnav.FragNavPopController;
import com.app.community.fragnav.FragNavTransactionOptions;


public class CurrentTabHistoryController extends BaseFragNavTabHistoryController {
    public CurrentTabHistoryController(FragNavPopController fragNavPopController) {
        super(fragNavPopController);
    }

    @Override
    public boolean popFragments(int popDepth,
                                FragNavTransactionOptions transactionOptions) throws UnsupportedOperationException {
        return fragNavPopController.tryPopFragments(popDepth, transactionOptions) > 0;
    }

    @Override
    public void switchTab(int index) {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
    }

    @Override
    public void restoreFromBundle(@Nullable Bundle savedInstanceState) {
    }
}
