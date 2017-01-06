package training.fpt.nhutlv.lvnstore.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;

import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.adapters.RecyclerViewMoviesBoxAdapter;
import training.fpt.nhutlv.lvnstore.entities.Movies;
import training.fpt.nhutlv.lvnstore.lib.EndlessRecyclerViewScrollListener;
import training.fpt.nhutlv.lvnstore.lib.GridSpacingItemDecoration;
import training.fpt.nhutlv.lvnstore.model.service.MoviesServiceImpl;
import training.fpt.nhutlv.lvnstore.utils.Callback;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;
import training.fpt.nhutlv.lvnstore.utils.UtilsFragment;


/**
 * Created by NhutDu on 18/12/2016.
 */

public class AppsFragment extends Fragment {

    private static final String TAG = AppsFragment.class.getSimpleName();
    ArrayList<Movies> mMovies;
    MoviesServiceImpl mMoviesService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mMovies = new ArrayList<>();
        mMoviesService = new MoviesServiceImpl(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps,container,false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(new PreferenceState(getActivity()).getStateShow()== Constant.GRID){
            UtilsFragment.addFragment(getActivity().getSupportFragmentManager(),
                    new GridAppFragment().newInstance(new PreferenceState(getActivity()).getStateFragment()),
                    R.id.frame);
        }else{
            UtilsFragment.addFragment(getActivity().getSupportFragmentManager(),
                    new ListAppFragment().newInstance(new PreferenceState(getActivity()).getStateFragment()),
                    R.id.frame);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

}
