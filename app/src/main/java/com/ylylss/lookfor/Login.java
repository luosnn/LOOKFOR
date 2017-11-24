package com.ylylss.lookfor;

import android.app.Activity;
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

public class Login extends Activity implements View.OnClickListener {
    private EditText l_user;
    private EditText l_pass;
    private Button l_login;
    private Button l_forget;
    private Button l_register;
    private String b_u;
    private String b_p;
    private String b_e;
    private ProgressBar login_pro;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        l_user = findViewById(R.id.login_user);
        l_pass = findViewById(R.id.login_pass);
        l_login = findViewById(R.id.login_login);
        l_login.setOnClickListener(this);
        l_forget = findViewById(R.id.forget_pass);
        l_forget.setOnClickListener(this);
        l_register = findViewById(R.id.no_id);
        l_register.setOnClickListener(this);
        login_pro = findViewById(R.id.login_pro);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login:
                login_pro.setVisibility(View.VISIBLE);
                b_u = l_user.getText().toString().trim();
                b_p = l_pass.getText().toString().trim();
                if (b_u==null){
                    Toast.makeText(Login.this, "对不起，请输入有效ID账号" , Toast.LENGTH_LONG).show();
                    login_pro.setVisibility(View.GONE);
                    return;
                }else if(b_p==null||b_p.length()<6) {
                    Toast.makeText(Login.this, "对不起，请输入有效ID密码" , Toast.LENGTH_LONG).show();
                    login_pro.setVisibility(View.GONE);
                    return;
                }
                else if(b_p==null&&b_u==null) {
                    Toast.makeText(Login.this, "对不起，请输入有效ID账号和密码" , Toast.LENGTH_LONG).show();
                    login_pro.setVisibility(View.GONE);
                    return;
                }else {
                    User myuser = new User();
                    myuser.setUsername(b_u);
                    myuser.setPassword(b_p);
                    myuser.login(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e != null) {
                                login_pro.setVisibility(View.GONE);
                                startActivity(new Intent(Login.this, Main.class));
                                finish();
                            } else {
                                login_pro.setVisibility(View.GONE);
                                Toast.makeText(Login.this, "对不起，登录失败" + e.toString(), Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    });
                }
                 break;
            case R.id.forget_pass:
                startActivity( new Intent(Login.this,Forget_PASS.class));
                break;
            case R.id.no_id:
                startActivity( new Intent(Login.this,Register.class));
                break;
        }
    }
}
