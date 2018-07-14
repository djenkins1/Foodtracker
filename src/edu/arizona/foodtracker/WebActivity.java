package edu.arizona.foodtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends Activity 
{
	public void onCreate(Bundle savedInstanceState) 
	{
		Intent intent = getIntent();
		String url = intent.getStringExtra( "url" );
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		final WebView wv = (WebView) findViewById(R.id.web_holder);
		wv.loadUrl( url );
	}	
}
