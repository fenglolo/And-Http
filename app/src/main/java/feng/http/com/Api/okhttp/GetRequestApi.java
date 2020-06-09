package feng.http.com.Api.okhttp;

import org.json.JSONObject;

import feng.http.com.utils.okHttp.api.ApiUtil;

/**
 * 发送get请求的api
 * Created by feng on 2020/6/5.
 */

public class GetRequestApi extends ApiUtil{

    public String mResponse;
    public String mUrl;

    /**
     * 传参
     * @param url 请求地址
     */
    public GetRequestApi(String url) {
        this.mUrl = url;
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
