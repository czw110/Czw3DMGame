package my.qq.com.czw3dmgame.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import my.qq.com.czw3dmgame.R;
import my.qq.com.czw3dmgame.adapter.MainArticleFramentViewPagerAdapter;
import my.qq.com.czw3dmgame.customview.MainArticleFragmentViewPager;

/**
 * Created by czw on 2016/7/7  11:16.
 */
public class ArticleFragment extends Fragment{
    private int typeid;
    MainArticleFramentViewPagerAdapter mainArticleFramentViewPagerAdapter;
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
        TextView tv = (TextView)view.findViewById(R.id.activity_main_articlefragment_tv);
        Log.i("aaa","typeid="+typeid);
        tv.setText(typeid+"");

        return view;
    }
}
