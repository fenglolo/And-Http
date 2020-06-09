package feng.http.com.Api.okhttp;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import feng.http.com.utils.okHttp.api.ApiCommonParams;
import feng.http.com.utils.okHttp.api.ApiUtil;

/**
 * 发送post、put、delete请求的api
 * Created by feng on 2020/6/5.
 */

public class RequestApi extends ApiUtil{

    public String mResponse;
    public String mUrl;

    /**
     * 传参
     * @param url 请求地址
     * @param hashMap 参数
     */
    public RequestApi(String url, HashMap<String, String > hashMap) {
        mUrl = url;
        //添加公共参数
        for(Map.Entry<String, String> comm: ApiCommonParams.fetchCommonParams().entrySet()){
            addParams(comm.getKey(),comm.getValue());
        }
        //添加 hashMap参数
        for(Map.Entry<String, String> entry: hashMap.entrySet()) {
            addParams(entry.getKey(),entry.getValue());
        }
    }

    @Override
    protected String getUrl() {
        return mUrl;
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        mResponse = jsonObject.toString();
    }
}
