package training.fpt.nhutlv.lvnstore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.adapters.ListAppAdapter;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.lib.DividerItemDecoration;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.DataDemo;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class ListAppFragment extends Fragment{

    //region Properties
    RecyclerView mRecyclerView;
    ArrayList<AppInfo> mApps;
    ListAppAdapter mAdapter;

    //endregion

    //region instance
    public Fragment newInstance(int idFragment){
        Fragment fragment = new ListAppFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ID",idFragment);
        fragment.setArguments(bundle);
        return  fragment;
    }

    //endregion

    //region override methods
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
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_list_app,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_main);
        mAdapter = new ListAppAdapter(getActivity(),mApps);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),1));

        mAdapter.notifyDataSetChanged();

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if(menu.findItem(R.id.gird_menu)!=null)
        menu.findItem(R.id.gird_menu).setVisible(false);

    }

    //endregion
}
