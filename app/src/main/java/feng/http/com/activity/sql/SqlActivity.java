package feng.http.com.activity.sql;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import feng.http.com.R;
import feng.http.com.utils.http.HttpUtil;

/**
 * Sql测试类
 * Created by feng on 2020/6/9.
 */

public class SqlActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        initView();
        initAction();
    }

    private void initView() {

    }

    private void initAction() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                break;
            default:
                break;
        }
    }
}
