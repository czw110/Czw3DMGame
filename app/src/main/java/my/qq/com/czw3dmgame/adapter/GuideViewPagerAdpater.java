package my.qq.com.czw3dmgame.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by czw on 2016/7/6  16:08.
 * 引导页面GuideActivity的viewpager适配器
 */
public class GuideViewPagerAdpater extends PagerAdapter {
    private List<View> views;
    public GuideViewPagerAdpater(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
