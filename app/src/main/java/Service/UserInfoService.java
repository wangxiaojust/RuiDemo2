package Service;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pojo.UserInfo;

/**
 * Created by wangxiao on 14-10-27.
 */
public class UserInfoService {

    private SQLiteDatabase db = null;
    public UserInfoService(SQLiteDatabase db){
        this.db = db;
    }

    //注册；
    public void insertUserInfo(UserInfo userInfo){
        db.execSQL("insert into USERINFO (ID,LOGIN_ID,PASSWORD) values (?,?,?)",new Object[]{
                userInfo.getId(),userInfo.getLoginId(),userInfo.getPassword()
        });
    }

    //查询全部
    public List<UserInfo> queryAll(){
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        Cursor cursor = db.rawQuery("select * from USERINFO",null);
        while (cursor.moveToNext()){
            UserInfo userInfo = new UserInfo(cursor.getString(0),cursor.getString(1),cursor.getString(2));

            userInfos.add(userInfo);
        }
        cursor.close();
        return userInfos;

    }

    //登录－－根据用户名和密码
    public UserInfo queryByInfo(String loginId,String password){
        UserInfo userInfo = null;
        Cursor cursor = db.rawQuery("select * from USERINFO where LOGIN_ID = ? and PASSWORD = ?",new
                String[]{loginId,password});
        while (cursor.moveToNext()){
            userInfo = new UserInfo( cursor.getString(0),cursor.getString(1),cursor.getString(2));
        }
        cursor.close();
        return userInfo;
    }



}

