package cn.xm.vemaxmall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import java.util.ArrayList;

import cn.lzz.utils.ResoureUtils;
import cn.xm.vemaxmall.R;
import cn.xm.vemaxmall.base.BaseFragment;
import cn.xm.vemaxmall.utils.GlideImageLoader;


public class HomeFragment extends BaseFragment {

    private View rootView;
    private SwipeRefreshLayout srl;
    private Banner banner;

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
        srl = (SwipeRefreshLayout) rootView.findViewById(R.id.srl);
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



}
