package feng.http.com.activity.http;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import feng.http.com.Api.okhttp.GetRequestApi;
import feng.http.com.Api.okhttp.RequestApi;
import feng.http.com.R;
import feng.http.com.consts.UrlConstans;
import feng.http.com.entity.uesr.UserInfoVo;
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
 * OkHttp测试类
 * Created by feng on 2020/6/5.
 */

public class OkhttpActivity extends Activity implements View.OnClickListener {

    private Button btn_post, btn_get, btn_put, btn_delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
        initAction();
    }

    private void initView() {
        btn_post = findViewById(R.id.btn_post);
        btn_get = findViewById(R.id.btn_get);
        btn_put = findViewById(R.id.btn_put);
        btn_delete = findViewById(R.id.btn_delete);
    }

    private void initAction() {
        btn_post.setOnClickListener(this);
        btn_get.setOnClickListener(this);
        btn_put.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_post:
                //demo1：原生
                useOkhttpSendRequest();
                //demo2：封装
                sendPostRequest();
                break;
            case R.id.btn_get:
                //封装
                sendGetRequest();
                break;
            case R.id.btn_put:
                //封装
                sendPutRequest();
                break;
            case R.id.btn_delete:
                //封装
                sendDeleteRequest();
                break;
            default:
                break;
        }
    }

    /**
     * 原生okhttp请求，未封装
     */
    private void useOkhttpSendRequest() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("title", "111")
                .add("msg", "222")
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(UrlConstans.URL)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responsbody = response.body().toString();
                }
            }
        });
    }

    //------------------------------post、put、delete、get请求-------------------------------------

    private void sendPostRequest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "15");
        new RequestApi(UrlConstans.URL, map)
                .sendRequest(new ApiListener() {
                    @Override
                    public void success(ApiUtil apiUtil) {
                        RequestApi api = (RequestApi) apiUtil;
                        UserInfoVo userInfoVo = new Gson().fromJson(api.mResponse, UserInfoVo.class);
                    }

                    @Override
                    public void failure(ApiUtil apiUtil) {

                    }
                }, OkHttpUtil.REQUEST_TYPE.POST);
    }

    private void sendPutRequest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "15");
        new RequestApi(UrlConstans.URL, map)
                .sendRequest(new ApiListener() {
                    @Override
                    public void success(ApiUtil apiUtil) {
                        RequestApi api = (RequestApi) apiUtil;
                        UserInfoVo userInfoVo = new Gson().fromJson(api.mResponse, UserInfoVo.class);
                    }

                    @Override
                    public void failure(ApiUtil apiUtil) {

                    }
                }, OkHttpUtil.REQUEST_TYPE.PUT);
    }

    private void sendDeleteRequest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "15");
        new RequestApi(UrlConstans.URL, map)
                .sendRequest(new ApiListener() {
                    @Override
                    public void success(ApiUtil apiUtil) {
                        RequestApi api = (RequestApi) apiUtil;
                        UserInfoVo userInfoVo = new Gson().fromJson(api.mResponse, UserInfoVo.class);
                    }

                    @Override
                    public void failure(ApiUtil apiUtil) {

                    }
                }, OkHttpUtil.REQUEST_TYPE.DELETE);
    }

    private void sendGetRequest() {
        new GetRequestApi(UrlConstans.URL)
                .get(new ApiListener() {
                    @Override
                    public void success(ApiUtil apiUtil) {
                        GetRequestApi api = (GetRequestApi) apiUtil;
                        UserInfoVo userInfoVo = new Gson().fromJson(api.mResponse, UserInfoVo.class);
                    }

                    @Override
                    public void failure(ApiUtil apiUtil) {

                    }
                });
    }
}
