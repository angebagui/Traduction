package com.teachersdunet.android.traduction.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.teachersdunet.android.traduction.model.ResponseData;
import com.teachersdunet.android.traduction.model.Result;

import android.net.Uri;

import com.google.gson.Gson;

public class NetworkOperation {
	  private static final String ENDPOINT = "http://api.mymemory.translated.net/get";
	
	public String getTraduction(String motATraduire){
		String translatedText = null;
		String url = Uri.parse(ENDPOINT).buildUpon()
				.appendQueryParameter("q", motATraduire)
				.appendQueryParameter("langpair", "en|fr")
				.build().toString();
		
		try {
			String jsonResult = getStringUrl(url);
			Gson gson = new Gson();
			Result result = gson.fromJson(jsonResult, Result.class);
			ResponseData data = result.getResponseData();
			translatedText = data.getTranslatedText();
			
			return translatedText;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] getBytes(String urlSpec) throws IOException{
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		try{
		ByteArrayOutputStream out  = new ByteArrayOutputStream();
		InputStream is = connection.getInputStream();
		
		if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
			return null;
		}
		
		int bytesRead = 0;
		byte [] buffer= new byte[1024];
		while((bytesRead = is.read(buffer)) >0){
			out.write(buffer, 0, bytesRead);
			
		}
		out.close();
		return out.toByteArray();
		}finally{
			connection.disconnect();
		}
		
	}
	public String getStringUrl(String url) throws IOException{
		return new String(getBytes(url));
	}
}
