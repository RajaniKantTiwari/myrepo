package com.app.community.ui.dashboard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.app.community.CommonApplication;
import com.app.community.R;
import com.app.community.databinding.ActivityDashboardBinding;
import com.app.community.fragnav.FragNavController;
import com.app.community.fragnav.FragNavSwitchController;
import com.app.community.fragnav.FragNavTransactionOptions;
import com.app.community.fragnav.tabhistory.FragNavTabHistoryController;
import com.app.community.injector.component.DaggerDashboardComponent;
import com.app.community.injector.component.DashboardComponent;
import com.app.community.injector.module.DashboardModule;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.drawerresponse.Ingredient;
import com.app.community.network.response.dashboard.drawerresponse.Recipe;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.cart.ProductSubproductFragment;
import com.app.community.ui.dashboard.expandrecycleview.draweradapter.DrawerAdapterRight;
import com.app.community.ui.dashboard.home.SearchActivity;
import com.app.community.ui.dashboard.home.WelcomeHomeFragment;
import com.app.community.ui.dashboard.home.adapter.DrawerAdapterLeft;
import com.app.community.ui.dashboard.notification.NotificationFragment;
import com.app.community.ui.dashboard.offer.OfferFragment;
import com.app.community.ui.dashboard.user.UserFragment;
import com.app.community.utils.ExplicitIntent;
import com.app.community.widget.bottomnavigation.BottomNavigationBar;
import com.app.community.widget.bottomnavigation.NavigationPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.PRODUCT_SUBPRODUCT;

