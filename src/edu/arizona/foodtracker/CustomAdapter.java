package edu.arizona.foodtracker;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<ItemObject> {

	private Context con;
	private List<ItemObject> list;
	private int viewId;
	
	public CustomAdapter(Context context, int textViewResourceId,
			List<ItemObject> objects) {
		super(context, textViewResourceId, objects);
		this.con = context;
		this.list = objects;
		this.viewId = textViewResourceId;
	}
	
	public View getView(int pos, View old, ViewGroup group){
		TextView c = new TextView(con);
		c.setText(list.get(pos).toString() + "(" +((Menu_Item) list.get(pos)).getCalories() + ")");
		c.setTextSize(20);
		c.setPadding(5, 8, 2, 8);
		if (((Menu_Item) list.get(pos)).getColor().compareTo("RED") == 0)
			c.setTextColor(con.getResources().getColor(android.R.color.holo_red_dark));
		else if (((Menu_Item) list.get(pos)).getColor().compareTo("GREEN") == 0)
			c.setTextColor(con.getResources().getColor(android.R.color.holo_green_dark));
		else if (((Menu_Item) list.get(pos)).getColor().compareTo("YELLOW") == 0)
			c.setTextColor(con.getResources().getColor(android.R.color.holo_orange_dark));
			else c.setTextColor(con.getResources().getColor(android.R.color.holo_orange_dark));
		return c;
	}

}
