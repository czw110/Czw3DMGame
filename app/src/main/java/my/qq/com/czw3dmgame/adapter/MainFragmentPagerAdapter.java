package my.qq.com.czw3dmgame.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 这个类是主界面的整体碎片的适配器
 * 把ArticleFragment这个整体碎片类填充到主界面
 * Created by czw on 2016/7/7  11:58.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public MainFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
