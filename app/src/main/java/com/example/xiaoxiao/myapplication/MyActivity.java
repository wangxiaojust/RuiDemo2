package com.example.xiaoxiao.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import DBTool.RuiMTDBService;
import Service.UserInfoService;
import pojo.UserInfo;


public class MyActivity extends Activity {

    private EditText username,password;
    private Button loginbtn,registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my);
        init();
        findView();
        addlistener();
    }

    private void init(){
        String dir = Environment.getExternalStorageDirectory().getPath()+"/"+"ruimt/DB";
        File pathdb = new File(dir);
        if(!pathdb.exists()){
            pathdb.mkdirs();
        }
    }

    private void findView(){
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginBtn);
        registerbtn = (Button) findViewById(R.id.registerBtn);

    }

    private void addlistener(){
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user1 = username.getText().toString().trim();
                String pasd1 = password.getText().toString().trim();
                if(user1.equals("")){
                    showMessage("请输入用户名");
                    return;
                }
                if (pasd1.equals("")){
                    showMessage("请输入密码");
                    return;

                }
                UserInfo userInfo = new UserInfo();
                RuiMTDBService db = new RuiMTDBService(MyActivity.this);
                UserInfoService service = new UserInfoService(db.getDB());
                userInfo = service.queryByInfo(user1,pasd1);

                if(userInfo == null){
                    showMessage("登录失败，请检查用户名或密码");
                }else{
                    showMessage("登录成功");
                    startActivity(new Intent(MyActivity.this,MainActivity.class));

                }
                db.CloseDB();

            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至注册页面

                startActivity(new Intent(MyActivity.this,RegisterActivity.class));


            }
        });
    }

    private void showMessage (String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
