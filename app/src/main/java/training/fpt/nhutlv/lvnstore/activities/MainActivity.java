package training.fpt.nhutlv.lvnstore.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.adapters.ViewPagerHomeAdapter;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.event.NumberFavourite;
import training.fpt.nhutlv.lvnstore.fragments.FragmentCategory;
import training.fpt.nhutlv.lvnstore.fragments.ListAppFragment;
import training.fpt.nhutlv.lvnstore.model.service.AppInfoServiceImpl;
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
    Toolbar mToolbar;
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.number_favourite)
    TextView mNumberFavourite;

    @BindView(R.id.image_avatar)
    CircleImageView mAvatar;

    @BindView(R.id.edit_profile)
    Button mEditProfile;

    TextView mTitle;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        new PreferenceState(this).setDefault();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mTitle = (TextView) mToolbar.findViewById(R.id.title);
        int index = new PreferenceState(MainActivity.this).getStateFragment();
        String [] categories = getResources().getStringArray(R.array.list_category);
        mTitle.setText(categories[index]);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<AppInfo> results = realm.where(AppInfo.class).findAll();
        mNumberFavourite.setText(String.valueOf(results.size()));

        mEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });

        Picasso.with(this).load("http://abc/image.jpg").placeholder(R.drawable.image).into(mAvatar);

//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        mAdapter = new ViewPagerHomeAdapter(getSupportFragmentManager(),4);

//        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        mAdapter.addFragment(AppsFragment.newInstance(),getResources().getString(R.string.tab_app));
//        mAdapter.addFragment(FavouriteFragment.newInstance(),getResources().getString(R.string.tab_favourite));
//        mAdapter.addFragment(SettingFragment.newInstance(),getResources().getString(R.string.tab_setting));
//        mAdapter.addFragment(AboutFragment.newInstance(),getResources().getString(R.string.tab_about));
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
                switch (tab.getPosition()){
                    case 0:
                        int index = new PreferenceState(MainActivity.this).getStateFragment();
                        String [] categories = getResources().getStringArray(R.array.list_category);
                        mTitle.setText(categories[index]);
                        break;
                    case 1:
                        mTitle.setText(getResources().getString(R.string.tab_favourite));
                        break;
                    case 2:
                        mTitle.setText(getResources().getString(R.string.tab_setting));
                        break;
                    case 3:
                        mTitle.setText(getResources().getString(R.string.tab_about));
                        break;
                }
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
                UtilsFragment.changeFragment(getSupportFragmentManager(),
                        new ListAppFragment().newInstance(new PreferenceState(this).getStateFragment(),new PreferenceState(this).getStateShow()),
                        R.id.frame);
                break;
            case R.id.gird_menu:
                if(getSupportFragmentManager().getBackStackEntryCount() >0)
                   getSupportFragmentManager().popBackStack();
                UtilsFragment.changeFragment(getSupportFragmentManager(),
                        new ListAppFragment().newInstance(new PreferenceState(this).getStateFragment(),new PreferenceState(this).getStateShow()),
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
            new AppInfoServiceImpl(this).getListByCategoryName("topselling_free", 1, new Callback<ArrayList<AppInfo>>() {
                @Override
                public void onResult(ArrayList<AppInfo> appInfos) {
                    Log.d("TAG AppInfo",appInfos.size()+"");
                }
            });
        } else if (id == R.id.nav_gallery) {
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
        int actionBarHeight = mToolbar.getHeight();
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
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void event(NumberFavourite numberFavourite){
        mNumberFavourite.setText(String.valueOf(numberFavourite.getNumberFavourite()));
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void setTitle(String s){
        mTitle.setText(s);
    }
}
