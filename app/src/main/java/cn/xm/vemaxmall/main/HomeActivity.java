package cn.xm.vemaxmall.main;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import cn.xm.vemaxmall.R;
import cn.xm.vemaxmall.base.BaseActivity;
import cn.xm.vemaxmall.view.ObservableScrollView;


public class HomeActivity extends BaseActivity implements ObservableScrollView.ScrollViewListener {

    private ObservableScrollView scrollView;
    private LinearLayout titleBar;
    private ImageView ivBanner;
    private int imageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
    }


    private void initViews() {
        scrollView = findViewById(R.id.scrollView);
        ivBanner = findViewById(R.id.iv_banner);
        titleBar = findViewById(R.id.titleBar);

        titleBar.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));

        ViewTreeObserver vto = ivBanner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleBar.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = ivBanner.getHeight();

                scrollView.setScrollViewListener(HomeActivity.this);
            }
        });


        scrollView.setScrollViewListener(this);

    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y,

                                int oldx, int oldy) {

        if (y <= 0) {
            //设置标题的背景颜色
            titleBar.setBackgroundColor(Color.argb((int) 0, 144,151,166));
        } else if (y > 0 && y <= imageHeight) {
            //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
//            titleBar.setTextColor(Color.argb((int) alpha, 255,255,255));
            titleBar.setBackgroundColor(Color.argb((int) alpha, 144,151,166));
        } else {    //滑动到banner下面设置普通颜色
            titleBar.setBackgroundColor(Color.argb((int) 255, 144,151,166));
        }


    }

}
