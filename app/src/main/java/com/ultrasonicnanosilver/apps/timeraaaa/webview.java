package com.ultrasonicnanosilver.apps.timeraaaa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView webView = (WebView)
                findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //WebViewClientImpl webViewClient = new WebViewClientImpl(this);
       // webView.setWebViewClient(webViewClient);

        webView.loadUrl("http://ionexx.com/equaflow.html");
    }
   // private class MyWebViewClient extends com.ultrasonicnanosilver.apps.timeraaaa.webview {
     //   @Override
       // public boolean shouldOverrideUrlLoading(WebView webView, String url) {
      //      return false;
     //   }
    //}
}
