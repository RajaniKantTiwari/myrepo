package com.app.community.fragnav.tabhistory;


import com.app.community.fragnav.FragNavPopController;

abstract class BaseFragNavTabHistoryController implements FragNavTabHistoryController {
    FragNavPopController fragNavPopController;

    BaseFragNavTabHistoryController(FragNavPopController fragNavPopController) {
        this.fragNavPopController = fragNavPopController;
    }
}
