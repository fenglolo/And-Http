package feng.http.com.utils.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HttpURLConnection 工具类
 * Created by feng on 2020/6/3.
 */

public class HttpUtil {

    //-------------------------------GET---------------------------------------------
    /**
     *返回该url对应的网址内容
     * @param sengUrl
     * @return
     */
    public static String sendUrl(String sengUrl){
        try {
            URL url = new URL(sengUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            InputStream stream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = reader.readLine()) != null){
                buffer.append(str);
            }
            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *返回该url的图片内容
     * @param sengUrl
     * @return
     */
    public static Bitmap loadImg(String sengUrl){
        try {
            URL url = new URL(sengUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            InputStream stream = conn.getInputStream();

            String fileName = String.valueOf(System.currentTimeMillis());
            FileOutputStream outputStream = null;
            File fileDownload = null;
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File parent = Environment.getExternalStorageDirectory();
                fileDownload = new File(parent,fileName);
                outputStream = new FileOutputStream(fileDownload);
            }

            byte[] bytes = new byte[2*1024];
            int lens;
            if(outputStream != null){
                while ((lens = stream.read(bytes)) != -1){
                    outputStream.write(bytes,0,lens);
                }
                return BitmapFactory.decodeFile(fileDownload.getAbsolutePath());
            }else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //-------------------------------POST---------------------------------------------
    public static String post(String strUrl,String params){
        try {
            URL postUrl = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) postUrl.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            //设置请求体的类型为文本类型   默认就是文本类型
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            //设置请求体的长度
            conn.setRequestProperty("Content-Type",String.valueOf(params.getBytes()));

            //获得输出流，向服务器写入数据
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(params.getBytes());

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream stream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String str = null;
                while ((str = reader.readLine()) != null){
                    buffer.append(str);
                }
                return buffer.toString();
            }else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //-------------------------------PUT---------------------------------------------
    public static String put(String strUrl,String params){
        try {
            URL postUrl = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) postUrl.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("PUT");

            //获得输出流，向服务器写入数据
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(params.getBytes());

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream stream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String str = null;
                while ((str = reader.readLine()) != null){
                    buffer.append(str);
                }
                return buffer.toString();
            }else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //-------------------------------DELETE---------------------------------------------
    public static String delete(String strUrl,String params){
        try {
            URL postUrl = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) postUrl.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("DELETE");

            //获得输出流，向服务器写入数据
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(params.getBytes());

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream stream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String str = null;
                while ((str = reader.readLine()) != null){
                    buffer.append(str);
                }
                return buffer.toString();
            }else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
