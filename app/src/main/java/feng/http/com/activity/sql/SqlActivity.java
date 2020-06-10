package feng.http.com.activity.sql;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import feng.http.com.R;
import feng.http.com.utils.sqlite.DBManger;
import feng.http.com.utils.sqlite.MySqliteHelper;

/**
 * Sql测试类
 * Created by feng on 2020/6/9.
 */

public class SqlActivity extends Activity implements View.OnClickListener {

    private Button btn_creat;

    private MySqliteHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        initView();
        initAction();

        helper = DBManger.getInstance(this);
    }

    private void initView() {
        btn_creat = findViewById(R.id.btn_creat);
    }

    private void initAction() {
        btn_creat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_creat://点击按钮，创建数据库
                /**
                 * getReadableDatabase()getWritableDatabase()
                 * 创建或者打开数据库
                 * 如果数据库不存在则是创建数据库，如果数据库存在直接打开数据库
                 * 默认情况下两个函数都表示打开或者创建可读可写的数据库对象
                 * 如果磁盘已满或者数据库本身权限等情况下getReadableDatabase()打开的是只读数据库
                 */
                SQLiteDatabase db = helper.getWritableDatabase();

                break;
            default:
                break;
        }
    }
}
