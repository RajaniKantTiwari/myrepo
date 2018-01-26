package com.app.community.ui.newspaper.event;

/**
 * Created by rajnikant on 26/01/18.
 */

public class SubscriptionEvent {
    //1 for subscription 2 for subscriptiondetails
    private int show;

    public int getShow() {
        return show;
    }

    public SubscriptionEvent(int show) {
        this.show = show;
    }
}
