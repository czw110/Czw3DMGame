package my.qq.com.czw3dmgame.uitls;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import my.qq.com.czw3dmgame.service.DownLoaddataService;

/**
 * Created by czw on 2016/7/5  19:25.
 */
public class JsonUtils {
    public static List<News> jsonTONews(String json){
        List<News> list=new ArrayList<>();
        if (json!=null){
            Log.i("aaa","json不为空");
            try {
                JSONObject root=new JSONObject(json);
                JSONObject data=root.getJSONObject("data");

                for (int i=0;i<20;i++){
                    JSONObject jsonObject=data.getJSONObject(i+"");
                    String id=jsonObject.getString("id");

                    String title=jsonObject.getString("title");
                    String shorttitle=jsonObject.getString("shorttitle");
                    String litpic="http://www.3dmgame.com"+jsonObject.getString("litpic");
                    String senddate=jsonObject.getString("senddate");
                    String feedback=jsonObject.getString("feedback");
                    byte[] b=HttpUtils.request(litpic);

                    //调用下载图片的方法,并返回图片保存路径
                    String imgpath= DownLoaddataService.saveFile(b,id+".jpg");
                    Log.i("aaa","图片保存路径"+imgpath);

                    News news=new News(id,title,shorttitle,imgpath,senddate,feedback);

                    list.add(news);

                }
                //调用存入数据库的方法
                boolean flag=DownLoaddataService.saveData(list);
                Log.i("aaa","数据存完"+flag);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
