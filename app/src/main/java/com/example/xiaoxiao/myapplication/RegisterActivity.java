package com.example.xiaoxiao.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiaoxiao.myapplication.R;

import DBTool.RuiMTDBService;
import Service.UserInfoService;
import pojo.UserInfo;

public class RegisterActivity extends Activity {


    private EditText username,password,surepwd;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        findView();
        addlistener();
    }

    private void findView(){

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        surepwd = (EditText) findViewById(R.id.surepwd);
        registerBtn = (Button) findViewById(R.id.registerBtn);
    }

    private void addlistener(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = username.getText().toString().trim();
                String password1 = password.getText().toString().trim();
                String surepwd1 = surepwd.getText().toString().trim();

                if(username1.equals("")){
                    showMessage("请输入用户名！");
                    return;
                }
                if(!password1.equals(surepwd1)){
                    showMessage("两次密码输入不一致！");
                    return;
                }
                if(password1.equals("")){
                    showMessage("请输入密码！");
                    return;
                }
                if(surepwd1.equals("")){
                    showMessage("请输入确认密码！");
                    return;
                }

                UserInfo userInfo = new UserInfo();

                RuiMTDBService db = new RuiMTDBService(RegisterActivity.this);
                UserInfoService service = new UserInfoService(db.getDB());
                userInfo = service.queryByUser(username1);
                if (userInfo != null){
                    showMessage("该用户名已经存在，请重新填写！");
                    return;
                }


                try{
                    UserInfo userInfo1 = new UserInfo();
                    userInfo1.setId(username1);
                    userInfo1.setLoginId(username1);
                    userInfo1.setPassword(password1);
                    service.insertUserInfo(userInfo1);
                    db.CloseDB();
                    showMessage("注册成功！");
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                }catch (Exception e){
                    e.printStackTrace();
                    showMessage("注册失败！");
                }


            }
        });

    }

    private void showMessage (String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
