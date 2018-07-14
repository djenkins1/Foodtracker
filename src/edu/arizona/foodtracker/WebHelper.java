package edu.arizona.foodtracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class WebHelper extends  AsyncTask <Integer, Void, Void>{
	
	private ObjectParser parser;
	
	private ProgressDialog simpleWaitDialog;
	
	private Activity main;
	
	private boolean executeAfter;
	
	public WebHelper( Activity main, ProgressDialog dialog, boolean executeAfter )
	{
		this.simpleWaitDialog = dialog;
		parser = new ObjectParser();
		this.main = main;
		this.executeAfter = executeAfter;
	}
	
	@Override
	protected void onPreExecute() 
	{
		super.onPreExecute();
	}	
	
	public Void download(Integer... params) {
		HttpURLConnection file;
		try {
			System.out.println(params[0]);
			if (params[0] == -1){
			file = (HttpURLConnection) new URL("http://52.11.41.33").openConnection();
			}
			else {
				file = (HttpURLConnection) new URL("http://52.11.41.33?restaurant=" + params[0]).openConnection();
			}
		    BufferedReader in;
			in = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line;
			while((line = in.readLine()) != null){
				parser.updateObjectsFromOutput(line);
				System.out.println(line);
			}
			in.close();
		}
		catch (Exception e) {}
		parser.print();
		return null;
	}
	
	@Override
	protected void onPostExecute( Void result) 
	{
		super.onPostExecute(result);
		if ( executeAfter )
		{
			((ViewResturants) main).executeAfter();
		}	
	}	
	
	protected Void doInBackground(Integer... params) {
		HttpURLConnection file;
		try {
			System.out.println(params[0]);
			if (params[0] == -1){
			file = (HttpURLConnection) new URL("http://52.11.41.33").openConnection();
			}
			else {
				file = (HttpURLConnection) new URL("http://52.11.41.33?restaurant=" + params[0]).openConnection();
			}
		    BufferedReader in;
			in = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line;
			while((line = in.readLine()) != null){
				parser.updateObjectsFromOutput(line);
			}
			in.close();
		}
		catch (Exception e) {}
		return null;
	}
	
	public ArrayList<ItemObject> getObjects()
	{
		return parser.getObjects();
	}
	
}
