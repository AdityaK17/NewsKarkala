package com.beast.adi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.firebase.messaging.FirebaseMessaging;

public class Home extends AppCompatActivity {
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseMessaging.getInstance().subscribeToTopic("news_karkala_by_nmamit");
        wv =findViewById(R.id.webv);
        Button btn = (Button) findViewById(R.id.button);
        WebSettings ws = wv.getSettings();
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(false);
        wv.getSettings().setSupportZoom(false);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setBackgroundColor(Color.TRANSPARENT);
        //wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebV());
        String cmp=getIntent().getStringExtra("msg");
        wv.loadUrl("https://newskarkala.com/");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://chat.whatsapp.com/LlADelxB4YJBpSnn4ngQN5");
            }
        });


    }

    private void gotoUrl(String s) {
        Uri url = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,url));
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wv.canGoBack()) {
                        wv.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}


class WebV extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}