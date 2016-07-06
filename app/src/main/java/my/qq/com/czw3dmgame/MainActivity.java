package my.qq.com.czw3dmgame;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        File root= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //关闭页面时,清空下载的图片
        deleteAllFiles(root);
        Log.i("aaa","已清空图片");
        //sd卡路径
         String sdpath= Environment.getExternalStorageDirectory().getAbsolutePath();
        //数据库文件名
         String dbname="news.db";
         SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath+ File.separator+dbname,null);
        //清空数据库表内的数据
        db.execSQL("delete from news");
        Log.i("aaa","已清空数据库");
    }
    //页面关闭时清空图片文件夹的方法
    private void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }
}
