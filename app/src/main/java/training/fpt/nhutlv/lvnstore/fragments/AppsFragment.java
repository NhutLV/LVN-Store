package training.fpt.nhutlv.lvnstore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;
import training.fpt.nhutlv.lvnstore.utils.UtilsFragment;


/**
 * Created by NhutDu on 18/12/2016.
 */

public class AppsFragment extends Fragment {

    private static final String TAG = AppsFragment.class.getSimpleName();

    public static Fragment newInstance() {
        Fragment fragment = new AppsFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps,container,false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            UtilsFragment.addFragment(getActivity().getSupportFragmentManager(),
                    new ListAppFragment().newInstance(new PreferenceState(getActivity()).getStateFragment(),new PreferenceState(getActivity()).getStateShow()),
                    R.id.frame);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

}