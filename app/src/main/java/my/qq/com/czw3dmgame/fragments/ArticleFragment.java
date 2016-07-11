package my.qq.com.czw3dmgame.fragments;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import my.qq.com.czw3dmgame.DetailsActivity;
import my.qq.com.czw3dmgame.R;
import my.qq.com.czw3dmgame.adapter.MainArticleFramentViewPagerAdapter;
import my.qq.com.czw3dmgame.adapter.PullToRefreshAdapter;
import my.qq.com.czw3dmgame.customview.MainArticleFragmentViewPager;

/**
 * Created by czw on 2016/7/7  11:16.
 * 这个类是主页面的整体碎片,包括三张大图和listview
 * 这个类里的三个大图又是一个自定义的viewpager,所以下面又有MainArticleFramentViewPagerAdapter这个类
 * MainArticleFramentViewPagerAdapter这个类,把imageview(即三张本地图片资源)填充到本类里
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
        //从xml文件中获得主界面的整体布局
        View view = inflater.inflate(R.layout.activity_main_articlefragment,null);
        //获得Fragment中的ViewPager,也就是那三张大图的控件
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
        if(typeid!=2){
            //从文章首页向右划到其他页面时,让它隐藏那三张大图
            // 隐藏（GONE）可见（visible)不可见（invisible）
            View view1=view.findViewById(R.id.activity_main_articlefragment_framelayout);//获取listview上面的整个布局,不能只隐藏那三个大图的控件
            view1.setVisibility(View.GONE);
        }
        //三张图片下面那一块,显示数据库的数据条目
            initpulltorefresh(view);

        return view;
    }
    /**
     * 下面是PullRefreshListView的代码---------------------------------------------------------------
     */
    //填充PullRefreshListView
    public void initpulltorefresh(View view){
        PullToRefreshListView mPullRefreshListView= (PullToRefreshListView) view.findViewById(R.id.activity_main_articlefragment_pull);

        final List<HashMap<String,Object>> alldata=selectData();
        Log.i("aaa","查询数据库完成");
        if (alldata!=null){
            Log.i("aaa","PullToRefreshAdapter填充");
            PullToRefreshAdapter myadapter=new PullToRefreshAdapter(getContext(),alldata);
            mPullRefreshListView.setAdapter(myadapter);
            Log.i("aaa","typeid="+typeid);
            //OnRefreshListener和OnRefreshListener2的区别,OnRefreshListener里面是onRefresh方法
            //OnRefreshListener2里面是上拉和下拉的方法
            mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    //下拉执行的方法
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉执行的方法
                }
            });
            //**********************************

            mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=new Intent(getContext(), DetailsActivity.class);
                    Log.i("aaa","i="+i+"long="+l);
                    String urlstr=alldata.get(i-1).get("arcurl").toString();
                    Log.i("aaa","ArticleFragment跳转到详情"+urlstr);
                    intent.putExtra("urlstr",urlstr);
                    startActivity(intent);
                }
            });

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
            String arcurl= cursor.getString(cursor.getColumnIndex("arcurl"));//下载量
            HashMap<String,Object> map=new HashMap<>();
            map.put("imgpath",imgpath);
            map.put("shorttitle",shorttitle);
            map.put("senddate",senddate);
            map.put("weight",weight);
            map.put("arcurl",arcurl);
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
