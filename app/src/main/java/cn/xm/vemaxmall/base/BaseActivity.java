package cn.xm.vemaxmall.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import cn.lzz.utils.ToastUtils;
import cn.xm.vemaxmall.view.loadingdialog.LoadingDialog;


public class BaseActivity extends FragmentActivity {

    LoadingDialog loadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void showLoadingDialog(final String tips){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingDialog = new LoadingDialog(BaseActivity.this);
                if(TextUtils.isEmpty(tips)){
                    loadingDialog.setLoadingText("加载中，请稍候");
                }else{
                    loadingDialog.setLoadingText(tips);
                }
                loadingDialog.show();
            }
        });
    }

    public void dismissLoadingDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loadingDialog != null){
                    loadingDialog.dismiss();
                }
            }
        });
    }

    public void showToast(String tips) {
        ToastUtils.toastInBottom(this, tips);
    }

    public void showToastCenter(String tips) {
        ToastUtils.toastInCenter(this, tips);
    }

}
