package cn.xm.vemaxmall.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.ArrayList;

import cn.lzz.utils.ResoureUtils;
import cn.xm.vemaxmall.R;
import cn.xm.vemaxmall.base.BaseFragment;
import cn.xm.vemaxmall.utils.GlideImageLoader;
import cn.xm.vemaxmall.view.ObservableScrollView;


public class HomeFragment extends BaseFragment implements ObservableScrollView.ScrollViewListener{

    private ObservableScrollView scrollView;
    private LinearLayout titleBar;
    private View rootView;
    private SwipeRefreshLayout srl;
    private Banner banner;
    private TextView tvInput;
    private int imageHeight;

    public static HomeFragment newInstance(){
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.fragment_home, null);
        initViews();
        return rootView;
    }

    private void initViews() {
        tvInput = rootView.findViewById(R.id.tv_input);
        scrollView = rootView.findViewById(R.id.scrollView);
        titleBar = rootView.findViewById(R.id.titleBar);
        banner = rootView.findViewById(R.id.banner);
        titleBar.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
        ViewTreeObserver vto = banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleBar.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = banner.getHeight();

                scrollView.setScrollViewListener(HomeFragment.this);
            }
        });


        scrollView.setScrollViewListener(this);

        srl = rootView.findViewById(R.id.srl);
        srl.setColorSchemeColors(ResoureUtils.getColor(getContext(), R.color.colorTheme));
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },1000);
            }
        });

        banner = (Banner) rootView.findViewById(R.id.banner);
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.img_two_bi_one);
        images.add(R.drawable.img_two_bi_one);
        images.add(R.drawable.img_two_bi_one);
        images.add(R.drawable.img_two_bi_one);
//        ArrayList<String>images = new ArrayList<>();
//        images.add("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg");
//        images.add("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg");
//        images.add("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg");

        banner.startAutoPlay();
        banner.setDelayTime(4000);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
//            banner.setImages(images);
    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {
            //设置标题的背景颜色
            tvInput.setAlpha(0.4f);
        } else if (y > 0 && y <= imageHeight) {
            //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / imageHeight;
            float alpha = (1 * scale);
            tvInput.setAlpha(alpha);
        } else {
            //滑动到banner下面设置普通颜色
            tvInput.setAlpha(1);
        }
    }
}
