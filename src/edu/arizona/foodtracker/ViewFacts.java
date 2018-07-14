package edu.arizona.foodtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewFacts extends Activity {

	private TextView food;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_facts);
		Intent data = getIntent();
		food = (TextView) findViewById(R.id.textView1);
		Menu_Item foodItem = (Menu_Item) data.getSerializableExtra( "item" );
		food.setText( foodItem.getName() );
		
		TextView calories = (TextView) findViewById(R.id.textView2);
		TextView totalFat = (TextView) findViewById(R.id.textView3);
		TextView satFat = (TextView) findViewById(R.id.textView4);
		TextView color = (TextView) findViewById(R.id.textView5);
		TextView price = (TextView) findViewById(R.id.textView6);
		
		calories.setText( "Calories: " + foodItem.getCalories() + "" );
		totalFat.setText( "Total Fat: " + foodItem.getTotalFat() + "");
		satFat.setText( "Saturated Fat: " + foodItem.getSaturatedFat() + "");
		color.setText( "Color: " + foodItem.getColor() );
		price.setText( "Price: " + foodItem.getPrice() );
	}
}
