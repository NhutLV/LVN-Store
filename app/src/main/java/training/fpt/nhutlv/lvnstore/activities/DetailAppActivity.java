package training.fpt.nhutlv.lvnstore.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import training.fpt.nhutlv.lvnstore.R;
import training.fpt.nhutlv.lvnstore.adapters.ScreenShotAdapter;
import training.fpt.nhutlv.lvnstore.entities.AppInfo;
import training.fpt.nhutlv.lvnstore.entities.RealmString;
import training.fpt.nhutlv.lvnstore.model.service.AppInfoServiceImpl;
import training.fpt.nhutlv.lvnstore.utils.Callback;
import training.fpt.nhutlv.lvnstore.utils.Constant;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class DetailAppActivity extends AppCompatActivity {

    //region Properties
    @BindView(R.id.recycler_image_detail)
    RecyclerView mScreenShots;
    ScreenShotAdapter mAdapter;
    RealmList<RealmString> mImages = new RealmList<>();
    AppInfo mAppInfo;
    DecimalFormat df = new DecimalFormat("#,000");

    @BindView(R.id.image_detail)
    ImageView mIcon;

    @BindView(R.id.title_detail)
    TextView mTitle;

    @BindView(R.id.developer_detail)
    TextView mDeveloper;

    @BindView(R.id.rating_detail)
    RatingBar mRatingBar;

    @BindView(R.id.number_rating_detail)
    TextView mNumberRating;

    @BindView(R.id.price_detail)
    TextView mPrice;

    @BindView(R.id.description_detail)
    TextView mDescription;

    @BindView(R.id.what_is_new)
    TextView mWhatIsNew;

    @BindView(R.id.update_detail)
    TextView mUpdateDetail;

    @BindView(R.id.website_detail)
    TextView mWebsite;

    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_app);

        ButterKnife.bind(this);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        String package_name = bundle.getString("package_name");
        String title = bundle.getString("title");
        setTitle(title);


        if(package_name.equals("favourite")){
            final AppInfo appInfo = bundle.getParcelable("APP");
            mImages = appInfo.getScreenshots();
//            Picasso.with(DetailAppActivity.this).load(appInfo.getIcon()).placeholder(R.drawable.image).into(mIcon);
            Bitmap bmp = BitmapFactory.decodeByteArray(appInfo.getImageIcon(), 0, appInfo.getImageIcon().length);
            mIcon.setImageBitmap(Bitmap.createScaledBitmap(bmp, 150,
                   150, false));
            mTitle.setText(appInfo.getTitle());
            mDeveloper.setText(appInfo.getDeveloper());
            mNumberRating.setText("("+String.valueOf(appInfo.getRating())+")");
            mRatingBar.setNumStars(appInfo.getNumber_rating());
            mRatingBar.setIsIndicator(true);
            String price = String.valueOf(df.format(appInfo.getPrice_numeric()* Constant.USDTOVN));
            mPrice.setText(price.equals("000")?getResources().getString(R.string.text_price):price+" VNĐ");
            mDescription.setText(appInfo.getDescription());
            mWhatIsNew.setText(appInfo.getWhat_is_new());
            mUpdateDetail.setText("Update : "+appInfo.getMarket_update());
            mWebsite.setText(appInfo.getWebsite());

            mWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailAppActivity.this,SupportActivity.class);
                    intent.putExtra("link_support",appInfo.getWebsite());
                    startActivity(intent);
                }
            });

            mScreenShots = (RecyclerView) findViewById(R.id.recycler_image_detail);
            mAdapter = new ScreenShotAdapter(DetailAppActivity.this,mImages);

            LinearLayoutManager layoutManager = new LinearLayoutManager(DetailAppActivity.this,LinearLayoutManager.HORIZONTAL,false);

            mScreenShots.setLayoutManager(layoutManager);
//            mScreenShots.setAdapter(mAdapter);
        }else{
            new AppInfoServiceImpl(this).getAppInfoByPackageName(package_name, new Callback<AppInfo>() {
                @Override
                public void onResult(final AppInfo appInfo) {
                    mAppInfo = appInfo;
                    mImages = mAppInfo.getScreenshots();
                    Picasso.with(DetailAppActivity.this).load(appInfo.getIcon()).into(mIcon);
                    mTitle.setText(appInfo.getTitle());
                    mDeveloper.setText(appInfo.getDeveloper());
                    mNumberRating.setText("("+String.valueOf(appInfo.getRating())+")");
                    mRatingBar.setNumStars(appInfo.getNumber_rating());
                    mRatingBar.setIsIndicator(true);
                    String price = String.valueOf(df.format(appInfo.getPrice_numeric()* Constant.USDTOVN));
                    mPrice.setText(price.equals("000")?getResources().getString(R.string.text_price):price+" VNĐ");
                    mDescription.setText(appInfo.getDescription());
                    mWhatIsNew.setText(appInfo.getWhat_is_new());
                    mUpdateDetail.setText("Update : "+appInfo.getMarket_update());
                    mWebsite.setText(appInfo.getWebsite());

                    mWebsite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DetailAppActivity.this,SupportActivity.class);
                            intent.putExtra("link_support",appInfo.getWebsite());
                            startActivity(intent);
                        }
                    });

                    mScreenShots = (RecyclerView) findViewById(R.id.recycler_image_detail);
                    mAdapter = new ScreenShotAdapter(DetailAppActivity.this,mImages);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(DetailAppActivity.this,LinearLayoutManager.HORIZONTAL,false);

                    mScreenShots.setLayoutManager(layoutManager);
                    mScreenShots.setAdapter(mAdapter);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
