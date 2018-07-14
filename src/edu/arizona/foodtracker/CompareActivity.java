package edu.arizona.foodtracker;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CompareActivity extends Activity {

	private TextView rest_1, rest_2;
	private ListView view_1, view_2;
	private ArrayList<ItemObject> menu_1, menu_2;
	private CustomAdapter list_1, list_2;
	private Intent data;
	public ProgressDialog simpleWaitDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compare);
		data = getIntent();
		rest_1 = (TextView) findViewById(R.id.textView1);
		rest_2 = (TextView) findViewById(R.id.textView2);
		rest_1.setText(data.getStringExtra("name1"));
		rest_2.setText(data.getStringExtra("name2"));
		view_1 = (ListView) findViewById(R.id.listView1);
		view_2 = (ListView) findViewById(R.id.listView2);
		Restaurant first = ( Restaurant ) data.getSerializableExtra( "rest1" );
		Restaurant second = ( Restaurant ) data.getSerializableExtra( "rest2" );
		menu_1 = Menu_Item.best( first.getItems() , 5 , "color" );
		System.out.println( menu_1 );
		menu_2 = Menu_Item.best( second.getItems() , 5 , "color" );
		System.out.println( menu_2 );
		list_1 = new CustomAdapter(this, android.R.layout.simple_list_item_1, menu_1);
		list_2 = new CustomAdapter(this, android.R.layout.simple_list_item_1, menu_2);
		view_1.setAdapter(list_1);
		view_2.setAdapter(list_2);
		final Activity main = this;
		view_1.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//in.putExtra("name", ((ItemObject) parent.getItemAtPosition(position)).toString());
				Intent intent = new Intent( main, ViewFacts.class );
				intent.putExtra( "item", ((ItemObject) parent.getItemAtPosition(position)));
				startActivity(intent);
				
			}
			
		});		
		
		view_2.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//in.putExtra("name", ((ItemObject) parent.getItemAtPosition(position)).toString());
				Intent intent = new Intent( main, ViewFacts.class );
				intent.putExtra( "item", ((ItemObject) parent.getItemAtPosition(position)));
				startActivity(intent);
				
			}
			
		});				
	}
}
