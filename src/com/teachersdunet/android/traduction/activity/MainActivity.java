package com.teachersdunet.android.traduction.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teachersdunet.android.traduction.R;
import com.teachersdunet.android.traduction.api.NetworkOperation;

public class MainActivity extends Activity{
	public static final String TAG = "Traduction";
	private Button btnTraduire;
	private EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnTraduire = (Button) findViewById(R.id.btnTraduire);
		editText = (EditText)findViewById(R.id.motATraduire);
		
		btnTraduire.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String textEntrer = editText.getText().toString();
				
				new FetchTraduction().execute(textEntrer);
			}
		});
	}
	
	private class FetchTraduction extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String q = params[0];
			return new NetworkOperation().getTraduction(q);
		}

		@Override
		protected void onPostExecute(String result) {
			if(result !=null){
				
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
			}
			
		}
		
		
		
	}
}
