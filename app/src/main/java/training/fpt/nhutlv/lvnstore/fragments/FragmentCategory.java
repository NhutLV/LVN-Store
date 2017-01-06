package training.fpt.nhutlv.lvnstore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;
import training.fpt.nhutlv.lvnstore.utils.UtilsFragment;

/**
 * Created by HCD-Fresher039 on 12/21/2016.
 */

public class FragmentCategory extends Fragment {

    private String[] categories = {"Top Free", "Top Paid", "Movers Shaker", "Top Grossing"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        View viewBottom = view.findViewById(R.id.view_dismiss);
        ListView listView = (ListView) view.findViewById(R.id.list_category);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_list_category, Arrays.asList(categories));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (new PreferenceState(getActivity()).getStateShow() == Constant.GRID) {
                    switch (i) {
                        case 0:
                            new PreferenceState(getActivity()).saveStateFragment(Constant.TOP_FREE);
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new GridAppFragment().newInstance(Constant.TOP_FREE),
                                    R.id.frame);
                            break;
                        case 1:
                            new PreferenceState(getActivity()).saveStateFragment(Constant.TOP_PAID);
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new GridAppFragment().newInstance(Constant.TOP_PAID),
                                    R.id.frame);
                            break;
                        case 2:
                            new PreferenceState(getActivity()).saveStateFragment(Constant.TOP_MOVERS_SHAKER);
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new GridAppFragment().newInstance(Constant.TOP_MOVERS_SHAKER),
                                    R.id.frame);
                            break;
                        case 3:
                            new PreferenceState(getActivity()).saveStateFragment(Constant.TOP_GROSSING);
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new GridAppFragment().newInstance(Constant.TOP_GROSSING),
                                    R.id.frame);
                            break;
                    }
                } else {
                    switch (i) {
                        case 0:
                            new PreferenceState(getActivity()).saveStateFragment(Constant.TOP_FREE);
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_FREE),
                                    R.id.frame);
                            break;
                        case 1:
                            new PreferenceState(getActivity()).saveStateFragment(Constant.TOP_PAID);
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_PAID),
                                    R.id.frame);
                            break;
                        case 2:
                            new PreferenceState(getActivity()).saveStateFragment(Constant.TOP_MOVERS_SHAKER);
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_MOVERS_SHAKER),
                                    R.id.frame);
                            break;
                        case 3:
                            new PreferenceState(getActivity()).saveStateFragment(Constant.TOP_GROSSING);
                            UtilsFragment.changeFragment(getActivity().getSupportFragmentManager(),
                                    new ListAppFragment().newInstance(Constant.TOP_GROSSING),
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

