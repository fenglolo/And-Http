package feng.http.com.application;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;

import java.util.List;

import feng.http.com.utils.dialog.DialogUtils;
import feng.http.com.utils.ListUtils;
import feng.http.com.utils.okHttp.OkHttpUtil;
import feng.http.com.utils.permission.AndPermissionUtil;

public class AppApplication extends Application {

	public static AppApplication app = null;
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
		init();
	}

    /**
	 * 标准配置
	 */
	private void init() {
		initOkHttp();
		requestPermission();
	}

	/**
	 * 初始化okhttp
	 */
	public void initOkHttp(){
		OkHttpUtil.init();
	}

    /**
     * 动态权限申请
     */
	public void requestPermission(){
//		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {//版本大于安卓9
//			AndPermissionUtil.requestPermission(app.getApplicationContext(),
//					Manifest.permission.WRITE_EXTERNAL_STORAGE,//读写权限
//					new Action<List<String>>() {
//						@Override
//						public void onAction(List<String> data) {
//							Toast.makeText(app,"读写权限申请成功",Toast.LENGTH_SHORT).show();
//						}
//					},
//					new Action<List<String>>() {
//						@Override
//						public void onAction(List<String> data) {
//							if(ListUtils.isEmpty(data)){
//								Toast.makeText(app,"读写权限申请成功",Toast.LENGTH_SHORT).show();
//								return;
//							}
//							if (data.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)){//读写权限
//                                DialogUtils.showDialog(app, "温馨提示", "我们需要:存储权限用于缓存文本图片等信息",
//                                        "确定", "取消",
//                                        new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                AndroidUtil.toSelfSetting(app);
////                                                System.exit(0);
//                                            }
//                                        }, new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
////                                                System.exit(0);
//                                            }
//                                        });
//							}
//						}
//					});
//			return;
//		}
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
			return;
		}
		AndPermissionUtil.requestPermissions(app.getApplicationContext(),
				new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,//读写权限
						Manifest.permission.READ_PHONE_STATE,//获取设备号权限
				},
				new Action<List<String>>() {
					@Override
					public void onAction(List<String> data) {
                        Toast.makeText(app,"权限申请成功",Toast.LENGTH_SHORT).show();
					}
				}, new Action<List<String>>() {
					@Override
					public void onAction(List<String> data) {
						if(ListUtils.isEmpty(data)){
                            Toast.makeText(app,"权限申请成功",Toast.LENGTH_SHORT).show();
							return;
						}
						if(data.size() == 2){
                            DialogUtils.showDialog(app, "温馨提示", "权限申请失败", "确定",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
//                                            System.exit(0);
                                        }
                                    });
						}else {
							if (data.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)){//读写权限
                                DialogUtils.showDialog(app, "温馨提示", "读写权限申请失败", "确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                System.exit(0);
                                            }
                                        });
							}else if(data.contains(Manifest.permission.READ_PHONE_STATE)){//获取设备信息
                                DialogUtils.showDialog(app, "温馨提示", "获取设备信息权限申请失败", "确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                System.exit(0);
                                            }
                                        });
							}
						}
					}
				});
	}

}
