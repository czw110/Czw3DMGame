package my.qq.com.czw3dmgame.uitls;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by czw on 2016/7/6  15:15.
 * 判断手机是否联网
 */
public class NetUitls {
    public static boolean netJudge(Activity activity){
        boolean flag=false;
        //得到连接的管理对象
        ConnectivityManager connectivityManager= (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //如果连接的管理对象为空，直接返回
        if (connectivityManager==null){
            return flag;
        }
        //根据连接的管理对象得到网络的信息对象
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        //如果连接的信息对象不为空，或连接的信息对象是活动的，说明网络连接成功
        if (networkInfo!=null||networkInfo.isAvailable()){
               flag=true;
        }
        return flag;
    }
}
