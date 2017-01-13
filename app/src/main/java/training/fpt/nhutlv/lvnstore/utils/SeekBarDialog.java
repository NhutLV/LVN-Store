package training.fpt.nhutlv.lvnstore.utils;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.fragments.SettingsFragment;

/**
 * Created by LamNT17 on 12/1/2015.
 */
public class SeekBarDialog extends DialogFragment {

    private SettingsFragment mSettingsFragment;

    public SeekBarDialog setSettingsFragment(SettingsFragment settingsFragment) {
        this.mSettingsFragment = settingsFragment;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.seekbar_dialog, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBarRate);
        final TextView numberSeekBar = (TextView) view.findViewById(R.id.number_seek_bar);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        int progress = Integer.parseInt(pref.getString(SettingsFragment.KEY_RATE, "0"));
        seekBar.setProgress(progress);
        numberSeekBar.setText(String.valueOf(progress));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                numberSeekBar.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button btnYes = (Button) view.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBarRate);
                mSettingsFragment.setRate(seekBar.getProgress());
                dismiss();
            }
        });
        Button btnNo = (Button) view.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }
}
