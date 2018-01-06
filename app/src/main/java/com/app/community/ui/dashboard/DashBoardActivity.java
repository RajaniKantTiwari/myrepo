package com.app.community.ui.dashboard;

import android.app.ActivityManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;


import com.app.community.CommonApplication;
import com.app.community.R;
import com.app.community.databinding.ActivityDashboardBinding;
import com.app.community.injector.component.DaggerDashboardComponent;
import com.app.community.injector.component.DashboardComponent;
import com.app.community.injector.module.DashboardModule;
import com.app.community.navcontroller.BackStackActivity;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.home.expendedrecyclerview.adapter.DrawerViewAdapter;
import com.app.community.ui.dashboard.home.expendedrecyclerview.model.Artist;
import com.app.community.ui.dashboard.home.expendedrecyclerview.model.Genre;
import com.app.community.ui.dashboard.home.fragment.CartFragment;
import com.app.community.ui.dashboard.home.fragment.CheckoutFragment;
import com.app.community.ui.dashboard.home.fragment.DainikJagranFragment;
import com.app.community.ui.dashboard.home.fragment.DoctorListFragment;
import com.app.community.ui.dashboard.home.fragment.FullInformationFragment;
import com.app.community.ui.dashboard.home.fragment.HelpandSupportFragment;
import com.app.community.ui.dashboard.home.fragment.HomeFragment;
import com.app.community.ui.dashboard.home.fragment.MyOrderFragment;
import com.app.community.ui.dashboard.home.fragment.NewsTabFragment;
import com.app.community.ui.dashboard.notification.NotificationFragment;
import com.app.community.ui.dashboard.offer.OfferFragment;
import com.app.community.ui.dashboard.user.UserFragment;
import com.app.community.widget.bottomnavigation.BottomNavigationBar;
import com.app.community.widget.bottomnavigation.NavigationPage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DashBoardActivity extends BackStackActivity implements BottomNavigationBar.BottomNavigationMenuClickListener {
    private ActivityDashboardBinding mBinding;

    public DashboardComponent mDashboardComponent;
    @Inject
    public DashboardPresenter mPresenter;

    private OnToolbarItemClickedListener onClickedListener;

    //Sliding drawer
    ActionBarDrawerToggle mDrawerToggle;

    //Bottom bar
    // helper class for handling UI and click events of bottom-nav-bar
    private BottomNavigationBar mBottomNav;
    // list of Navigation pages to be shown
    private List<NavigationPage> mNavigationPageList = new ArrayList<>();

    private static final String STATE_CURRENT_TAB_ID = "current_tab_id";
    private static final int MAIN_TAB_ID = BottomNavigationBar.MENU_BAR_1;
    private Fragment curFragment;
    private int curTabId = MAIN_TAB_ID;
    private DrawerViewAdapter mDrawerAdapter;
    private List<Genre> listDrawerExpandable;

    public interface OnToolbarItemClickedListener {
        void onClicked(int id);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        listDrawerExpandable = new ArrayList<>();
        initTabs();
        initDashboardComponent();
        attachView();
        setupDrawerToggle();
    }

    private void initDashboardComponent() {
        mDashboardComponent = DaggerDashboardComponent.builder().
                dashboardModule(new DashboardModule(this)).
                applicationComponent(((CommonApplication) getApplication()).
                        getApplicationComponent()).build();

    }

    public DashboardComponent getActivityComponent() {
        return mDashboardComponent;
    }

    public void showFragment(@NonNull Fragment fragment) {
        showFragment(fragment, true);
    }


    public void showFragment(@NonNull Fragment fragment, boolean addToBackStack) {
        if (curFragment != null && addToBackStack) {
            pushFragmentToBackStack(curTabId, curFragment);
        }
        replaceFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        Pair<Integer, Fragment> pair = popFragmentFromBackStack();
        if (pair != null) {
            backTo(pair.first, pair.second);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onTabSelected(int position) {
        if (curFragment != null) {
            pushFragmentToBackStack(curTabId, curFragment);
        }
        curTabId = position;
        Fragment fragment = popFragmentFromBackStack(curTabId);
        if (fragment == null) {
            fragment = rootTabFragment(curTabId);
        }
        replaceFragment(fragment);
    }


    private void backTo(int tabId, @NonNull Fragment fragment) {
        if (tabId != curTabId) {
            curTabId = tabId;
            mBottomNav.selectItem(curTabId);
        }
        replaceFragment(fragment);
        getSupportFragmentManager().executePendingTransactions();
    }

   /* private void backToRoot() {
        if (isRootTabFragment(curFragment, curTabId)) {
            return;
        }
        resetBackStackToRoot(curTabId);
        Fragment rootFragment = popFragmentFromBackStack(curTabId);
        assert rootFragment != null;
        backTo(curTabId, rootFragment);
    }

    private boolean isRootTabFragment(@NonNull Fragment fragment, int tabId) {
        return fragment.getClass() == rootTabFragment(tabId).getClass();
    }*/

    private void replaceFragment(@NonNull Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        tr.replace(R.id.container, fragment);
        tr.commitAllowingStateLoss();
        curFragment = fragment;
    }


    @NonNull
    private Fragment rootTabFragment(int tabId) {
        switch (tabId) {
            case BottomNavigationBar.MENU_BAR_1:
                //return HomeFragment.newInstance();
                return DoctorListFragment.newInstance();
            case BottomNavigationBar.MENU_BAR_2:
                return OfferFragment.newInstance();
            case BottomNavigationBar.MENU_BAR_3:
                return NotificationFragment.newInstance();
            case BottomNavigationBar.MENU_BAR_4:
                return UserFragment.newInstance();
            default:
                throw new IllegalArgumentException();
        }
    }


    private void initTabs() {
        NavigationPage page1 = new NavigationPage("", ContextCompat.getDrawable(this, R.drawable.ic_home), rootTabFragment(BottomNavigationBar.MENU_BAR_1));
        NavigationPage page2 = new NavigationPage("", ContextCompat.getDrawable(this, R.drawable.ic_gift), rootTabFragment(BottomNavigationBar.MENU_BAR_2));
        NavigationPage page3 = new NavigationPage("", ContextCompat.getDrawable(this, R.drawable.ic_notification), rootTabFragment(BottomNavigationBar.MENU_BAR_3));
        NavigationPage page4 = new NavigationPage("", ContextCompat.getDrawable(this, R.drawable.ic_user), rootTabFragment(BottomNavigationBar.MENU_BAR_4));

        List<NavigationPage> navigationPages = new ArrayList<>();
        navigationPages.add(page1);
        navigationPages.add(page2);
        navigationPages.add(page3);
        navigationPages.add(page4);

        setupBottomBarHolderActivity(navigationPages);

    }


    /**
     * initializes the BottomBarHolderActivity with sent list of Navigation pages
     *
     * @param pages
     */
    public void setupBottomBarHolderActivity(List<NavigationPage> pages) {

        // throw error if pages does not have 4 elements
        if (pages.size() != 4) {
            throw new RuntimeException("List of NavigationPage must contain 4 members.");
        } else {
            mNavigationPageList = pages;
            mBottomNav = new BottomNavigationBar(this, pages, this);
//            if (savedInstanceState == null) {
            showFragment(rootTabFragment(MAIN_TAB_ID));
//            }
            mBottomNav.selectItem(MAIN_TAB_ID);
        }

    }

    public void setClickedListener(OnToolbarItemClickedListener onClickedListener) {
        this.onClickedListener = onClickedListener;
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
        mBinding.toolBar.ivMenu.setOnClickListener(this);
        mBinding.toolBar.ivDrawer.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (mBinding.toolBar.ivDrawer == view) {
            openDrawer();
        }
    }


    void setupDrawerToggle() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mBinding.drawer, null, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
        mBinding.drawer.addDrawerListener(mDrawerToggle);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
// RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:
        RecyclerView.ItemAnimator animator = mBinding.layoutDrawer.rvDrawer.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        setListItem();
        mDrawerAdapter = new DrawerViewAdapter(listDrawerExpandable, this);
        mBinding.layoutDrawer.rvDrawer.setLayoutManager(layoutManager);
        mBinding.layoutDrawer.rvDrawer.setAdapter(mDrawerAdapter);
    }

    private void setListItem() {
        for (int i = 0; i < 5; i++) {
            Genre genre = new Genre("Title" + i, setArtistList(i));
            listDrawerExpandable.add(genre);

        }
    }

    private List<Artist> setArtistList(int number) {
        List<Artist> artistList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Artist artist = new Artist("Artist" + number + i, false);
            artistList.add(artist);
        }
        return artistList;
    }

    private void openDrawer() {
        if (!mBinding.drawer.isDrawerOpen(Gravity.RIGHT)) {
            mBinding.drawer.openDrawer(Gravity.RIGHT);
        }
    }

    private void closeDrawer() {
        if (mBinding.drawer.isDrawerOpen(Gravity.RIGHT)) {
            mBinding.drawer.closeDrawers();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDrawerAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mDrawerAdapter.onRestoreInstanceState(savedInstanceState);
    }

}
