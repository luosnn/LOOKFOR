package com.ylylss.lookfor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import cn.bmob.v3.Bmob;

/**
 * Created by 罗什什 on 2017/11/24.
 */



public class Welcome extends Activity {
    Handler handler = new Handler();
    final int TIME = 1000;
    final int NUM = 4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        //初始化SDK
        Bmob.initialize(this, "e63b1892c55843668313aadabee6ea83");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity( new Intent(Welcome.this,Login.class));
                finish();
            }
        },TIME*NUM);
    }
}
