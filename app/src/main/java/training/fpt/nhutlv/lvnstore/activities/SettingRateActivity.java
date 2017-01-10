package training.fpt.nhutlv.lvnstore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.event.SettingEvent;
import training.fpt.nhutlv.lvnstore.utils.PreferenceState;

/**
 * Created by NhutDu on 01/01/2017.
 */

public class SettingRateActivity extends AppCompatActivity {

    @BindView(R.id.cancel_rate)
    TextView mCancel;

    @BindView(R.id.yes_rate)
    TextView mYes;

    @BindView(R.id.rate)
    SeekBar mRating;

    @BindView(R.id.number_select_rating)
    TextView mNumberRating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_rate);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("App with Rate from");
        setSupportActionBar(toolbar);

        mRating.setProgress(new PreferenceState(this).getRating());
        Log.d("RATINGGG",new PreferenceState(this).getRating()+"");
        mNumberRating.setText("Rating : "+mRating.getProgress());

        mRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mNumberRating.setText("Rating : "+mRating.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RATING",mRating.getProgress()+"");
                new PreferenceState(SettingRateActivity.this).saveRating(mRating.getProgress());
                EventBus.getDefault().postSticky(new SettingEvent(PreferenceState.PREF_RATING,String.valueOf(mRating.getProgress())));
                finish();
            }
        });

    }
}
