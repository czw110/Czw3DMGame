package my.qq.com.czw3dmgame.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by czw on 2016/7/7  11:29.
 */
public class MainArticleFragmentViewPager extends ViewPager {
    public MainArticleFragmentViewPager(Context context) {
        super(context);
    }

    public MainArticleFragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //不允许父类拦截触摸事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
