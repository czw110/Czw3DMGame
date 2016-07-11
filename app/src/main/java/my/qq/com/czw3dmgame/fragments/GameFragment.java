package my.qq.com.czw3dmgame.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import my.qq.com.czw3dmgame.GamePaperActivity;
import my.qq.com.czw3dmgame.R;

/**
 * Created by czw on 2016/7/9  17:16.
 * 游戏页面的碎片
 */
public class GameFragment extends Fragment{
    private GridView gview;
    private Spinner spinner;
    private List<HashMap<String, Object>> game_list;
    private SimpleAdapter sim_adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_game,null);
        //获取控件GridView,spinner
        gview= (GridView) view.findViewById(R.id.gamefragment_gridview);
        spinner= (Spinner) view.findViewById(R.id.gamefragment_spinner);
        //查询数据库,游戏页面初始化显示的数据,默认tyeid=181,动作游戏
        //填充GridView
        game_list=selectData("181");
        String [] from ={"imgpath","shorttitle"};
        int [] to = {R.id.gamefragment_iv,R.id.gamefragment_tv};
        sim_adapter = new SimpleAdapter(getContext(), game_list, R.layout.game_item, from, to);
        gview.setAdapter(sim_adapter);
        //给GridView加监听
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String gameurl=game_list.get(i).get("arcurl").toString();
                Intent intent=new Intent(getContext(), GamePaperActivity.class);
                intent.putExtra("gameurl",gameurl);
                startActivity(intent);
            }
        });
        //填充spinner*****************************************************
        paddingSpinner();
        return view;
    }

    //填充spinner的方法
    public void paddingSpinner(){
        String[] games={"动作(ACT)","射击(FPS)","角色扮演(RPG)","养成(GAL)"};
        //3适配器
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.item_spinner,games);
        spinner.setAdapter(adapter);
        Log.i("aaa","填充spinner");
        //spinner的选择监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //项选择后执行的方法
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //i是项目的位置,从0开始,long是条目的id
                Log.i("aaa","i="+i);
                List<HashMap<String, Object>> listtemp=null;
                switch (i){
                    case 0:
                        //动作(ACT)
                        listtemp=selectData("181");
                        game_list.clear();
                        game_list.addAll(listtemp);
                        sim_adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        //射击(FPS)
                        listtemp=selectData("182");
                        game_list.clear();
                        game_list.addAll(listtemp);
                        sim_adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        //角色扮演(RPG)
                        listtemp=selectData("183");
                        game_list.clear();
                        game_list.addAll(listtemp);
                        sim_adapter.notifyDataSetChanged();
                        break;
                    case 3:
                        //养成(GAL)
                        listtemp=selectData("184");
                        game_list.clear();
                        game_list.addAll(listtemp);
                        sim_adapter.notifyDataSetChanged();
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    //查询数据库获取数据的方法
    public List<HashMap<String,Object>> selectData(String typeid){
        List<HashMap<String,Object>> selectlist=new ArrayList<HashMap<String, Object>>();
        String sdpath= Environment.getExternalStorageDirectory().getAbsolutePath();
        String dbname="news.db";
        SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath + File.separator + dbname, null);
        Cursor cursor=db.rawQuery("select * from news where typeid="+typeid, null);
        Log.i("aaa","查询数据库方法中的typeid="+typeid);
        while (cursor.moveToNext()) { // moveToNext判断下一个条数据是否存在
            String imgpath= cursor.getString(cursor.getColumnIndex("imgpath"));//图片
            String shorttitle= cursor.getString(cursor.getColumnIndex("shorttitle"));//短标题
            String arcurl= cursor.getString(cursor.getColumnIndex("arcurl"));//时间

            HashMap<String,Object> map=new HashMap<>();
            map.put("imgpath",imgpath);
            map.put("shorttitle",shorttitle);
            map.put("arcurl",arcurl);
//            map.put("weight",weight);
//            map.put("description",description);
            selectlist.add(map);
        }

        Log.i("aaa","selectlist.size"+selectlist.size());
        // 4关闭游标
        cursor.close();
        // 5关闭数据库
        db.close();
        return selectlist;

    }
}
