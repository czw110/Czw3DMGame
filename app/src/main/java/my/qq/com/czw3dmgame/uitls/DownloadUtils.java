package my.qq.com.czw3dmgame.uitls;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by czw on 2016/7/9  20:41.
 */
public class DownloadUtils {
    public static int i=0;
    //把图片存入SD卡的方法
    public static String saveFile(byte[] data,String filename){
        File file=null;
        //判断SD卡挂
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //获得SD卡的下载目录 /mnt/sdcard/donwnload
            File root=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            //完整路径mnt/sdcard/download/filename
            file=new File(root,filename);
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                fileOutputStream.write(data,0,data.length);
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file.toString();
    }
    //保存到数据库的方法
    public static boolean saveData(List<News> list){
        //sd卡路径
        String sdpath=Environment.getExternalStorageDirectory().getAbsolutePath();
        String dbname="news.db";
        Log.i("aaa","创建了数据库");

        if (list!=null){
            for (News news:list){
                int id=news.getId();
                String typeid=news.getTypeid();
                String title=news.getTitle();
                String shorttitle=news.getShorttitle();
                String imgpath=news.getLitpic();
                String senddate=news.getSenddate();
                String weight=news.getWeight();
                String arcurl=news.getArcurl();
                String description=news.getDescription();
                //新建或打开数据库
                SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath+ File.separator+dbname,null);
                db.execSQL("create table if not exists news(id integer,typeid varchar(10),title varchar(50), shorttitle varchar(50),imgpath varchar(50),senddate varchar(50),weight varchar(50),arcurl varchar(50),description varchar(100))");
                Log.i("aaa","创建表");
                Log.i("aaa","insert into news(id,typeid,title,shorttitle,imgpath,senddate,weight,arcurl,description) values("+id+",'"+typeid+"','"+title+"','"+shorttitle+"','"+imgpath+"','"+senddate+"','"+weight+"','"+arcurl+"','"+description+"')");

                db.execSQL("insert into news(id,typeid,title,shorttitle,imgpath,senddate,weight,arcurl,description) values("+id+",'"+typeid+"','"+title+"','"+shorttitle+"','"+imgpath+"','"+senddate+"','"+weight+"','"+arcurl+"','"+description+"')");
                ++i;

                Log.i("aaa","正在存入数据库一条数据"+i);
            }

            return true;

        }
        return false;
    }
}
