package DBTool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by xiaoxiao on 14-10-27.
 */
public class RuiMTDBService {

    private DBTool tool = null;
    private SQLiteDatabase db = null;

    public RuiMTDBService(Context context){
        tool = new DBTool(context);
    }

    public SQLiteDatabase getDB(){
        if(null == this.db){
            this.db = tool.getWritableDatabase();
        }
        return this.db;
    }

    public void CloseDB(){
        if(null != this.db){
            this.db.close();
        }
    }
}

