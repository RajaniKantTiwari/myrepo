package com.app.community.ui.dashboard.home.event;

/**
 * Created by rajnikant on 07/01/18.
 */

public class FragmentEvent {
    private int fragmentId;
    private boolean shouldAdd;
    private boolean isAddToBackStack;

    public FragmentEvent(int fragmentId, boolean shouldAdd, boolean isAddToBackStack) {
        this.fragmentId = fragmentId;
        this.shouldAdd = shouldAdd;
        this.isAddToBackStack = isAddToBackStack;
    }

    public int getFragmentId() {
        return fragmentId;
    }

    public boolean isShouldAdd() {
        return shouldAdd;
    }

    public boolean isAddToBackStack() {
        return isAddToBackStack;
    }
}
