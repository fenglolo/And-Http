package feng.http.com.utils.permission;

import android.content.Context;
import android.os.Build;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

/**
 * AndPermission权限申请工具类
 * Created by feng on 2019/9/24.
 */

public class AndPermissionUtil {
    public static String TAG = "AndPermissionUtil";

    public static void requestPermission(Context context, String permissions, Action<List<String>> granted,
                                         Action<List<String>> denied){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            AndPermission.with(context)
                    .runtime()
                    .permission(permissions)
                    .onGranted(granted)
                    .onDenied(denied)
                    .start();
        }else {
            granted.onAction(null);
        }
    }

    public static void requestPermissions(Context context, String[] permissions, Action<List<String>> granted,
                                          Action<List<String>> denied){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            AndPermission.with(context)
                    .runtime()
                    .permission(permissions)
                    .onGranted(granted)
                    .onDenied(denied)
                    .start();
        }else {
            granted.onAction(null);
        }
    }
}
