package feng.http.com.utils.okHttp.api;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import feng.http.com.utils.okHttp.OkHttpUtil;
import feng.http.com.utils.okHttp.callback.OkHttpCallBack;
import okhttp3.Call;

/**
 * 发送请求api工具类
 * Created by feng on 2020/6/5.
 */

public abstract class ApiUtil {

    private ApiListener apiListener = null;
    private String mStatus;

    private OkHttpCallBack okHttpCallBack = new OkHttpCallBack() {
        @Override
        public void onSuccess(Call call, JSONObject jsonObject) {
            mStatus = jsonObject.optString("status");
            if(isSuccess()){
                try {
                    parseData(jsonObject);
                    apiListener.success(ApiUtil.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                apiListener.failure(ApiUtil.this);
            }
        }

        @Override
        public void onFailure(Call call, IOException e) {
            apiListener.failure(ApiUtil.this);
        }
    };

    public boolean isSuccess(){
        return "0".equals(mStatus) || "200".equals(mStatus);
    }

    protected abstract void parseData(JSONObject jsonObject) throws Exception;

    protected abstract String getUrl();

    /**
     * 发送get请求
     * @param listener 告诉客户端请求是否成功
     */
    public void get(ApiListener listener){
        apiListener = listener;
        OkHttpUtil.get(getUrl(), okHttpCallBack);
    }

    private HashMap<String,String> bodyMap = new HashMap<>();

    /**
     * 添加传入参数
     * @param key 约定的key
     * @param value 数据
     */
    public void addParams(String key,String value){
        bodyMap.put(key,value);
    }

    /**
     * 发送post、put、delete请求
     * @param listener 告诉客户端请求是否成功
     */
    public void sendRequest(ApiListener listener, OkHttpUtil.REQUEST_TYPE type){
        apiListener = listener;
        OkHttpUtil.sendRequest(getUrl(), okHttpCallBack, bodyMap, type);
    }
}
