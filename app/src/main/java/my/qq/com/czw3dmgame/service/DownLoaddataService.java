package my.qq.com.czw3dmgame.service;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import my.qq.com.czw3dmgame.uitls.HttpUtils;
import my.qq.com.czw3dmgame.uitls.JsonUtils;
import my.qq.com.czw3dmgame.uitls.News;

public class DownLoaddataService extends Service {
   public static int i=0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String path=intent.getStringExtra("path");
        Log.i("aaa","onStartCommand执行了---path="+path+"线程名"+Thread.currentThread().getName());
        final String path1=intent.getStringExtra("path1");
        Log.i("aaa","onStartCommand执行了---path1="+path1+"线程名"+Thread.currentThread().getName());
        if(path!=null){
            new Thread(){
                @Override
                public void run() {
                    byte[] b= HttpUtils.request(path);
                    try {
                        String json=new String(b,"utf-8");
                        List<News> list= JsonUtils.jsonTONews(json);
                        Log.i("aaa","服务耗时操作完成,数据库新建完成,线程:"+Thread.currentThread().getName());
                        Log.i("aaa","*****i="+i+"下载另外10条数据");
                        if (i==10) {
                        byte[] b1= HttpUtils.request(path1);
                        String json1=new String(b1,"utf-8");
                        List<News> list1= JsonUtils.jsonTONews(json1);
                        Log.i("aaa","服务耗时操作完成,数据库新建完成1,线程:"+Thread.currentThread().getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }
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
                //新建或打开数据库
                SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath+ File.separator+dbname,null);
                db.execSQL("create table if not exists news(id integer primary key,typeid varchar(10),title varchar(50), shorttitle varchar(50),imgpath varchar(50),senddate varchar(50),weight varchar(50),arcurl varchar(50))");
                Log.i("aaa","创建表");
//                Log.i("aaa","insert into news(id,typeid,title,shorttitle,imgpath,senddate,feedback,arcurl) values("
//                        +id+",'"+typeid+"','"+title+"','"+shorttitle+"','"+imgpath+"','"+senddate+"','"+feedback+"','"+arcurl+"')");
                db.execSQL("insert into news(id,typeid,title,shorttitle,imgpath,senddate,weight,arcurl) values("+id+",'"+typeid+"','"+title+"','"+shorttitle+"','"+imgpath+"','"+senddate+"','"+weight+"','"+arcurl+"')");
                ++i;

                Log.i("aaa","正在存入数据库一条数据"+i);
            }

            return true;

        }
        return false;
    }
}
