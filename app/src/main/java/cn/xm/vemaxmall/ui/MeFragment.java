package cn.xm.vemaxmall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.xm.vemaxmall.R;
import cn.xm.vemaxmall.base.BaseFragment;


public class MeFragment extends BaseFragment {

    private View rootView;
    private SwipeRefreshLayout srl;

    public static MeFragment newInstance(){
        MeFragment meFragment = new MeFragment();
        return meFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.fragment_me, null);
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
    }



}
