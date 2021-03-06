package training.fpt.nhutlv.lvnstore.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

import training.fpt.nhutlv.lvnstore.activities.MainActivity;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;
import training.fpt.nhutlv.lvnstore.utils.StateShow;
import training.fpt.nhutlv.lvnstore.utils.UtilsFragment;

/**
 * Created by HCD-Fresher039 on 12/21/2016.
 */

public class FragmentCategory extends Fragment {

    MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        String[] categories = getActivity().getResources().getStringArray(R.array.list_category);
        View viewBottom = view.findViewById(R.id.view_dismiss);
        ListView listView = (ListView) view.findViewById(R.id.list_category);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_list_category, Arrays.asList(categories));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (StateShow.getStateShow() == Constant.GRID) {
                    switch (i) {
                        case Constant.TOP_FREE:
                            StateShow.setCategory(Constant.TOP_FREE);
                            mainActivity.setTitle(getResources().getString(R.string.top_free));
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_FREE,Constant.GRID),
                                    R.id.frame);
                            break;
                        case Constant.TOP_PAID:
                            StateShow.setCategory(Constant.TOP_PAID);
                            mainActivity.setTitle(getResources().getString(R.string.top_paid));
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_PAID,Constant.GRID),
                                    R.id.frame);
                            break;
                        case Constant.TOP_MOVERS_SHAKER:
                            StateShow.setCategory(Constant.TOP_MOVERS_SHAKER);
                            mainActivity.setTitle(getResources().getString(R.string.movers_shaker));
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_MOVERS_SHAKER,Constant.GRID),
                                    R.id.frame);
                            break;
                        case Constant.TOP_GROSSING:
                            StateShow.setCategory(Constant.TOP_GROSSING);
                            mainActivity.setTitle(getResources().getString(R.string.top_grossing));
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_GROSSING,Constant.GRID),
                                    R.id.frame);
                            break;
                    }
                } else {
                    switch (i) {
                        case Constant.TOP_FREE:
                            StateShow.setCategory(Constant.TOP_FREE);
                            mainActivity.setTitle(getResources().getString(R.string.top_free));
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_FREE,Constant.LIST),
                                    R.id.frame);
                            break;
                        case Constant.TOP_PAID:
                            StateShow.setCategory(Constant.TOP_PAID);
                            mainActivity.setTitle(getResources().getString(R.string.top_paid));
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_PAID,Constant.LIST),
                                    R.id.frame);
                            break;
                        case Constant.TOP_MOVERS_SHAKER:
                            StateShow.setCategory(Constant.TOP_MOVERS_SHAKER);
                            mainActivity.setTitle(getResources().getString(R.string.movers_shaker));
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_MOVERS_SHAKER,Constant.LIST),
                                    R.id.frame);
                            break;
                        case Constant.TOP_GROSSING:
                            StateShow.setCategory(Constant.TOP_GROSSING);
                            mainActivity.setTitle(getResources().getString(R.string.top_grossing));
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_GROSSING,Constant.LIST),
                                    R.id.frame);
                            break;
                    }
                }
            }
        });

        viewBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null && getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0)
                    getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.findItem(R.id.drop_down).setVisible(false);
        inflater.inflate(R.menu.menu_fragment_category, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}

