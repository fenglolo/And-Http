package feng.http.com.utils.okHttp;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import feng.http.com.utils.okHttp.api.ApiCommonParams;
import feng.http.com.utils.okHttp.callback.OkHttpCallBack;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * OkHttp工具类
 * Created by feng on 2020/6/5.
 */

public class OkHttpUtil {
    private static OkHttpClient mOkHttpClient = null;
    public enum REQUEST_TYPE{
        POST,PUT,DELETE
    }

    public static void init() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient()
                    .newBuilder()
                    .connectTimeout(5000, TimeUnit.MICROSECONDS)
                    .readTimeout(5000, TimeUnit.MICROSECONDS)
                    .writeTimeout(5000, TimeUnit.MICROSECONDS);
            mOkHttpClient = builder.build();
        }
    }

    /**
     * GET请求
     * @param url 请求地址
     * @param okHttpCallBack 回调
     */
    public static void get(String url, OkHttpCallBack okHttpCallBack) {
        Call call = null;
        try {
            HashMap<String, String> commonHasMap = null;
            commonHasMap = ApiCommonParams.fetchCommonParams();

            url = getFinalString(url,commonHasMap);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到追加公共参数的url
     *
     * @param url 请求地址
     * @return 请求的完整拼接地址
     */
    public static String getFinalString(String url, HashMap<String, String> hashMap) {
        try {
            StringBuilder builder = new StringBuilder();
            for (HashMap.Entry<String, String> entry : hashMap.entrySet()) {
                if(builder.length() > 0){
                    builder.append("&");
                }
                builder.append(entry.getKey());
                builder.append("=");
                builder.append(URLEncoder.encode(entry.getValue(), "utf-8"));
            }
            String paramString = builder.toString();
            if (url.contains("?")) {
                url += ("&" + paramString);
            } else {
                url += ("?" + paramString);
            }
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * POST\PUT\DELETE请求，根据传参类型区分
     * @param url 请求地址
     * @param okHttpCallBack 回调
     * @param bodyMap 传参
     * @param type 请求方式 POST PUT DELETE
     */
    public static void sendRequest(String url, OkHttpCallBack okHttpCallBack,
                                   HashMap<String, String> bodyMap, REQUEST_TYPE type) {
        Call call = null;
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (HashMap.Entry<String, String> entry : bodyMap.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            RequestBody body = builder.build();
            Request request = null;
            switch (type){
                case POST:
                    new Request.Builder()
                            .post(body)
                            .url(url)
                            .build();
                    break;
                case PUT:
                    new Request.Builder()
                            .put(body)
                            .url(url)
                            .build();
                    break;
                case DELETE:
                    new Request.Builder()
                            .delete(body)
                            .url(url)
                            .build();
                    break;
            }
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
