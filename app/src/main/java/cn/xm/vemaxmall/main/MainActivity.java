package cn.xm.vemaxmall.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import cn.xm.vemaxmall.R;
import cn.xm.vemaxmall.base.BaseActivity;
import cn.xm.vemaxmall.ui.HomeFragment;
import cn.xm.vemaxmall.ui.MeFragment;
import cn.xm.vemaxmall.ui.QueryFragment;


public class MainActivity extends BaseActivity {

    private HomeFragment homeFragment;
    private QueryFragment queryFragment;
    private MeFragment meFragment;

    private RadioButton[] btnAry = new RadioButton[3];
    private Fragment[] fragmentAry = null;
    private int currentIndex;
    private int selectedIndex;
    private MyButtonListener myButtonListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        addListener();
    }

    private void initViews() {
        btnAry[0] = findViewById(R.id.radio1);
        btnAry[1] = findViewById(R.id.radio2);
        btnAry[2] = findViewById(R.id.radio3);
        homeFragment = HomeFragment.newInstance();
        meFragment = MeFragment.newInstance();
        queryFragment = QueryFragment.newInstance();
        fragmentAry = new Fragment[]{homeFragment, queryFragment, meFragment};
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, homeFragment);
        fragmentTransaction.show(homeFragment);
        fragmentTransaction.commit();
    }

    private void addListener() {
        myButtonListener = new MyButtonListener();
        for (int i = 0; i < btnAry.length; i++) {
            btnAry[i].setOnClickListener(myButtonListener);
        }
    }

    class MyButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.radio1:
                    selectedIndex = 0;
                    break;
                case R.id.radio2:
                    selectedIndex = 1;
                    break;
                case R.id.radio3:
                    selectedIndex = 2;
                    break;
                    default:
            }
            if (selectedIndex != currentIndex) {
                FragmentTransaction transation = getSupportFragmentManager().beginTransaction();
                transation.hide(fragmentAry[currentIndex]);
                if (!fragmentAry[selectedIndex].isAdded()) {
                    transation.add(R.id.fragment_container, fragmentAry[selectedIndex]);
                }
                transation.show(fragmentAry[selectedIndex]);
                transation.commit();
                btnAry[selectedIndex].setSelected(true);
                btnAry[currentIndex].setSelected(false);
                currentIndex = selectedIndex;
            }
        }
    }

    private boolean isExit = false;

    @Override
    public void onBackPressed() {
        if(isExit){
            super.onBackPressed();
        }else{
            isExit = true;
            showToast("再次点击将退出程序");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        }
    }

}
