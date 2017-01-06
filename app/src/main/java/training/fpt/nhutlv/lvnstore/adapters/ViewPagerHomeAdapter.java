package training.fpt.nhutlv.lvnstore.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import training.fpt.nhutlv.lvnstore.fragments.AboutFragment;
import training.fpt.nhutlv.lvnstore.fragments.FavouriteFragment;
import training.fpt.nhutlv.lvnstore.fragments.AppsFragment;
import training.fpt.nhutlv.lvnstore.fragments.SettingFragment;

/**
 * Created by NhutDu on 18/10/2016.
 */
public class ViewPagerHomeAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerHomeAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AppsFragment tab1 =new AppsFragment();
                return tab1;
            case 1:
                FavouriteFragment tab2 = new FavouriteFragment();
                return tab2;
            case 2:
                SettingFragment tab3 =new SettingFragment();
                return tab3;
            case 3:
                AboutFragment tab4 = new AboutFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
