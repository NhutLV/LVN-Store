package training.fpt.nhutlv.lvnstore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.event.SettingEvent;
import training.fpt.nhutlv.lvnstore.utils.Constant;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;

/**
 * Created by NhutDu on 01/01/2017.
 */

public class SettingCategoryActivity extends AppCompatActivity {

    @BindView(R.id.cancel_category)
    TextView mCancel;

    @BindView(R.id.yes_category)
    TextView mYes;

    @BindView(R.id.rdgCategory)
    RadioGroup mRadioGroup;

    @BindView(R.id.rbTopFree)
    RadioButton mRadioFree;

    @BindView(R.id.rbtopPaid)
    RadioButton mRadioPaid;

    @BindView(R.id.rbMoverShaker)
    RadioButton mRadioShaker;

    @BindView(R.id.rbTopGrossing)
    RadioButton mRadioGrossing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_category);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Category");
        setSupportActionBar(toolbar);

        int index = new PreferenceState(this).getStateFragment();

        switch (index){
            case Constant.TOP_FREE:
                mRadioFree.setChecked(true);
                break;
            case Constant.TOP_PAID:
                mRadioPaid.setChecked(true);
                break;
            case Constant.TOP_MOVERS_SHAKER:
                mRadioShaker.setChecked(true);
                break;
            case Constant.TOP_GROSSING:
                mRadioGrossing.setChecked(true);
                break;
        }

        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selection = mRadioGroup.getCheckedRadioButtonId();
                String message="";
                switch (selection){
                    case R.id.rbTopFree:
                        new PreferenceState(SettingCategoryActivity.this).saveStateFragment(Constant.TOP_FREE);
                        message = getResources().getString(R.string.top_free);
                        break;
                    case R.id.rbtopPaid:
                        new PreferenceState(SettingCategoryActivity.this).saveStateFragment(Constant.TOP_PAID);
                        message = getResources().getString(R.string.top_paid);
                        break;
                    case R.id.rbMoverShaker:
                        new PreferenceState(SettingCategoryActivity.this).saveStateFragment(Constant.TOP_MOVERS_SHAKER);
                        message = getResources().getString(R.string.movers_shaker);
                        break;
                    case R.id.rbTopGrossing:
                        new PreferenceState(SettingCategoryActivity.this).saveStateFragment(Constant.TOP_GROSSING);
                        message = getResources().getString(R.string.top_grossing);
                        break;
                }

                EventBus.getDefault().postSticky(new SettingEvent(PreferenceState.PREF_STATE_FRAGMENT,message));

                finish();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
