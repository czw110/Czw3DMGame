package my.qq.com.czw3dmgame.fragments;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import my.qq.com.czw3dmgame.R;
import my.qq.com.czw3dmgame.adapter.MainArticleFramentViewPagerAdapter;
import my.qq.com.czw3dmgame.adapter.PullToRefreshAdapter;
import my.qq.com.czw3dmgame.customview.MainArticleFragmentViewPager;
import my.qq.com.czw3dmgame.service.DownLoaddataService;

/**
 * Created by czw on 2016/7/7  11:16.
 */
public class ArticleFragment extends Fragment{
    private int typeid;
    MainArticleFramentViewPagerAdapter mainArticleFramentViewPagerAdapter;
    //PullToRefreshAdapter myadapter;
    public ArticleFragment() {

    }

    @SuppressLint("ValidFragment")//Annotation 注解
    public ArticleFragment(int typeid) {
        this.typeid = typeid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获得Fragment中整体布局
        View view = inflater.inflate(R.layout.activity_main_articlefragment,null);
        //获得Fragment中的ViewPager
        MainArticleFragmentViewPager mainArticleFragmentViewPager =(MainArticleFragmentViewPager) view.findViewById(R.id.main_articlefragment_viewpager);

        int imageRsId [] = {R.drawable.default1,R.drawable.default2,R.drawable.default3};
        //初始化ViewPager的数据
        List<ImageView> imageViews = new ArrayList<ImageView>();
        for(int i=0;i<3;i++){
            ImageView imageView = new ImageView(getActivity());
            //设置图片的缩放类型  铺满全屏
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageRsId[i]);
            imageViews.add(imageView);
        }
        mainArticleFramentViewPagerAdapter = new MainArticleFramentViewPagerAdapter(imageViews);
        mainArticleFragmentViewPager.setAdapter(mainArticleFramentViewPagerAdapter);
        //三张图片下面那一块,显示数据库的数据条目
        if (DownLoaddataService.i>=10){
            initpulltorefresh(view);
        }



        return view;
    }
    /**
     * 下面是PullRefreshListView的代码---------------------------------------------------------------
     */
    public void initpulltorefresh(View view){
        PullToRefreshListView mPullRefreshListView= (PullToRefreshListView) view.findViewById(R.id.activity_main_articlefragment_pull);

         List<HashMap<String,Object>> alldata=selectData();
        Log.i("aaa","查询数据库完成");
        if (alldata!=null){
            Log.i("aaa","PullToRefreshAdapter填充");
             PullToRefreshAdapter myadapter=new PullToRefreshAdapter(getContext(),alldata);
            mPullRefreshListView.setAdapter(myadapter);
            Log.i("aaa","typeid="+typeid);

        }
    }

    //查询数据库获取数据的方法
    public List<HashMap<String,Object>> selectData(){
        List<HashMap<String,Object>> selectlist=new ArrayList<HashMap<String, Object>>();
        String sdpath= Environment.getExternalStorageDirectory().getAbsolutePath();
        String dbname="news.db";
        SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath + File.separator + dbname, null);
        Cursor cursor=db.rawQuery("select * from news where typeid="+typeid, null);
        Log.i("aaa","查询数据库方法中的typeid="+typeid);
        while (cursor.moveToNext()) { // moveToNext判断下一个条数据是否存在
            String imgpath= cursor.getString(cursor.getColumnIndex("imgpath"));//图片
            String shorttitle= cursor.getString(cursor.getColumnIndex("shorttitle"));//短标题
            String senddate= cursor.getString(cursor.getColumnIndex("senddate"));//时间
            String weight= cursor.getString(cursor.getColumnIndex("weight"));//下载量
            HashMap<String,Object> map=new HashMap<>();
            map.put("imgpath",imgpath);
            map.put("shorttitle",shorttitle);
            map.put("senddate",senddate);
            map.put("weight",weight);
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
