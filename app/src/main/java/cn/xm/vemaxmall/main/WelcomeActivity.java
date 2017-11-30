package cn.xm.vemaxmall.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import cn.lzz.utils.AppPermission;
import cn.lzz.utils.ToastUtils;
import cn.xm.vemaxmall.R;
import cn.xm.vemaxmall.base.BaseActivity;


public class WelcomeActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionCheck();
        } else {
            startApp();
        }
    }

    private void permissionCheck() {
        boolean hasRequest = AppPermission.requestAllUnGrantedPermission(this);
        if (!hasRequest) {
            startApp();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case AppPermission.PERMISSION_REQUEST_CODE:
                String toastTip = null;
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        toastTip = "";
                    }
                }
                if (toastTip != null) {
                    ToastUtils.toastInBottom(this, "部分权限被禁用");
                    ToastUtils.toastInBottom(this, "可能会导致部分功能失效");
                }
                startApp();
                break;
                default:
        }
    }

    private void startApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

}
