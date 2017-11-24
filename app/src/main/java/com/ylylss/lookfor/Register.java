package com.ylylss.lookfor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 罗什什 on 2017/11/24.
 */

public class Register extends Activity implements View.OnClickListener {
    private EditText r_user;
    private EditText r_email;
    private EditText r_pass;
    private Button r_r;
    private Button help;
    private ProgressBar r_pro;
    private String r_u;
    private String r_p;
    private String r_e;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        r_user = findViewById(R.id.register_user);
        r_pass = findViewById(R.id.register_pass);
        r_email = findViewById(R.id.register_email);
        r_r = findViewById(R.id.register);
        r_r.setOnClickListener(this);
        help = findViewById(R.id.help);
        help.setOnClickListener(this);
        r_pro = findViewById(R.id.register_pro);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                r_pro.setVisibility(View.VISIBLE);
                r_u = r_user.getText().toString().trim();
                r_p = r_pass.getText().toString().trim();
                r_e = r_email.getText().toString().trim();
                if (r_u == null) {
                    Toast.makeText(Register.this, "对不起，请输入有效ID账号", Toast.LENGTH_LONG).show();
                    r_pro.setVisibility(View.GONE);
                    return;
                } else if (r_p == null || r_p.length() < 6) {
                    Toast.makeText(Register.this, "对不起，请输入有效ID密码", Toast.LENGTH_LONG).show();
                    r_pro.setVisibility(View.GONE);
                    return;
                } else if (r_p == null && r_u == null) {
                    Toast.makeText(Register.this, "对不起，请输入有效ID账号和密码", Toast.LENGTH_LONG).show();
                    r_pro.setVisibility(View.GONE);
                    return;
                } else {
                User user = new User();
                user.setUsername(r_u);
                user.setPassword(r_p);
                user.setEmail(r_e);
                user.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e!=null){
                            r_pro.setVisibility(View.GONE);
                            Toast.makeText(Register.this, "恭喜您成功注册财大失物招领平台", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,Login.class));
                            finish();
                        }else {
                            r_pro.setVisibility(View.GONE);
                            Toast.makeText(Register.this, "对不起，注册失败，请稍后再试"+e.toString(), Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
                }
                break;
            case R.id.help:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this).setMessage("设置邮箱方便忘记密码时找回，财大开发者不会对您进行骚扰");
                break;

        }
    }
}
