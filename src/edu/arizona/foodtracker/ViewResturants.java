package edu.arizona.foodtracker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class ViewResturants extends Activity implements OnClickListener {

	private Spinner rest;
	private CustomAdapter r_adpt;
	private ArrayList<ItemObject> menu_items;
	private ArrayList<ItemObject> rest_a;
	private ListView menu;
	private Intent in;
	private WebHelper wh;
	public ProgressDialog simpleWaitDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		simpleWaitDialog = ProgressDialog.show( this,"Wait", "Retrieving from Server");
		setContentView(R.layout.activity_view_resturants);
		rest = (Spinner) findViewById(R.id.spinner1);
		menu = (ListView) findViewById(R.id.listView1);
		menu_items = new ArrayList<ItemObject>();
		r_adpt = new CustomAdapter(this, android.R.layout.simple_list_item_1, menu_items);
		in = new Intent(this, ViewFacts.class);
		try {
			wh = new WebHelper(this,simpleWaitDialog,true);
			wh.execute(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		menu.setAdapter(r_adpt);
		menu.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				in.putExtra( "item", ((ItemObject) parent.getItemAtPosition(position)));
				startActivity(in);
				
			}
			
		});
	}

	@Override
	public void onClick(View v) {
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater m = getMenuInflater();

		m.inflate(R.menu.main_menu, menu);
		SubMenu sub = menu.getItem(0).getSubMenu();
		return true;
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.item1){
			SubMenu sub = item.getSubMenu();
			sub.clear();
			for (int i = 0; i < rest_a.size(); i++) {
				if (rest_a.get(i).toString().compareTo(rest.getSelectedItem().toString()) != 0)
					sub.add(Menu.NONE, i, Menu.NONE, rest_a.get(i).toString());
			}
			
			return true;
		}
		else if(item.getItemId() == R.id.item3){
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> color = new ArrayList<String>();
			for (int i = 0; i < rest_a.size(); i++) {
				list.add(rest_a.get(i).toString());
			}
			Intent in = new Intent(this, MapActivity.class);
			in.putStringArrayListExtra("rest", list);
			startActivity(in);
			
			return true;
		}
		else if ( item.getItemId() == R.id.item2 ){
			Intent intent = new Intent( this, FilterActivity.class );
			intent.putExtra( "items", menu_items);
			startActivity(intent);		
			return true;
		}
		else if ( item.getItemId() == R.id.item4 ){
			Restaurant temp = (Restaurant)rest.getSelectedItem();
			Intent viewURL = new Intent(this, WebActivity.class );
			viewURL.putExtra( "url" , temp.getURL() );
			startActivity( viewURL );		
			return true;
		}
		else {
			//Intent in = new Intent(this, CompareActivity.class);
			//in.putExtra("name1", rest_a.get(rest.getSelectedItemPosition()).toString());
			//in.putExtra("id1", ((Restaurant) rest_a.get(rest.getSelectedItemPosition())).getID());
			//in.putExtra("name2", item.getTitle());
			//in.putExtra("id2", ((Restaurant) rest_a.get(item.getItemId())).getID());
			//startActivity(in);
			
			Intent in = new Intent(this, CompareActivity.class);
			in.putExtra("name1", rest_a.get(rest.getSelectedItemPosition()).toString());
			in.putExtra("id1", ((Restaurant) rest_a.get(rest.getSelectedItemPosition())).getID());
			in.putExtra( "rest1" , ((Restaurant) rest_a.get(rest.getSelectedItemPosition())) );
			in.putExtra("name2", item.getTitle());
			in.putExtra("id2", ((Restaurant) rest_a.get(item.getItemId())).getID());
			in.putExtra( "rest2" , ((Restaurant) rest_a.get(item.getItemId())));
			startActivity(in);			
		}
		return false;
	}
	
	public void executeAfterAgain()
	{
		simpleWaitDialog.dismiss();
		ArrayAdapter<ItemObject> adpt = new ArrayAdapter<ItemObject>(this, android.R.layout.simple_spinner_item, rest_a);
		adpt.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		rest.setAdapter(adpt);
		for ( ItemObject obj : rest_a )
		{
			System.out.println( obj );
			System.out.println( ((  Restaurant ) obj ).getColor() );
		}
		final ViewResturants main = this;
		rest.setOnItemSelectedListener(new OnItemSelectedListener(){
	
			@SuppressLint("NewApi")
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position,
					long id) {
				WebHelper w;
				menu_items.clear();
				try {
					//w = new WebHelper(main,simpleWaitDialog);
					//w.execute((( Restaurant )(parent.getItemAtPosition(position))).getID() );
					//w.get();
				
					ArrayList<ItemObject> m = (( Restaurant )(parent.getItemAtPosition(position))).getItems();
					menu_items.addAll(m);
					r_adpt.notifyDataSetChanged();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
	
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Button filterButton = (Button) findViewById( R.id.filterButton );
		filterButton.setOnClickListener(new OnClickListener(){
	
			@Override
			public void onClick(View v) {
				Intent intent = new Intent( main, FilterActivity.class );
				intent.putExtra( "items", menu_items);
				startActivity(intent);				
				
			}
		
		});		
	
		Button urlViewer = (Button) findViewById( R.id.viewWeb );
		urlViewer.setOnClickListener(new OnClickListener()
		{
	
			@Override
			public void onClick(View v) 
			{
				Restaurant temp = (Restaurant)rest.getSelectedItem();
				Intent viewURL = new Intent(main, WebActivity.class );
				viewURL.putExtra( "url" , temp.getURL() );
				startActivity( viewURL );
			}
			
		}
		);
		
		menu.setAdapter(r_adpt);
		menu.setOnItemClickListener(new OnItemClickListener(){
	
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//in.putExtra("name", ((ItemObject) parent.getItemAtPosition(position)).toString());
				in.putExtra( "item", ((ItemObject) parent.getItemAtPosition(position)));
				startActivity(in);
				
			}
			
		});		
	}
	
	public void executeAfter()
	{
		rest_a = wh.getObjects();
		for ( ItemObject object : rest_a )
		{
			Restaurant item = ( Restaurant ) object;
			wh = new WebHelper(this,simpleWaitDialog,false);
			wh.execute( item.getID() );
			try {
				wh.get();
				item.withItems( wh.getObjects() );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		
		executeAfterAgain();
	}	
	
}
