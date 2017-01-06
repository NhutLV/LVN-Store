package training.fpt.nhutlv.lvnstore.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import training.fpt.nhutlv.lvnstore.R;

/**
 * Created by NhutDu on 18/12/2016.
 */

public class FavouriteFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static Fragment newInstance(){
        Fragment fragment = new FavouriteFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite,container,false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        MenuItem item = menu.findItem(R.id.list_menu);

        if(item!=null)
            item.setVisible(false);
        menu.findItem(R.id.drop_down).setVisible(false);
        menu.findItem(R.id.favorite).setVisible(false);
        if(menu.findItem(R.id.drop_up)!=null)
        menu.findItem(R.id.drop_up).setVisible(false);
        if(menu.findItem(R.id.gird_menu)!=null)
        menu.findItem(R.id.gird_menu).setVisible(false);


        inflater.inflate(R.menu.menu_favourite,menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
    }
}
