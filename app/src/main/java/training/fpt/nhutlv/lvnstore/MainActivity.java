package training.fpt.nhutlv.lvnstore;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import training.fpt.nhutlv.lvnstore.adapters.ViewPagerHomeAdapter;
import training.fpt.nhutlv.lvnstore.entities.TypeImage;
import training.fpt.nhutlv.lvnstore.fragments.FragmentCategory;
import training.fpt.nhutlv.lvnstore.fragments.GridAppFragment;
import training.fpt.nhutlv.lvnstore.fragments.ListAppFragment;
import training.fpt.nhutlv.lvnstore.model.service.TypeImageServiceImpl;
import training.fpt.nhutlv.lvnstore.utils.Callback;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;
import training.fpt.nhutlv.lvnstore.utils.UtilsFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    ViewPagerHomeAdapter mAdapter;
    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAdapter = new ViewPagerHomeAdapter(getSupportFragmentManager(),4);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(getResources().getString(R.string.tab_app)).setIcon(R.drawable.ic_home_white_24dp);
        mTabLayout.getTabAt(1).setText(getResources().getString(R.string.tab_favourite)).setIcon(R.drawable.ic_favorite_white_24dp);
        mTabLayout.getTabAt(2).setText(getResources().getString(R.string.tab_setting)).setIcon(R.drawable.ic_settings_white_24dp);
        mTabLayout.getTabAt(3).setText(getResources().getString(R.string.tab_about)).setIcon(R.drawable.ic_info_white_24dp);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.list_menu:
                if(getSupportFragmentManager().getBackStackEntryCount() >0)
                    getSupportFragmentManager().popBackStack();
                new PreferenceState(this).saveStateShow(Constant.GRID);
                UtilsFragment.changeFragment(getSupportFragmentManager(),
                        new GridAppFragment().newInstance(new PreferenceState(this).getStateFragment()),
                        R.id.frame);
                break;
            case R.id.gird_menu:
                if(getSupportFragmentManager().getBackStackEntryCount() >0)
                   getSupportFragmentManager().popBackStack();
                new PreferenceState(this).saveStateShow(Constant.LIST);
                UtilsFragment.changeFragment(getSupportFragmentManager(),
                        new ListAppFragment().newInstance(new PreferenceState(this).getStateFragment()),
                        R.id.frame);
                break;
            case R.id.drop_down:
                if(getSupportFragmentManager().getBackStackEntryCount() >0)
                    getSupportFragmentManager().popBackStack();
                Fragment childFragment2 = new FragmentCategory();
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.setCustomAnimations(R.anim.enter_from_bottom,R.anim.enter_from_top);
                transaction2.addToBackStack("FragmentCategory");
                transaction2.add(R.id.frame, childFragment2,"FragmentCategory");
                transaction2.commit();
                break;
            case R.id.drop_up:
                if(getSupportFragmentManager().getBackStackEntryCount() >0)
                    getSupportFragmentManager().popBackStack();
                break;
            case R.id.favorite:
                if(getSupportFragmentManager().getBackStackEntryCount() >0)
                   getSupportFragmentManager().popBackStack();
                mTabLayout.setScrollPosition(1,0f,true);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.setting:
                if(getSupportFragmentManager().getBackStackEntryCount() >0)
                    getSupportFragmentManager().popBackStack();
                mTabLayout.setScrollPosition(2,0f,true);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.about:
                if(getSupportFragmentManager().getBackStackEntryCount() >0)
                    getSupportFragmentManager().popBackStack();
                mTabLayout.setScrollPosition(3,0f,true);
                mViewPager.setCurrentItem(3);
                break;
            case R.id.app:
                if(getSupportFragmentManager().getBackStackEntryCount() >0)
                   getSupportFragmentManager().popBackStack();

                mTabLayout.setScrollPosition(0,0f,true);
                mViewPager.setCurrentItem(0);

                break;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            new TypeImageServiceImpl(this).getImageURL(new Callback<TypeImage>() {

                @Override
                public void onResult(TypeImage typeImage) {
                }
            });

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private int getActionBarHeight() {
        int actionBarHeight = toolbar.getHeight();
        if (actionBarHeight != 0)
            return actionBarHeight;
        final TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        } else if (getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        return actionBarHeight;
    }

    private void showSnackBar(){
        if(!isNetworkAvailable()){
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Not network connect", 10000)
                    .setAction("TRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            recreate();
                        }
                    });
            snackbar.setActionTextColor(Color.YELLOW);

            View sbView = snackbar.getView();
            CoordinatorLayout.LayoutParams params=(CoordinatorLayout.LayoutParams)sbView.getLayoutParams();
            params.gravity = Gravity.TOP;
            params.topMargin= 100;
            sbView.setLayoutParams(params);

            sbView.setBackgroundColor(getResources().getColor(R.color.background_snackbar));
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
    }
}
