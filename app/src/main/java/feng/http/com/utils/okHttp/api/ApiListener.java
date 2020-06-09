package feng.http.com.utils.okHttp.api;

/**
 * Created by feng on 2020/6/5.
 */

public interface ApiListener {

    void success(ApiUtil apiUtil);

    void failure(ApiUtil apiUtil);
}
