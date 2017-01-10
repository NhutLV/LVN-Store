package training.fpt.nhutlv.lvnstore.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import training.fpt.nhutlv.lvnstore.fragments.AboutFragment;
import training.fpt.nhutlv.lvnstore.fragments.FavouriteFragment;
import training.fpt.nhutlv.lvnstore.fragments.AppsFragment;
import training.fpt.nhutlv.lvnstore.fragments.SettingsFragment;

/**
 * Created by NhutDu on 18/10/2016.
 */
public class ViewPagerHomeAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public ViewPagerHomeAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new AppsFragment();
            case 1:
                return new FavouriteFragment();
            case 2:
                return new SettingsFragment();
            case 3:
                return  new AboutFragment();
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
