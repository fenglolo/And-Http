package feng.http.com.utils.okHttp.api;

import java.util.HashMap;

/**
 * 公共参数
 * Created by feng on 2020/6/5.
 */

public class ApiCommonParams {

    public static HashMap<String ,String > fetchCommonParams(){
        HashMap<String ,String > params = new HashMap<>();
        params.put("client_type","android");
        params.put("uid","11111");
        return params;
    }

}
