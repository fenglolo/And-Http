package feng.http.com.utils.okHttp.callback;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * OkHttpCallBack封装工具类
 * Created by feng on 2020/6/5.
 */

public abstract class OkHttpCallBack implements Callback{

    public abstract void onSuccess(final Call call, JSONObject jsonObject);

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(response != null){
            if(response.isSuccessful()){
                try {
                    String str = response.body().string().trim();
                    JSONObject object = (JSONObject) new JSONTokener(str).nextValue();
                    if(object != null){
                        onSuccess(call,object);
                    }else {
                        onFailure(call,null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                onFailure(call,null);
            }
        }
    }
}
