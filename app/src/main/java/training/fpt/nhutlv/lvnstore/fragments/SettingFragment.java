package training.fpt.nhutlv.lvnstore.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.activities.SettingCategoryActivity;
import training.fpt.nhutlv.lvnstore.activities.SettingLanguageActivity;
import training.fpt.nhutlv.lvnstore.activities.SettingRateActivity;
import training.fpt.nhutlv.lvnstore.activities.SettingSortActivity;
import training.fpt.nhutlv.lvnstore.activities.SettingYearReleaseActivity;
import training.fpt.nhutlv.lvnstore.event.SettingEvent;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;

/**
 * Created by NhutDu on 18/12/2016.
 */

public class SettingFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.filter_category)
    TextView mFilterCategory;

    @BindView(R.id.filter_rate)
    TextView mFilterRate;

    @BindView(R.id.filter_year_release)
    TextView mFilterYear;

    @BindView(R.id.sort_by)
    TextView mSortBy;

    @BindView(R.id.language)
    TextView mLanguage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);
    }

    public static Fragment newInstance(){
        Fragment fragment = new SettingFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        ButterKnife.bind(this,view);
        mLanguage.setOnClickListener(this);
        mFilterCategory.setOnClickListener(this);
        mFilterRate.setOnClickListener(this);
        mFilterYear.setOnClickListener(this);
        mSortBy.setOnClickListener(this);

        switch (new PreferenceState(getActivity()).getStateFragment()){
            case Constant.TOP_FREE:
                mFilterCategory.setText(getResources().getString(R.string.top_free));
                break;
            case Constant.TOP_PAID:
                mFilterCategory.setText(getResources().getString(R.string.top_paid));
                break;
            case Constant.TOP_MOVERS_SHAKER:
                mFilterCategory.setText(getResources().getString(R.string.movers_shaker));
                break;
            case Constant.TOP_GROSSING:
                mFilterCategory.setText(getResources().getString(R.string.top_grossing));
                break;
        }

        mFilterRate.setText(String.valueOf(new PreferenceState(getActivity()).getRating()));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(menu.findItem(R.id.list_menu)!=null)
            menu.findItem(R.id.list_menu).setVisible(false);

        menu.findItem(R.id.drop_down).setVisible(false);
        menu.findItem(R.id.setting).setVisible(false);
        if(menu.findItem(R.id.drop_up)!=null)
            menu.findItem(R.id.drop_up).setVisible(false);
        if(menu.findItem(R.id.gird_menu)!=null)
            menu.findItem(R.id.gird_menu).setVisible(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.filter_category:
                startActivity(new Intent(getActivity(),SettingCategoryActivity.class));
                break;
            case R.id.filter_rate:
                startActivity(new Intent(getActivity(),SettingRateActivity.class));
                break;
            case R.id.filter_year_release:
                startActivity(new Intent(getActivity(),SettingYearReleaseActivity.class));
                break;
            case R.id.sort_by:
                startActivity(new Intent(getActivity(),SettingSortActivity.class));
                break;
            case R.id.language:
                startActivity(new Intent(getActivity(),SettingLanguageActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void eventSetting(SettingEvent settingEvent){
        switch (settingEvent.getType()){
            case PreferenceState.PREF_STATE_FRAGMENT:
                mFilterCategory.setText(settingEvent.getContent());
                break;
            case PreferenceState.PREF_RATING:
                mFilterRate.setText(settingEvent.getContent());
                break;
            case PreferenceState.PREF_YEAR:
                mFilterYear.setText(settingEvent.getContent());
                break;
            case PreferenceState.PREF_SORT:
                mSortBy.setText(settingEvent.getContent());
                break;
            case PreferenceState.PREF_LANGUAGE:
                mSortBy.setText(settingEvent.getContent());
                break;
        }
    }

}
