package training.fpt.nhutlv.lvnstore.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.activities.DetailAppActivity;
import training.fpt.nhutlv.lvnstore.adapters.GridAppAdapter;
import training.fpt.nhutlv.lvnstore.adapters.ListAppAdapter;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.event.RemovePositionEvent;
import training.fpt.nhutlv.lvnstore.lib.DividerItemDecoration;
import training.fpt.nhutlv.lvnstore.lib.EndlessRecyclerViewScrollListener;
import training.fpt.nhutlv.lvnstore.lib.EndlessScrollRecyclerViewListener;
import training.fpt.nhutlv.lvnstore.lib.GridSpacingItemDecoration;
import training.fpt.nhutlv.lvnstore.lib.RecyclerItemClickListener;
import training.fpt.nhutlv.lvnstore.model.Configuration;
import training.fpt.nhutlv.lvnstore.model.service.AppInfoServiceImpl;
import training.fpt.nhutlv.lvnstore.utils.Callback;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.DataDemo;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;

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

    //endregion

    //region instance
    public Fragment newInstance(int idFragment,int stateShow){
        Fragment fragment = new ListAppFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ID",idFragment);
        bundle.putInt("STATE",stateShow);
        fragment.setArguments(bundle);
        return  fragment;
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
//        mApps.add(new AppInfo("com.nhut.ca","ZingMP3","Hello","",5f,120,"hello i love you"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_list_app,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_main);
        avi = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        if(getArguments().getInt("STATE")==Constant.LIST){
            mAdapter = new ListAppAdapter(getActivity(),mApps);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),1));
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setMyClickDetailLister(this);
            switch (getArguments().getInt("ID")){
                case Constant.TOP_FREE:
                    showDataByCategory(manager,Configuration.TOP_FREE);
                    break;
                case Constant.TOP_PAID:
                    showDataByCategory(manager,Configuration.TOP_PAID);
                    break;
                case Constant.TOP_MOVERS_SHAKER:
                    showDataByCategory(manager,Configuration.MOVERS_SHAKER);
                    break;
                case Constant.TOP_GROSSING:
                    showDataByCategory(manager,Configuration.TOP_GROSSING);
                    break;
            }

        }else{
            layoutManagerGrid = new GridLayoutManager(getActivity(),2);
            mRecyclerView.setLayoutManager(layoutManagerGrid);
            mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            mAdapterGrid = new GridAppAdapter(getActivity(),mApps);

            mRecyclerView.setAdapter(mAdapterGrid);
            switch (getArguments().getInt("ID")){
                case Constant.TOP_FREE:
                    showDataByCategoryGrid(layoutManagerGrid,Configuration.TOP_FREE);
                    break;
                case Constant.TOP_PAID:
                    showDataByCategoryGrid(layoutManagerGrid,Configuration.TOP_PAID);
                    break;
                case Constant.TOP_MOVERS_SHAKER:
                    showDataByCategoryGrid(layoutManagerGrid,Configuration.MOVERS_SHAKER);
                    break;
                case Constant.TOP_GROSSING:
                    showDataByCategoryGrid(layoutManagerGrid,Configuration.TOP_GROSSING);
                    break;
            }
            mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    startActivity(new Intent(getActivity(), DetailAppActivity.class));
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
        if(new PreferenceState(getActivity()).getStateShow()==Constant.LIST){
            if(menu.findItem(R.id.gird_menu)!=null)
                menu.findItem(R.id.gird_menu).setVisible(false);
        }else{
            if(menu.findItem(R.id.list_menu)!=null)
                menu.findItem(R.id.list_menu).setVisible(false);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void eventList(RemovePositionEvent positionEvent){
        if(positionEvent.isCheck()){
            Log.d("TAG","True List");
            mApps.get(positionEvent.getPosition()).setFavourite(true);
        }else{
            Log.d("TAG","False List");
            mApps.get(mApps.indexOf(positionEvent.getAppInfo())).setFavourite(false);
        }
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    //endregion

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void showDataByCategory(LinearLayoutManager layoutManager, final String category){
        if(!avi.isShown())
            avi.show();

        service.getListByCategoryName(category,1,new Callback<ArrayList<AppInfo>>() {
            @Override
            public void onResult(ArrayList<AppInfo> movies) {
                if(avi.isShown())
                    avi.hide();
                new DataDemo().checkFavourite(movies);
                mApps.addAll(movies);
                mAdapter.notifyDataSetChanged();
            }
        });

        mScrollListener = new EndlessScrollRecyclerViewListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                service.getListByCategoryName(category,page,new Callback<ArrayList<AppInfo>>() {
                    @Override
                    public void onResult(ArrayList<AppInfo> movies) {
                        new DataDemo().checkFavourite(movies);
                        mApps.addAll(movies);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    private void showDataByCategoryGrid(GridLayoutManager layoutManager, final String category){
        if(!avi.isShown())
            avi.show();

        service.getListByCategoryName(category,1,new Callback<ArrayList<AppInfo>>() {
            @Override
            public void onResult(ArrayList<AppInfo> movies) {
                if(avi.isShown())
                    avi.hide();
                new DataDemo().checkFavourite(movies);
                mApps.addAll(movies);
                mAdapterGrid.notifyDataSetChanged();
            }
        });

        mScrollListener = new EndlessScrollRecyclerViewListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                service.getListByCategoryName(category,page,new Callback<ArrayList<AppInfo>>() {
                    @Override
                    public void onResult(ArrayList<AppInfo> movies) {
                        new DataDemo().checkFavourite(movies);
                        mApps.addAll(movies);
                        mAdapterGrid.notifyDataSetChanged();
                    }
                });
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    @Override
    public void onCLickItem(int position) {
        Intent intent = new Intent(getActivity(),DetailAppActivity.class);
        intent.putExtra("package_name",mApps.get(position).getPackage_name());
        startActivity(intent);
    }
}
