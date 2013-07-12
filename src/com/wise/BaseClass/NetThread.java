package com.wise.BaseClass;

import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 多线程操作类
 * @author honesty
 */
public class NetThread {
	private static String TAG ="NetThread";
	public static class postDataThread extends Thread{
		Handler handler;
		String url;
		int what;
		List<NameValuePair> params;
		public postDataThread(Handler handler,String url,List<NameValuePair> params,int what){
			this.handler = handler;
			this.url = url;
			this.what = what;
			this.params = params;
		}
		@Override
		public void run() {
			super.run();
			HttpPost httpPost = new HttpPost(url);
			try {
				 httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				 HttpClient client = new DefaultHttpClient();
				 client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
				 client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
				 HttpResponse httpResponse = client.execute(httpPost);
				 if(httpResponse.getStatusLine().getStatusCode() == 200){
					 String strResult = EntityUtils.toString(httpResponse.getEntity());
					 Message message = new Message();
					 message.what = what;
					 message.obj = strResult;
					 handler.sendMessage(message);
				 }else{
					 Log.d(TAG, "状态" +httpResponse.getStatusLine().getStatusCode());
				 }
			} catch (Exception e) {
				Message message = new Message();
				message.what = what;
				message.obj = "";
				handler.sendMessage(message);
				e.printStackTrace();
			}
		}
	}
}