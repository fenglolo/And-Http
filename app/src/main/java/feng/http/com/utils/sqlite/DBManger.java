package feng.http.com.utils.sqlite;

import android.content.Context;

import java.util.logging.Handler;

/**
 * Created by feng on 2020/6/10.
 */

public class DBManger {
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context){
        if(helper == null){
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

}
