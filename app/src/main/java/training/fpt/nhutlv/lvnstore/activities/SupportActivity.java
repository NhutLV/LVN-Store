package training.fpt.nhutlv.lvnstore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import training.fpt.nhutlv.lvnstore.R;

/**
 * Created by NhutDu on 08/01/2017.
 */

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        WebView webView = (WebView) findViewById(R.id.web_view_support);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDisplayZoomControls(true);
        webView.loadUrl(getIntent().getStringExtra("link_support"));
    }
}
