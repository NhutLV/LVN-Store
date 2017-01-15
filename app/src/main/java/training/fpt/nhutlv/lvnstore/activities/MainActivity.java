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
import android.widget.FrameLayout;
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
import training.fpt.nhutlv.lvnstore.MyApplication;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.adapters.ViewPagerHomeAdapter;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.event.NumberFavourite;
import training.fpt.nhutlv.lvnstore.fragments.FragmentCategory;
import training.fpt.nhutlv.lvnstore.fragments.ListAppFragment;
import training.fpt.nhutlv.lvnstore.fragments.SettingsFragment;
import training.fpt.nhutlv.lvnstore.model.service.AppInfoServiceImpl;
import training.fpt.nhutlv.lvnstore.utils.Callback;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.NetworkChangeReceiver;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;
import training.fpt.nhutlv.lvnstore.utils.StateShow;
import training.fpt.nhutlv.lvnstore.utils.UtilsFragment;
import training.fpt.nhutlv.lvnstore.utils.segmented.SettingLanguage;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,NetworkChangeReceiver.ConnectivityReceiverListener {

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

    @BindView(R.id.connect)
    TextView mMessage;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.retry)
    TextView mRetry;

    SharedPreferences pref;
    private int category = 0;
    private int rate = 0;
    private int year = 0;
    private int sort = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
//        new PreferenceState(this).setDefault();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
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

        checkConnection();

//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mAdapter = new ViewPagerHomeAdapter(getSupportFragmentManager(),4);

        setDefaultSettingValues();
        checkChangeSettingAndReloadData(savedInstanceState);
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

        Log.d("CCCCCCCC SSSS",new PreferenceState(this).getStateFragment()+"");
        Log.d("CCCCCCCC SSSS",new PreferenceState(this).getStateShow()+"");

        StateShow.setmCategory(new PreferenceState(this).getStateFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
        checkConnection();
//        new SettingLanguage(this).setLanguage("vi");
//        recreate();
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

        if(getSupportFragmentManager().getBackStackEntryCount() >0)
            getSupportFragmentManager().popBackStack();

        switch (item.getItemId()){
            case R.id.list_menu:
                StateShow.setStateShow(Constant.GRID);
                UtilsFragment.changeFragment(getSupportFragmentManager(),
                        new ListAppFragment().newInstance(StateShow.getmCategory(), Constant.GRID),
                        R.id.frame);
                break;
            case R.id.gird_menu:
                StateShow.setStateShow(Constant.LIST);
                UtilsFragment.changeFragment(getSupportFragmentManager(),
                        new ListAppFragment().newInstance(StateShow.getmCategory(),Constant.LIST),
                        R.id.frame);
                break;
            case R.id.drop_down:
                Fragment childFragment2 = new FragmentCategory();
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.setCustomAnimations(R.anim.enter_from_bottom,R.anim.enter_from_top);
                transaction2.addToBackStack("FragmentCategory");
                transaction2.add(R.id.frame, childFragment2,"FragmentCategory");
                transaction2.commit();
                break;
            case R.id.drop_up:

                break;
            case R.id.favorite:
                mTabLayout.setScrollPosition(1,0f,true);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.setting:
                mTabLayout.setScrollPosition(2,0f,true);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.about:
                mTabLayout.setScrollPosition(3,0f,true);
                mViewPager.setCurrentItem(3);
                break;
            case R.id.app:
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

    private void checkConnection() {
        boolean isConnected = NetworkChangeReceiver.isConnected();
        showMessageConnection(isConnected);
    }

    private void showMessageConnection(final boolean isConnected) {
        String message;
        if (!isConnected) {
            message = "Sorry! Not connected to internet";
            mMessage.setVisibility(View.VISIBLE);
            mRetry.setVisibility(View.VISIBLE);
            mMessage.setText(message);
            mRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TAGGG","CLICK");
                   checkConnection();
                    recreate();
                }
            });
        }else{
            mMessage.setVisibility(View.GONE);
            mRetry.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showMessageConnection(isConnected);
    }

    private void setDefaultSettingValues() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        for (String key : SettingsFragment.listKeys) {
            if(!pref.contains(key)) {
                editor.putString(key, "0");
            }
        }
        editor.commit();
    }

    public void checkChangeSettingAndReloadData(Bundle savedInstanceState) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();

        category = Integer.parseInt(pref.getString(SettingsFragment.KEY_CATEGORY, "0"));
        rate = Integer.parseInt(pref.getString(SettingsFragment.KEY_RATE, "0"));
        year = Integer.parseInt(pref.getString(SettingsFragment.KEY_RELEASE_YEAR, "0"));
        sort = Integer.parseInt(pref.getString(SettingsFragment.KEY_SORT, "0"));

        // Check change config rate
        boolean isCategoryChange = pref.getBoolean(SettingsFragment.KEY_CATEGORY + SettingsFragment.IS_CHANGE, false);
        if(isCategoryChange) {
            editor.putBoolean(SettingsFragment.KEY_CATEGORY + SettingsFragment.IS_CHANGE, false);
        }
        boolean isRateChange = pref.getBoolean(SettingsFragment.KEY_RATE + SettingsFragment.IS_CHANGE, false);
        if(isRateChange) {
            rate = Integer.parseInt(pref.getString(SettingsFragment.KEY_RATE, "0"));
            editor.putBoolean(SettingsFragment.KEY_RATE + SettingsFragment.IS_CHANGE, false);
        }
        boolean isYearChange = pref.getBoolean(SettingsFragment.KEY_RELEASE_YEAR + SettingsFragment.IS_CHANGE, false);
        if(isYearChange) {
            year = Integer.parseInt(pref.getString(SettingsFragment.KEY_RELEASE_YEAR, "0"));
            editor.putBoolean(SettingsFragment.KEY_RELEASE_YEAR + SettingsFragment.IS_CHANGE, false);
        }
        boolean isSortChange = pref.getBoolean(SettingsFragment.KEY_SORT + SettingsFragment.IS_CHANGE, false);
        if(isSortChange) {
            sort = Integer.parseInt(pref.getString(SettingsFragment.KEY_SORT, "0"));
            editor.putBoolean(SettingsFragment.KEY_SORT + SettingsFragment.IS_CHANGE, false);
        }

        editor.commit();
    }
}
