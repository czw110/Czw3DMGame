package my.qq.com.czw3dmgame.uitls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by czw on 2016/7/5  18:58.
 */
public class HttpUtils {
    /**
     * 网络请求数据
     * @param urlpath 网络地址
     * @return 字节数组
     */
    public static byte[] request(String urlpath){

        //字节输入流
        InputStream is=null;
        //内存缓冲输出流
        ByteArrayOutputStream baos=null;
        try {
            //创建URL对象
            URL url=new URL(urlpath);
            //调用openconnection方法
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            //设置参数
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            //连接
            connection.connect();
            //处理响应
            if(connection.getResponseCode()==200){
                //获得输入流
                is=connection.getInputStream();
                baos=new ByteArrayOutputStream();
                int len=0;
                byte[] buf=new byte[1024*4];
                while((len=is.read(buf))!=-1){
                    baos.write(buf, 0, len);
                }
            }
            return baos.toByteArray();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
