package my.qq.com.czw3dmgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * 文章详情页面
 */
public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private WebView webView;
    private WebSettings webSettings;
    private Button btngo,btnback;
    String urlstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //隐藏actionbar
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        webView= (WebView) findViewById(R.id.DetailsActivity_webview);
        btngo= (Button) findViewById(R.id.DetailsActivity_btngo);
        btnback= (Button) findViewById(R.id.DetailsActivity_btnback);

        Intent intent=getIntent();
        urlstr=intent.getStringExtra("urlstr");
        Log.i("aaa","详情页url="+urlstr);
        btngo.setOnClickListener(this);
        btnback.setOnClickListener(this);

        webSettings=webView.getSettings();
        //setBuiltInZoomControls(true)屏幕右下角出现放大缩小按钮
        webSettings.setBuiltInZoomControls(true);
        webView.loadUrl(urlstr);
        Log.i("aaa","文章详情页加载webView.loadUrl---"+urlstr);
        //这个方法就是保证打开链接时，不会跳转到浏览器，而是在本应用中打开
        webView.setWebViewClient(new WebViewClient(){
            //覆盖url的加载
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("aaa","文章详情页setWebViewClient方法执行---"+url);
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.DetailsActivity_btngo:
                if (webView.canGoForward()){
                    webView.goForward();
                }
                break;
            case R.id.DetailsActivity_btnback:
                if (webView.canGoBack()){
                    webView.goBack();
                }
                break;
        }
    }
}
