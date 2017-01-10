package training.fpt.nhutlv.lvnstore.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.activities.DetailAppActivity;
import training.fpt.nhutlv.lvnstore.adapters.ListAppAdapter;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.event.RemovePositionEvent;
import training.fpt.nhutlv.lvnstore.lib.DividerItemDecoration;

/**
 * Created by NhutDu on 18/12/2016.
 */

public class FavouriteFragment extends Fragment implements SearchView.OnQueryTextListener,ListAppAdapter.MyClickDetailLister {

    RecyclerView mRecyclerView;
    ArrayList<AppInfo> mApps = new ArrayList<>();
    ListAppAdapter mAdapter;
    Realm realm = Realm.getDefaultInstance();
    SearchView searchView;
    ArrayList<AppInfo> mTemp = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);
        RealmResults<AppInfo> apps = realm.where(AppInfo.class).findAll();
        mApps.addAll(apps);
        mTemp.addAll(apps);
    }

    public static Fragment newInstance() {
        Fragment fragment = new FavouriteFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_favourite);
        mAdapter = new ListAppAdapter(getActivity(), mApps);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        mAdapter.setMyClickDetailLister(this);
        mAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.findItem(R.id.list_menu);

        if (item != null)
            item.setVisible(false);
        menu.findItem(R.id.drop_down).setVisible(false);
        menu.findItem(R.id.favorite).setVisible(false);
        if (menu.findItem(R.id.drop_up) != null)
            menu.findItem(R.id.drop_up).setVisible(false);
        if (menu.findItem(R.id.gird_menu) != null)
            menu.findItem(R.id.gird_menu).setVisible(false);


        inflater.inflate(R.menu.menu_favourite, menu);

        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                     // Do something when collapsed
                        mAdapter.setFilter(mApps);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true;
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventFavourite(RemovePositionEvent positionEvent) {
        if (!positionEvent.isCheck()) {
            mApps.remove(positionEvent.getPosition());
        } else {
            mApps.add(positionEvent.getAppInfo());
        }
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //search view
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<AppInfo> filteredModelList = filter(mTemp, newText);
        mApps = filteredModelList;
        mAdapter.setFilter(filteredModelList);
        return true;
    }

    private ArrayList<AppInfo> filter(List<AppInfo> models, String query) {
        query = query.toLowerCase();
        final ArrayList<AppInfo> filteredModelList = new ArrayList<>();
        for (AppInfo model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onCLickItem(int position) {
        Intent intent = new Intent(getActivity(),DetailAppActivity.class);
        intent.putExtra("package_name",mApps.get(position).getPackage_name());
        startActivity(intent);
    }
}
