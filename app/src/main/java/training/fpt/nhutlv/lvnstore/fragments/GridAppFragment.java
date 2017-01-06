package training.fpt.nhutlv.lvnstore.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

import training.fpt.nhutlv.lvnstore.DetailAppActivity;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.adapters.GridAppAdapter;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.lib.GridSpacingItemDecoration;
import training.fpt.nhutlv.lvnstore.lib.RecyclerItemClickListener;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.DataDemo;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class GridAppFragment extends Fragment {

    RecyclerView mRecyclerView;
    GridAppAdapter mAdapter;
    ArrayList<AppInfo> mApps;

    public Fragment newInstance(int idFragment){
        Fragment fragment = new GridAppFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ID",idFragment);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        switch (getArguments().getInt("ID")){
            case Constant.TOP_FREE:
                getActivity().setTitle("Top Free");
                mApps= DataDemo.getData();
                break;
            case Constant.TOP_PAID:
                getActivity().setTitle("Top Paid");
                mApps= DataDemo.getData1();
                break;
            case Constant.TOP_MOVERS_SHAKER:
                getActivity().setTitle("Movers Shaker");
                mApps= DataDemo.getData2();
                break;
            case Constant.TOP_GROSSING:
                getActivity().setTitle("Top Grossing");
                mApps= DataDemo.getData3();
                break;
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_app,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_grid);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        mAdapter = new GridAppAdapter(getActivity(),mApps);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), DetailAppActivity.class));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        mAdapter.notifyDataSetChanged();

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.app);
        item.setVisible(false);
        if(menu.findItem(R.id.list_menu)!=null){
            menu.findItem(R.id.list_menu).setVisible(false);
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
