package my.qq.com.czw3dmgame.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import my.qq.com.czw3dmgame.R;

/**
 * Created by czw on 2016/7/9  10:47.
 * 论坛页面的碎片
 */
public class BbsFragment extends Fragment implements View.OnClickListener{
    private WebView webView;
    private WebSettings webSettings;
    private Button btngo, btnback;
    private String urlstr = "http://bbs.3dmgame.com/forum.php";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //null是根,没有,所以写null
        View view=inflater.inflate(R.layout.activity_bbs,null);
        webView= (WebView) view.findViewById(R.id.BbsActivity_webview);
        btngo= (Button) view.findViewById(R.id.BbsActivity_btngo);
        btnback= (Button) view.findViewById(R.id.BbsActivity_btnback);
        btngo.setOnClickListener(this);
        btnback.setOnClickListener(this);
        Log.i("aaa","论坛页面onCreateView");
        webSettings=webView.getSettings();
        //setBuiltInZoomControls(true)屏幕右下角出现放大缩小按钮
        webSettings.setBuiltInZoomControls(true);
        webView.loadUrl(urlstr);
        Log.i("aaa","论坛页面webView.loadUrl"+urlstr);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("aaa","论坛页面setWebViewClient"+url);
                view.loadUrl(url);
                return true;
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BbsActivity_btngo:
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
            case R.id.BbsActivity_btnback:
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                break;
    }
}
}
