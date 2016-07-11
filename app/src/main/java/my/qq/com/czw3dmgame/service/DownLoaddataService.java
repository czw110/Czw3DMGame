package my.qq.com.czw3dmgame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

import my.qq.com.czw3dmgame.uitls.DownloadUtils;
import my.qq.com.czw3dmgame.uitls.HttpUtils;
import my.qq.com.czw3dmgame.uitls.JsonUtils;
import my.qq.com.czw3dmgame.uitls.News;

public class DownLoaddataService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String path = intent.getStringExtra("path");
        Log.i("aaa","服务中得到的path"+path);
        String path1 = intent.getStringExtra("path1");
        String path2 = intent.getStringExtra("path2");
        String path3 = intent.getStringExtra("path3");
        String path4 = intent.getStringExtra("path4");
        final String[] paths = {path3, path1, path2, path, path4};
        String url = "";
        for (int j = 0; j < paths.length; j++) {
            url = paths[j];
            //url = path3+(j+2);
            Log.i("aaa", "onStartCommand执行了---url=" + url + "线程名" + Thread.currentThread().getName());
            if (url != null) {
                final String str = url;
                new Thread() {
                    @Override
                    public void run() {
                        byte[] b = HttpUtils.request(str);
                        try {
                            String json = new String(b, "utf-8");
                            List<News> list = JsonUtils.jsonTONews(json);
                            Log.i("aaa", "服务耗时操作完成,数据库新建完成,线程:" + Thread.currentThread().getName()+"**********");
                            //调用存入数据库的方法
                            boolean flag= DownloadUtils.saveData(list);
                            Log.i("aaa","数据存完"+flag);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
        stopSelf();
        Log.i("aaa","结束了服务");
            //return START_REDELIVER_INTENT;
            return super.onStartCommand(intent, flags, startId);
        }

    }


