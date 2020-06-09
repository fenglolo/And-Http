package feng.http.com.utils.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * dialog工具类
 * Created by feng on 2020/6/4.
 */

public class DialogUtils {

    /**
     * 单个按钮dialog
     * @param context
     * @param title
     * @param msg
     * @param ok_btn
     * @param ok_click
     */
    public static void showDialog(Context context, String title, String msg,
                                  String ok_btn,
                                  DialogInterface.OnClickListener ok_click) {
        // 创建构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 设置参数
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton(ok_btn, ok_click);
        builder.create().show();
    }

    /**
     * 2个按钮dialog
     * @param context
     * @param title
     * @param msg
     * @param ok_btn
     * @param no_btn
     * @param ok_click
     * @param no_click
     */
    public static void showDialog(Context context, String title, String msg,
                           String ok_btn, String no_btn,
                           DialogInterface.OnClickListener ok_click,
                           DialogInterface.OnClickListener no_click) {
        // 创建构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 设置参数
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton(ok_btn, ok_click)
                .setNegativeButton(no_btn, no_click);
        builder.create().show();
    }
}
