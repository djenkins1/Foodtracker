package edu.arizona.foodtracker;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class FilterActivity extends Activity 
{
	
	private CustomAdapter r_adpt;
	private ListView menu;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filterview);
		Intent intent = this.getIntent();
		final ArrayList<ItemObject> menuItems = (ArrayList<ItemObject>)( intent.getSerializableExtra( "items" ) );
		final ArrayList<ItemObject> copyItems = new ArrayList<ItemObject>();
		copyItems.addAll( menuItems );
		
		menu = (ListView) findViewById( R.id.filterList );
		
		final Spinner filterSpinner =(Spinner) findViewById(R.id.filterSpinner);
		ArrayList<String> filterTypes = Menu_Item.filterTypes();
		
		ArrayAdapter<String> strs = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, filterTypes );
		filterSpinner.setAdapter(strs);
		
		final Spinner optionSpinner = (Spinner )findViewById(R.id.optionSpinner);
		ArrayList<String> filters = Menu_Item.filterOptions();
		
		ArrayAdapter<String> opts = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, filters );
		optionSpinner.setAdapter(opts);
		
		r_adpt = new CustomAdapter(this, android.R.layout.simple_list_item_1, menuItems);
		final Activity main = this;
		menu.setAdapter(r_adpt);
		menu.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				Intent in = new Intent( main, ViewFacts.class );
				in.putExtra( "item", ((ItemObject) parent.getItemAtPosition(position)));
				startActivity(in);
				
			}
		});	
		
		final EditText edit = (EditText ) findViewById( R.id.inputValue );
		final Button filterButton = ( Button ) findViewById( R.id.filterMe );
		filterButton.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {
				try
				{
					int value = Integer.parseInt( edit.getText().toString() );
					if ( value >= 0 )
					{
						String option = optionSpinner.getSelectedItem().toString();
						String type = filterSpinner.getSelectedItem().toString();
						ArrayList<ItemObject> filtered = Menu_Item.filterOut( copyItems, option , type, value );
						System.out.println( filtered );
						menuItems.clear();
						menuItems.addAll(filtered);
						r_adpt.notifyDataSetChanged();
					}
				}
				catch( Exception e )
				{
					System.out.println( "EXCEPTION" );
					e.printStackTrace();
				}
				
			}
			
		});
		
	
	}
}
