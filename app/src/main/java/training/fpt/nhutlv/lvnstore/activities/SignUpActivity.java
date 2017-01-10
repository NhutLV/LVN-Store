package training.fpt.nhutlv.lvnstore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.entities.User;
import training.fpt.nhutlv.lvnstore.utils.segmented.SegmentedGroup;

/**
 * Created by NhutDu on 11/10/2016.
 */
public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.segmented_gender)
    SegmentedGroup mSegmentedGroup;

    @BindView(R.id.ip_username)
    TextInputLayout mIpUsername;

    @BindView(R.id.edit_username)
    EditText mEdUsername;

    @BindView(R.id.ip_password)
    TextInputLayout mIpPassword;

    @BindView(R.id.edit_password)
    EditText mEdPassword;

    @BindView(R.id.ip_email)
    TextInputLayout mIpEmail;

    @BindView(R.id.edit_email)
    EditText mEdEmail;

    @BindView(R.id.btn_next)
    Button mBTnSignUp;

    int mGender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mEdUsername.addTextChangedListener(new MyTextWatcher(mIpUsername));
        mEdPassword.addTextChangedListener(new MyTextWatcher(mIpPassword));
        mEdEmail.addTextChangedListener(new MyTextWatcher(mIpEmail));

        mBTnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
                Realm mRealm = Realm.getDefaultInstance();
                User user = new User();
                user.setEmail(mEdEmail.getText().toString());
                user.setName(mEdUsername.getText().toString());
                user.setPassword(mEdPassword.getText().toString());
                user.setGender(mGender==1);
                mRealm.beginTransaction();
                mRealm.createObject(User.class,user.getEmail());
                mRealm.commitTransaction();

                RealmResults<User> users = mRealm.where(User.class).findAll();
                Log.d("TAG",users.size()+"");
            }
        });

    }

    //region Private methods
    private void submitForm() {

        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        if(!validateGender()){
            return;
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateName() {
        if (mEdUsername.getText().toString().trim().isEmpty()) {
            mIpUsername.setError(getString(R.string.err_msg_name));
            requestFocus(mEdUsername);
            return false;
        } else {
            mIpUsername.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = mEdEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            mIpEmail.setError(getString(R.string.err_msg_email));
            requestFocus(mEdEmail);
            return false;
        } else {
            mIpEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (mEdPassword.getText().toString().trim().isEmpty()) {
            mIpPassword.setError(getString(R.string.err_msg_password));
            requestFocus(mEdPassword);
            return false;
        } else {
            mIpPassword.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateGender(){
        if(mSegmentedGroup.getCheckedRadioButtonId()==R.id.radio_male){
            mGender =0;
        }else if(mSegmentedGroup.getCheckedRadioButtonId()==R.id.radio_female){
            mGender = 1;
        }else{
            Toast.makeText(this,"Please choose gender",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //endregion

    private class  MyTextWatcher implements TextWatcher{

        View mView;

        public MyTextWatcher(View view) {
            mView = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (mView.getId()){
                case R.id.ip_username :
                    validateName();
                    break;
                case R.id.ip_password :
                    validatePassword();
                    break;
                case R.id.ip_email :
                    validateEmail();
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
