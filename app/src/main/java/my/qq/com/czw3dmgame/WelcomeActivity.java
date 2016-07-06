package my.qq.com.czw3dmgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import my.qq.com.czw3dmgame.service.DownLoaddataService;
import my.qq.com.czw3dmgame.uitls.NetUitls;
import pl.droidsonroids.gif.GifImageView;

/**
 * 欢迎界面,设置一个动画
 */
public class WelcomeActivity extends AppCompatActivity {
    private GifImageView gifImageView;
    private Animation animation;
    private boolean netopen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        gifImageView= (GifImageView) this.findViewById(R.id.WelcomeActivity_giv);
        //给欢迎界面添加一个动画,动画期间可以启动服务做其他事,下载数据等
        animation=new AlphaAnimation(0,1.0f);//透明度动画
        animation.setDuration(3000);
        gifImageView.startAnimation(animation);
        //给动画添加监听,在动画开始和结束的时候执行操作
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            //动画开始,判断网络,启动服务下载数据
                netopen= NetUitls.netJudge(WelcomeActivity.this);
                if (netopen){
                    //开始Service，下载数据
                    Intent intent=new Intent(WelcomeActivity.this, DownLoaddataService.class);
                    String path="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=1&paging=1&page=1";
                    intent.putExtra("path",path);
                    startService(intent);
                    Log.i("aaa","启动服务,开始下载");
                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束后,判断是否是第一次打开应用,第一次打开进入引导页面
                //先判断是否有网络,没有网络直接提示
                if (!netopen){
                    Toast.makeText(WelcomeActivity.this,"请连接你的网络",Toast.LENGTH_LONG).show();
                }
                //判断是否是第一次登陆
                isFristLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void isFristLogin() {
        //创建sharedPreferences对象,Context.MODE_PRIVATE是指权限是私有的
        SharedPreferences sharedPreferences=getSharedPreferences("isFristLogin", Context.MODE_PRIVATE);
        //获得sharedPreferences对象中的isLogin属性,false是默认值
        boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
        //如果是第一登陆,就跳转到引导界面，否则的话，跳转到主界面
        if (!isLogin){
            Intent guideIntent = new Intent(WelcomeActivity.this,GuideActivity.class);
            startActivity(guideIntent);
            //如果不加finish,那关闭主界面之后还会返回到引导界面
            finish();
        }else {
            Intent mainIntent = new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
