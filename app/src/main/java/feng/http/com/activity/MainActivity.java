package feng.http.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import feng.http.com.R;
import feng.http.com.activity.http.HttpActivity;
import feng.http.com.activity.http.OkhttpActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button btn_get,btn_okhttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAction();
    }

    private void initView(){
        btn_get = findViewById(R.id.btn_get);
        btn_okhttp = findViewById(R.id.btn_okhttp);
    }

    private void initAction(){
        btn_get.setOnClickListener(this);
        btn_okhttp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get://Http
                startActivity(new Intent(MainActivity.this,HttpActivity.class));
                break;
            case R.id.btn_okhttp://OkHttp
                startActivity(new Intent(MainActivity.this,OkhttpActivity.class));
                break;
            default:
                break;
        }
    }


}
