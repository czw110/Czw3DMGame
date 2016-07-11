package my.qq.com.czw3dmgame;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import my.qq.com.czw3dmgame.adapter.MainFragmentPagerAdapter;
import my.qq.com.czw3dmgame.fragments.ArticleFragment;
import my.qq.com.czw3dmgame.fragments.BbsFragment;
import my.qq.com.czw3dmgame.fragments.GameFragment;

/**
 * 主页中的中间部分就是viewpager,包括三张图片和下面的listview,然后viewpager又是由fragments填充的
 * fragments集合中的每个碎片,都来自ArticleFragment,这个里面包括三张大图和listview
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener {
    private HorizontalScrollView horizontalScrollView;
    private RadioGroup radioGroup_top;
    private RadioGroup radioGroup_bottom;
    private RadioButton rb01_top,rb02_top,rb03_top,rb04_top,rb05_top,rb06_top,rb07_top,rb08_top,rb09_top,rb10_top;
    private RadioButton rb01_bottom,rb02_bottom,rb03_bottom;
    private ViewPager viewPager;
    MainFragmentPagerAdapter mainFragmentPagerAdapter;
    List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initlistener();
        initdata();

    }


    //初始化控件
    public void initview(){
        horizontalScrollView= (HorizontalScrollView) this.findViewById(R.id.MainActivity_top_hsv);
        radioGroup_top= (RadioGroup) this.findViewById(R.id.MainActivity_top_rg);
        rb01_top= (RadioButton) this.findViewById(R.id.MainActivity_top_rb1);
        rb02_top= (RadioButton) this.findViewById(R.id.MainActivity_top_rb2);
        rb03_top= (RadioButton) this.findViewById(R.id.MainActivity_top_rb3);
        rb04_top= (RadioButton) this.findViewById(R.id.MainActivity_top_rb4);
        rb05_top= (RadioButton) this.findViewById(R.id.MainActivity_top_rb5);
        rb06_top= (RadioButton) this.findViewById(R.id.MainActivity_top_rb6);
        rb08_top= (RadioButton) this.findViewById(R.id.MainActivity_top_rb8);
        rb09_top= (RadioButton) this.findViewById(R.id.MainActivity_top_rb9);
        rb10_top= (RadioButton) this.findViewById(R.id.MainActivity_top_rb10);
        radioGroup_bottom= (RadioGroup) this.findViewById(R.id.MainActivity_bottom_rg);
        rb01_bottom= (RadioButton) this.findViewById(R.id.MainActivity_bottom_rb01);
        rb02_bottom= (RadioButton) this.findViewById(R.id.MainActivity_bottom_rb02);
        rb03_bottom= (RadioButton) this.findViewById(R.id.MainActivity_bottom_rb03);
        viewPager= (ViewPager) this.findViewById(R.id.MainActivity_center_vp);
        rb01_top.setChecked(true);
    }
    //设置所有的监听
    public void initlistener(){
        radioGroup_top.setOnCheckedChangeListener(this);
        radioGroup_bottom.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
    }
    public void initdata(){
        fragments = new ArrayList<Fragment>();
        //添加Fragment
        ArticleFragment f1 = new ArticleFragment(2);//文章首页
        ArticleFragment f2 = new ArticleFragment(2);//热点新闻
        ArticleFragment f3 = new ArticleFragment(151);//游戏杂谈
        ArticleFragment f4 = new ArticleFragment(152);//硬件信息
        ArticleFragment f5 = new ArticleFragment(153);//游戏前瞻
        ArticleFragment f6 = new ArticleFragment(154);
        ArticleFragment f7 = new ArticleFragment(196);
        ArticleFragment f8 = new ArticleFragment(197);
        ArticleFragment f9 = new ArticleFragment(9);
        ArticleFragment f10 = new ArticleFragment(10);
        BbsFragment b1 = new BbsFragment();
        GameFragment g1=new GameFragment();
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
        fragments.add(f5);
        fragments.add(f6);
        fragments.add(f7);
        fragments.add(f8);
        fragments.add(f9);
        fragments.add(f10);
        fragments.add(b1);
        fragments.add(g1);
        Log.i("aaa","fragments.size"+fragments.size());
        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(mainFragmentPagerAdapter);
    }

    //上下两组radioGroup的监听
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            //上面radioGroup的十个按钮
            case R.id.MainActivity_top_rb1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.MainActivity_top_rb2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.MainActivity_top_rb3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.MainActivity_top_rb4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.MainActivity_top_rb5:
                viewPager.setCurrentItem(4);
                break;
            case R.id.MainActivity_top_rb6:
                viewPager.setCurrentItem(5);
                break;
            case R.id.MainActivity_top_rb7:
                viewPager.setCurrentItem(6);
                break;
            case R.id.MainActivity_top_rb8:
                viewPager.setCurrentItem(7);
                break;
            case R.id.MainActivity_top_rb9:
                viewPager.setCurrentItem(8);
                break;
            case R.id.MainActivity_top_rb10:
                viewPager.setCurrentItem(9);
                break;
            ////下面radioGroup的3个按钮
            case R.id.MainActivity_bottom_rb01:
                viewPager.setCurrentItem(0);
                //点击下面的文章按钮,上面的标签会回到第一个"文章首页"
                Log.i("aaa","文章按钮");
                horizontalScrollView.smoothScrollTo(0,0);
                break;
            case R.id.MainActivity_bottom_rb02:
                //论坛按钮
                Log.i("aaa","论坛按钮");
                //进入论坛,隐藏上面那一排按钮
                horizontalScrollView.setVisibility(View.GONE);
                viewPager.setCurrentItem(10);
                break;
            case R.id.MainActivity_bottom_rb03:
                horizontalScrollView.setVisibility(View.GONE);
                viewPager.setCurrentItem(11);
                break;
        }

    }

    /***
     * 下面三个是viewpager的监听实现方法
     *
     */

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.i("aaa","position="+position);
        //因为填充下面论坛和游戏按钮的碎片和填充上面那一排按钮用的是同一个适配器,所以就是所有的碎片都在同一个集合中
        //当点击论坛按钮的时候,position=10,程序回去上面那一排按钮中找id为10的按钮,并把它设为选中状态,但是找不到那个按钮,所以要加if(position<10)
        if(position<10) {
            //顶部的滚动条出现移动效果,上部条目一页显示不完,随着下面碎片的滑动而滑动
            horizontalScrollView.setVisibility(View.VISIBLE);
            radioGroup_top.setVisibility(View.VISIBLE);
            //获得当前ViewPager对应的RadioButton,并设置为选中状态
            RadioButton radioButton = (RadioButton) radioGroup_top.getChildAt(position);
            radioButton.setChecked(true);
            //让顶部的RadioButton随着ViewPager一起滚动
            int left = radioButton.getLeft();//获取选中标签离屏幕左边的距离,然后移动它
            horizontalScrollView.smoothScrollTo(left, 0);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 下面是清空数据库的操作*-----------------------------------------------------------------------
     */
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