public class DashBoardActivity extends BaseActivity implements BottomNavigationBar.BottomNavigationMenuClickListener,
        BaseFragment.FragmentNavigation, FragNavController.TransactionListener, FragNavController.RootFragmentListener,
        DrawerAdapterLeft.DrawerLeftListener,DrawerAdapterRight.ProductSubHolderListener {

    //Better convention to properly name the indices what they are in your app
    private final int HOME = FragNavController.TAB1;
    private final int OFFER = FragNavController.TAB2;
    private final int NOTIFICATION = FragNavController.TAB3;
    private final int USER = FragNavController.TAB4;
    private FragNavController mNavController;

    private ActivityDashboardBinding mBinding;
    public DashboardComponent mDashboardComponent;
    @Inject
    public DashboardPresenter mPresenter;
    //Sliding drawer
    private ActionBarDrawerToggle mDrawerToggleRight;

    //Bottom bar
    // helper class for handling UI and click events of bottom-nav-bar
    private BottomNavigationBar mBottomNav;
    // list of Navigation pages to be shown
    private static final int MAIN_TAB_ID = FragNavController.TAB1;
    private DrawerAdapterRight mDrawerAdapterRight;
    private List<Recipe> listDrawerExpandable;
    private ArrayList<NavigationPage> navigationPages;
    private ActionBarDrawerToggle mDrawerToggleLeft;
    private DrawerAdapterLeft mDrawerAdapterLeft;

    @Override
    public void onLeftDrawerItemClicked(int adapterPosition) {
        showToast("Clicked " + adapterPosition);
    }


    public interface OnToolbarItemClickedListener {
        void onClicked(int id);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        hideSoftKeyboard(mBinding.getRoot());

        listDrawerExpandable = new ArrayList<>();
        initTabs();
        initDashboardComponent();
        attachView();
        setupDrawerToggleRight();
        setupDrawerToggleLeft();
        navigationControl(savedInstanceState);
        initializeData();
        setListener();
    }

    private void navigationControl(Bundle savedInstanceState) {
        boolean initial = savedInstanceState == null;
        if (initial) {
            mBottomNav.selectItem(HOME);
        }
        mNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.container)
                .transactionListener(this)
                .rootFragmentListener(this, 4)
                .popStrategy(FragNavTabHistoryController.UNIQUE_TAB_HISTORY)
                .switchController(new FragNavSwitchController() {
                    @Override
                    public void switchTab(int index, FragNavTransactionOptions transactionOptions) {
                        mBottomNav.selectItem(index);
                    }
                })
                .build();
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

    @Override
    public void onBackPressed() {
        if (!mNavController.popFragment()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onTabSelected(int position) {
        changeIcon(position);
        switch (position) {
            case HOME:
                mNavController.switchTab(HOME);
                //mNavController.clearStack();
                break;
            case OFFER:
                mNavController.switchTab(OFFER);
                break;
            case NOTIFICATION:
                mNavController.switchTab(NOTIFICATION);
                break;
            case USER:
                mNavController.switchTab(USER);
                break;
        }
    }

    private void changeIcon(int position) {
        for (int i = 0; i < navigationPages.size(); i++) {
            NavigationPage navigation = navigationPages.get(i);
            switch (i) {
                case 0:
                    if (i == position) {
                        navigation.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_home));
                    } else {
                        navigation.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_home_light));
                    }

                    break;
                case 1:
                    if (i == position) {
                        navigation.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_gift));
                    } else {
                        navigation.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_gift_light));
                    }
                    break;
                case 2:
                    if (i == position) {
                        navigation.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_notification));
                    } else {
                        navigation.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_notification_light));
                    }
                    break;
                case 3:
                    if (i == position) {
                        navigation.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_user));
                    } else {
                        navigation.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_user_light));
                    }
                    break;
            }
            navigationPages.set(i, navigation);
        }
        mBottomNav.setIcon(navigationPages);
    }


    private void initTabs() {
        NavigationPage page1 = new NavigationPage("", ContextCompat.getDrawable(this, R.drawable.ic_home), getRootFragment(HOME));
        NavigationPage page2 = new NavigationPage("", ContextCompat.getDrawable(this, R.drawable.ic_gift_light), getRootFragment(OFFER));
        NavigationPage page3 = new NavigationPage("", ContextCompat.getDrawable(this, R.drawable.ic_notification_light), getRootFragment(NOTIFICATION));
        NavigationPage page4 = new NavigationPage("", ContextCompat.getDrawable(this, R.drawable.ic_user_light), getRootFragment(USER));

        navigationPages = new ArrayList<>();
        navigationPages.add(page1);
        navigationPages.add(page2);
        navigationPages.add(page3);
        navigationPages.add(page4);

        setupBottomBarHolderActivity(navigationPages);

    }


    /**
     * initializes the BottomBarHolderActivity with sent list of Navigation pages
     *
     * @param
     */
    public void setupBottomBarHolderActivity(List<NavigationPage> pages) {

        // throw error if pages does not have 4 elements
        if (pages.size() != 4) {
            throw new RuntimeException("List of NavigationPage must contain 4 members.");
        } else {
            // mNavigationPageList = pages;
            mBottomNav = new BottomNavigationBar(this, pages, this);
//            if (savedInstanceState == null) {
            //showFragment(rootTabFragment(MAIN_TAB_ID));
//            }
            mBottomNav.selectItem(MAIN_TAB_ID);
        }

    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
    }

    public void initializeData() {

    }

    public void setListener() {
        mBinding.toolBar.ivSearch.setOnClickListener(this);
        mBinding.toolBar.ivDrawer.setOnClickListener(this);
        mBinding.toolBar.ivRightDrawer.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (mBinding.toolBar.ivDrawer == view) {
            openDrawerLeft();
        } else if (view == mBinding.toolBar.ivRightDrawer) {
            openDrawerRight();
        } else if (view == mBinding.toolBar.ivSearch) {
            ExplicitIntent.getsInstance().navigateTo(this, SearchActivity.class);
        }
    }


    void setupDrawerToggleRight() {

        mDrawerToggleRight = new ActionBarDrawerToggle(this, mBinding.drawer, null, R.string.app_name, R.string.app_name) {
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
        mDrawerToggleRight.syncState();
        mBinding.drawer.addDrawerListener(mDrawerToggleRight);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:
        RecyclerView.ItemAnimator animator = mBinding.layoutDrawerRight.rvDrawer.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        setListItem();
        mDrawerAdapterRight = new DrawerAdapterRight(this,listDrawerExpandable,this);
        mBinding.layoutDrawerRight.rvDrawer.setLayoutManager(layoutManager);
        mBinding.layoutDrawerRight.rvDrawer.setAdapter(mDrawerAdapterRight);
    }


    void setupDrawerToggleLeft() {

        mDrawerToggleLeft = new ActionBarDrawerToggle(this, mBinding.drawer, null, R.string.app_name, R.string.app_name) {
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
        mDrawerToggleLeft.syncState();
        mBinding.drawer.addDrawerListener(mDrawerToggleLeft);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:
        RecyclerView.ItemAnimator animator = mBinding.layoutDrawerLeft.rvDrawer.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        mDrawerAdapterLeft = new DrawerAdapterLeft(this, this);
        mBinding.layoutDrawerLeft.rvDrawer.setLayoutManager(layoutManager);
        mBinding.layoutDrawerLeft.rvDrawer.setAdapter(mDrawerAdapterLeft);
    }


    private void setListItem() {
        Ingredient beef = new Ingredient("beef", false);
        Ingredient cheese = new Ingredient("cheese", true);
        Ingredient salsa = new Ingredient("salsa", true);
        Ingredient tortilla = new Ingredient("tortilla", true);
        Ingredient ketchup = new Ingredient("ketchup", true);
        Ingredient bun = new Ingredient("bun", true);

        Recipe taco = new Recipe("taco", Arrays.asList(beef, cheese, salsa, tortilla));
        Recipe quesadilla = new Recipe("quesadilla", Arrays.asList(cheese, tortilla));
        Recipe burger = new Recipe("burger", Arrays.asList(beef, cheese, ketchup, bun));
        listDrawerExpandable = Arrays.asList(taco, quesadilla, burger);
    }


    private void openDrawerLeft() {
        if (!mBinding.drawer.isDrawerOpen(Gravity.LEFT)) {
            mBinding.drawer.openDrawer(Gravity.LEFT);
        }
    }

    private void openDrawerRight() {
        if (!mBinding.drawer.isDrawerOpen(Gravity.RIGHT)) {
            mBinding.drawer.openDrawer(Gravity.RIGHT);
        }
    }

    private void closeDrawerLeft() {
        if (mBinding.drawer.isDrawerOpen(Gravity.LEFT)) {
            mBinding.drawer.closeDrawers();
        }
    }

    private void closeDrawerRight() {
        if (mBinding.drawer.isDrawerOpen(Gravity.RIGHT)) {
            mBinding.drawer.closeDrawers();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }

    //control for tab navigation
    @Override
    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.pushFragment(fragment);
        }
    }

    @Override
    public void popFragment() {
        if (mNavController != null) {
            mNavController.popFragment();
        }
    }


    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {
            case HOME:
                //return NewsPaperFragment.newInstance(0);
                return WelcomeHomeFragment.newInstance(0);
            case OFFER:
                return OfferFragment.newInstance(0);
            case NOTIFICATION:
                return NotificationFragment.newInstance(0);
            case USER:
                return UserFragment.newInstance(0);
        }
        throw new IllegalStateException("Need to send an index that we know");
    }

    @Override
    public void onTabTransaction(Fragment fragment, int index) {
        // If we have a backstack, show the back button
        if (getSupportActionBar() != null && mNavController != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
        }
    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
        if (getSupportActionBar() != null && mNavController != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
        }
    }
    //end


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mNavController.popFragment();
                break;
        }
        return true;
    }

    public void setTile(String title) {
        mBinding.toolBar.tvHeading.setText(title);
    }

    @Override
    public void onSubItemClicked(int parentPosition, int childPosition) {
        showToast(parentPosition+"    "+childPosition);
        closeDrawerRight();
        pushFragment(PRODUCT_SUBPRODUCT, null, R.id.container, true, true, NONE);
        //ProductSubproductFragment.newInstance(0, productList.get(adapterPosition));
    }
}
