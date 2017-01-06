package training.fpt.nhutlv.lvnstore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import training.fpt.nhutlv.lvnstore.adapters.ScreenShotAdapter;

/**
 * Created by HCD-Fresher039 on 12/27/2016.
 */

public class DetailAppActivity extends AppCompatActivity {

    //region Properties

    RecyclerView mScreenShots;
    int [] images = {R.drawable.image,R.drawable.image_b,R.drawable.image_c,R.drawable.image_d,R.drawable.image_e};
    ScreenShotAdapter mAdapter;

    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_app);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mScreenShots = (RecyclerView) findViewById(R.id.recycler_image_detail);
        mAdapter = new ScreenShotAdapter(this,images);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        mScreenShots.setLayoutManager(layoutManager);
        mScreenShots.setAdapter(mAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
