package cn.xm.vemaxmall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.lzz.utils.ResoureUtils;
import cn.xm.vemaxmall.R;
import cn.xm.vemaxmall.base.BaseFragment;
import cn.xm.vemaxmall.view.sidebar.CarBean;
import cn.xm.vemaxmall.view.sidebar.PinyinComparator;
import cn.xm.vemaxmall.view.sidebar.PinyinUtils;
import cn.xm.vemaxmall.view.sidebar.SideBar;
import cn.xm.vemaxmall.view.sidebar.SortAdapter;


public class QueryFragment extends BaseFragment {

    private View rootView;
    private ListView listView;
    private SortAdapter sortadapter;
    private List<CarBean> data;
    private SideBar sidebar;
    private TextView dialog;

    public static QueryFragment newInstance(){
        QueryFragment queryFragment = new QueryFragment();
        return queryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.fragment_query, null);
        initViews();
        return rootView;
    }

    private void initViews() {
        sidebar = rootView.findViewById(R.id.sidebar);
        listView = rootView.findViewById(R.id.listview);
        dialog = rootView.findViewById(R.id.dialog);
        sidebar.setTextView(dialog);
        // 设置字母导航触摸监听
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = sortadapter.getPositionForSelection(s.charAt(0));
                if (position != -1) {
                    listView.setSelection(position);
                }
            }
        });
        data = getData(getResources().getStringArray(R.array.brands));
        // 数据在放在adapter之前需要排序
        Collections.sort(data, new PinyinComparator());
        sortadapter = new SortAdapter(this.getContext(), data);
        listView.setAdapter(sortadapter);
    }

    private List<CarBean> getData(String[] data) {
        List<CarBean> listarray = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            String pinyin = PinyinUtils.getPingYin(data[i]);
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            CarBean carBean = new CarBean();
            carBean.setName(data[i]);
            carBean.setPinYin(pinyin);
            if (Fpinyin.matches("[A-Z]")) {
                carBean.setFirstPinYin(Fpinyin);
            } else {
                carBean.setFirstPinYin("#");
            }

            listarray.add(carBean);
        }
        return listarray;

    }

}
