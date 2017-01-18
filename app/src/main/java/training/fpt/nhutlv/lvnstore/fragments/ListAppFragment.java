package training.fpt.nhutlv.lvnstore.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.activities.DetailAppActivity;
import training.fpt.nhutlv.lvnstore.adapters.GridAppAdapter;
import training.fpt.nhutlv.lvnstore.adapters.ListAppAdapter;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.entities.RealmArrayByte;
import training.fpt.nhutlv.lvnstore.event.NumberFavourite;
import training.fpt.nhutlv.lvnstore.event.RemovePositionEvent;
import training.fpt.nhutlv.lvnstore.lib.DividerItemDecoration;
import training.fpt.nhutlv.lvnstore.lib.EndlessScrollRecyclerViewListener;
import training.fpt.nhutlv.lvnstore.lib.GridSpacingItemDecoration;
import training.fpt.nhutlv.lvnstore.lib.RecyclerItemClickListener;
import training.fpt.nhutlv.lvnstore.model.Configuration;
import training.fpt.nhutlv.lvnstore.model.service.AppInfoServiceImpl;
import training.fpt.nhutlv.lvnstore.utils.Callback;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.DataDemo;
import training.fpt.nhutlv.lvnstore.utils.UtilsImage;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class ListAppFragment extends Fragment implements ListAppAdapter.MyClickDetailLister {

    //region Properties

    RecyclerView mRecyclerView;
    ArrayList<AppInfo> mApps = new ArrayList<>();
    ListAppAdapter mAdapter;
    GridAppAdapter mAdapterGrid;
    GridLayoutManager layoutManagerGrid;
    EndlessScrollRecyclerViewListener mScrollListener;
    AppInfoServiceImpl service = new AppInfoServiceImpl(getActivity());
    AVLoadingIndicatorView avi;
    RealmList<RealmArrayByte> screenshotImage = new RealmList<>();


    int rating = 0;
    int yearRelease = 0;

    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefreshLayout;

    Realm realm = Realm.getDefaultInstance();

    //endregion

    //region instance
    public Fragment newInstance(int idFragment, int stateShow) {
        Fragment fragment = new ListAppFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ID", idFragment);
        bundle.putInt("STATE", stateShow);
        fragment.setArguments(bundle);
        return fragment;
    }

    //endregion

    //region override methods


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        rating = Integer.parseInt(pref.getString(SettingsFragment.KEY_RATE, "0"));
        yearRelease = Integer.parseInt(pref.getString(SettingsFragment.KEY_RELEASE_YEAR, "0"));
//        mApps.add(new AppInfo("com.nhut.ca","ZingMP3","Hello","",5f,120,"hello i love you"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater inflater1 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater1.inflate(R.layout.fragment_list_app, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_main);
        avi = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        Log.d("TAG MENU", getArguments().getInt("STATE") + "");

        if (getArguments().getInt("STATE") == Constant.LIST) {
            Log.d("TAG MENU", "List");
            mAdapter = new ListAppAdapter(getActivity(), mApps);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setMyClickDetailLister(this);
            switch (getArguments().getInt("ID")) {
                case Constant.TOP_FREE:
                    showDataByCategory(manager, Configuration.TOP_FREE);
                    break;
                case Constant.TOP_PAID:
                    showDataByCategory(manager, Configuration.TOP_PAID);
                    break;
                case Constant.TOP_MOVERS_SHAKER:
                    showDataByCategory(manager, Configuration.MOVERS_SHAKER);
                    break;
                case Constant.TOP_GROSSING:
                    showDataByCategory(manager, Configuration.TOP_GROSSING);
                    break;
            }

        } else {
            Log.d("TAG MENU", "Grid");
            layoutManagerGrid = new GridLayoutManager(getActivity(), 2);
            mRecyclerView.setLayoutManager(layoutManagerGrid);
            mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            mAdapterGrid = new GridAppAdapter(getActivity(), mApps);

            mRecyclerView.setAdapter(mAdapterGrid);
            switch (getArguments().getInt("ID")) {
                case Constant.TOP_FREE:
                    showDataByCategoryGrid(layoutManagerGrid, Configuration.TOP_FREE);
                    break;
                case Constant.TOP_PAID:
                    showDataByCategoryGrid(layoutManagerGrid, Configuration.TOP_PAID);
                    break;
                case Constant.TOP_MOVERS_SHAKER:
                    showDataByCategoryGrid(layoutManagerGrid, Configuration.MOVERS_SHAKER);
                    break;
                case Constant.TOP_GROSSING:
                    showDataByCategoryGrid(layoutManagerGrid, Configuration.TOP_GROSSING);
                    break;
            }
            mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), DetailAppActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("APP", mApps.get(position));
                    bundle.putString("title", mApps.get(position).getTitle());
                    bundle.putString("package_name", mApps.get(position).getPackage_name());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                @Override
                public void onLongItemClick(View view, int position) {

                }
            }));
            mAdapterGrid.notifyDataSetChanged();
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        Log.d("MENU", getArguments().getInt("STATE") + "");
        if (getArguments().getInt("STATE") == Constant.LIST) {
            if (menu.findItem(R.id.gird_menu) != null)
                menu.findItem(R.id.gird_menu).setVisible(false);
            if (menu.findItem(R.id.list_menu) != null)
                menu.findItem(R.id.list_menu).setVisible(true);
        } else {
            if (menu.findItem(R.id.list_menu) != null)
                menu.findItem(R.id.list_menu).setVisible(false);
            if (menu.findItem(R.id.gird_menu) != null)
                menu.findItem(R.id.gird_menu).setVisible(true);
        }
    }
    //endregion

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    //region load data
    private void showDataByCategory(LinearLayoutManager layoutManager, final String category) {
        if (!avi.isShown())
            avi.show();

        service.getListByCategoryName(category, 1, new Callback<ArrayList<AppInfo>>() {
            @Override
            public void onResult(ArrayList<AppInfo> appInfos) {
                if (avi.isShown())
                    avi.hide();
                new DataDemo().checkFavourite(appInfos);
                mApps.addAll(filterSetting(appInfos, yearRelease, rating));
                mAdapter.notifyDataSetChanged();
            }
        });

        mScrollListener = new EndlessScrollRecyclerViewListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                service.getListByCategoryName(category, page, new Callback<ArrayList<AppInfo>>() {
                    @Override
                    public void onResult(ArrayList<AppInfo> appInfos) {
                        new DataDemo().checkFavourite(appInfos);
                        mApps.addAll(filterSetting(appInfos, yearRelease, rating));
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        };
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mApps.clear();

                service.getListByCategoryName(category, 1, new Callback<ArrayList<AppInfo>>() {
                    @Override
                    public void onResult(ArrayList<AppInfo> appInfos) {
                        mApps.addAll(filterSetting(new DataDemo().checkFavourite(appInfos),yearRelease,rating));
                        mAdapter.notifyDataSetChanged();
                        (new Handler()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRefreshLayout.setRefreshing(false);
                            }
                        }, 3000);
                    }
                });
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    private void showDataByCategoryGrid(GridLayoutManager layoutManager, final String category) {
        if (!avi.isShown())
            avi.show();
        service.getListByCategoryName(category, 1, new Callback<ArrayList<AppInfo>>() {
            @Override
            public void onResult(ArrayList<AppInfo> appInfos) {
                if (avi.isShown())
                    avi.hide();
                new DataDemo().checkFavourite(appInfos);
                mApps.addAll(filterSetting(appInfos, yearRelease, rating));
                mAdapterGrid.notifyDataSetChanged();
            }
        });

        mScrollListener = new EndlessScrollRecyclerViewListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                service.getListByCategoryName(category, page, new Callback<ArrayList<AppInfo>>() {
                    @Override
                    public void onResult(ArrayList<AppInfo> appInfos) {
                        new DataDemo().checkFavourite(appInfos);
                        mApps.addAll(filterSetting(appInfos, yearRelease, rating));
                        mAdapterGrid.notifyDataSetChanged();
                    }
                });
            }
        };

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mApps.clear();

                service.getListByCategoryName(category, 1, new Callback<ArrayList<AppInfo>>() {
                    @Override
                    public void onResult(ArrayList<AppInfo> appInfos) {
                        mApps.addAll(filterSetting(new DataDemo().checkFavourite(appInfos),yearRelease,rating));
                        mAdapter.notifyDataSetChanged();
                        (new Handler()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRefreshLayout.setRefreshing(false);
                            }
                        }, 3000);
                    }
                });
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });
        mRecyclerView.addOnScrollListener(mScrollListener);
    }
    //endregion

    //onclick item recycler view
    @Override
    public void onCLickItem(int position) {
        Intent intent = new Intent(getActivity(), DetailAppActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("APP", mApps.get(position));
        bundle.putString("title", mApps.get(position).getTitle());
        bundle.putString("package_name", mApps.get(position).getPackage_name());
        intent.putExtras(bundle);
        startActivity(intent);
    }


    //onclick favourite
    @Override
    public void onClickFavourite(CheckBox checkBox, int position) {
        AppInfo app = mApps.get(position);
        if (checkBox.isChecked()) {
            app.setFavourite(true);
            app.setImageIcon(UtilsImage.getImageFromUrl(app.getIcon()));
//            for(int i =0;i<app.getScreenshots().size();i++){
//               new SaveListImage().execute(app.getScreenshots().get(i).toString());
//            }
//            app.setScreenShotImage(screenshotImage);
//
//            for (RealmArrayByte a :screenshotImage){
//                Log.d("CCC",a.toString());
//            }
            realm.beginTransaction();
            realm.copyToRealm(app);
            realm.commitTransaction();
            RealmResults<AppInfo> results = realm.where(AppInfo.class).findAll();
            EventBus.getDefault().postSticky(new RemovePositionEvent(position, true, app));
            EventBus.getDefault().postSticky(new NumberFavourite(results.size()));

        } else {
            realm.beginTransaction();
            app.setFavourite(false);
            RealmResults<AppInfo> results = realm.where(AppInfo.class).equalTo("package_name", app.getPackage_name()).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
            RealmResults<AppInfo> results1 = realm.where(AppInfo.class).findAll();
            EventBus.getDefault().postSticky(new NumberFavourite(results1.size()));
            EventBus.getDefault().postSticky(new RemovePositionEvent(position, false, Constant.TAB_LIST, app));
        }
    }

    //filter data from setting
    private ArrayList<AppInfo> filterSetting(ArrayList<AppInfo> list, int year, int rating) {
        ArrayList<AppInfo> listResult = new ArrayList<>();
        int i=0;
        for (AppInfo app : list) {
//            int releaseYear = Integer.parseInt(app.getCreated().split("-")[0]);
            if (app.getRating() >= rating ) {
                listResult.add(app);
            }
        }
        return listResult;
    }

    class SaveListImage extends AsyncTask<String, Void, RealmList<RealmArrayByte>> {

        @Override
        protected RealmList<RealmArrayByte> doInBackground(String... strings) {
            try {
                URL imageurl = new URL(strings[0]);
                Bitmap bitmap = BitmapFactory.decodeStream(imageurl.openConnection().getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                screenshotImage.add(new RealmArrayByte(baos.toByteArray()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return screenshotImage;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //receive event bus
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventList(RemovePositionEvent positionEvent) {
        if (positionEvent.isCheck()) {
            mApps.get(mApps.indexOf(positionEvent.getAppInfo())).setFavourite(true);
            mAdapter.notifyDataSetChanged();
        } else {
            if (positionEvent.getTab() == Constant.TAB_LIST) {
                mApps.get(mApps.indexOf(positionEvent.getAppInfo())).setFavourite(false);
                mAdapter.notifyDataSetChanged();
            } else if (positionEvent.getTab() == Constant.TAB_FAROURITE) {
                new DataDemo().checkFavouriteList(mApps);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
