package my.qq.com.czw3dmgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GamePaperActivity extends AppCompatActivity {
    private WebView webView;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐去标题栏 （应用程序的名字）
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game_paper);
        webView= (WebView) findViewById(R.id.GamePaperActivity_webview);

        Intent intent=getIntent();
        String gameurl=intent.getStringExtra("gameurl");
        webSettings=webView.getSettings();
        //setBuiltInZoomControls(true)屏幕右下角出现放大缩小按钮
        webSettings.setBuiltInZoomControls(true);
        webView.loadUrl(gameurl);
        Log.i("aaa","游戏详情页加载webView.loadUrl---"+gameurl);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("aaa","游戏详情页setWebViewClient方法执行---"+url);
                view.loadUrl(url);
                return true;
            }
        });

    }
}
