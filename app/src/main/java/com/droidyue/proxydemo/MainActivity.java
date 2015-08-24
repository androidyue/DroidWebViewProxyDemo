package com.droidyue.proxydemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends ActionBarActivity {
    private WebView myWebView;
    private static final String PROXY_HOST = "10.0.5.13";
    private static final int PROXY_PORT = 8087;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView = (WebView)findViewById(R.id.myWebView);
        initWebView();
    }

    private void initWebView() {
        ProxyUtils.setProxy(myWebView, PROXY_HOST, PROXY_PORT);
        myWebView.getSettings().setJavaScriptEnabled(true);
        String url = "http://droidyue.com";
        myWebView.loadUrl(url);
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_setproxy){
            ProxyUtils.setProxy(myWebView, PROXY_HOST, PROXY_PORT);
        } else if (id == R.id.action_clearproxy) {
            ProxyUtils.clearProxy(myWebView);
        }

        return super.onOptionsItemSelected(item);
    }
}
