package feng.http.com.activity.http;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import feng.http.com.Api.okhttp.GetRequestApi;
import feng.http.com.Api.okhttp.RequestApi;
import feng.http.com.R;
import feng.http.com.activity.MainActivity;
import feng.http.com.consts.UrlConstans;
import feng.http.com.entity.uesr.UserInfoVo;
import feng.http.com.utils.http.HttpUtil;
import feng.http.com.utils.okHttp.OkHttpUtil;
import feng.http.com.utils.okHttp.api.ApiListener;
import feng.http.com.utils.okHttp.api.ApiUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Http测试类
 * Created by feng on 2020/6/9.
 */

public class HttpActivity extends Activity implements View.OnClickListener {

    private Button btn_get;
    private WebView webView;
    private ImageView imageView;

    private SendurlTask sendurlTask;
    private LoadImgTask loadImgTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        initView();
        initAction();
    }

    private void initView() {
        btn_get = findViewById(R.id.btn_get);
        webView = findViewById(R.id.wv);
        imageView = findViewById(R.id.img);
    }

    private void initAction() {
        btn_get.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sendurlTask != null){
            sendurlTask.cancel(true);
        }
        if(loadImgTask != null){
            loadImgTask.cancel(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                sendurlTask = new SendurlTask("http://www.baidu.com");//http://www.baidu.com
                sendurlTask.execute();

                loadImgTask = new LoadImgTask("http://img2.sycdn.imooc.com/533e4d510001c2ad02000200-140-140.jpg");
                loadImgTask.execute();
                break;
            default:
                break;
        }
    }

    private class SendurlTask extends AsyncTask<Void,Void,String> {
        String mUrl;

        public SendurlTask(String url) {
            mUrl = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //做网络请求操作
            return HttpUtil.sendUrl(mUrl);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            webView.loadData(s,"text/html;charset=utf-8",null);
        }
    }

    private class LoadImgTask extends AsyncTask<Void,Void,Bitmap>{
        String mUrl;

        public LoadImgTask(String url) {
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            //做网络请求操作
            return HttpUtil.loadImg(mUrl);
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            if(s != null){
                imageView.setImageBitmap(s);
            }
        }
    }
}
