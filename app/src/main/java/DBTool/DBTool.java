package DBTool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
/**
 * Created by xiaoxiao on 14-10-28.
 */
public class DBTool extends SQLiteOpenHelper {

//    private static final String dbName = Environment.getExternalStorageDirectory().getPath()+"/"
//            +"ruimt/DB/ruimt.db";

    private static final String dbName = "ruimt.db";

    private static final int version = 1;

    public DBTool(Context context) {
        super(context,dbName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表结构
        db.execSQL("Create table USERINFO (ID varchar,LOGIN_ID varchar,PASSWORD varchar,primary key (ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            db.execSQL("drop table USERINFO");
            this.onCreate(db);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

