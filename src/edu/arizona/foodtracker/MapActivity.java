package edu.arizona.foodtracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MapActivity extends Activity {

	private Geocoder coder;
	private GoogleMap map;
	private List<Address> add;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		Intent in = getIntent();
		ArrayList<String> rest_a = in.getStringArrayListExtra("rest");
		coder = new Geocoder(this, Locale.getDefault());
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		
		try {
			add = coder.getFromLocationName("University of Arizona", 1);
			LatLng ua = new LatLng(add.get(0).getLatitude(), add.get(0).getLongitude());
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(ua, 12));

			for (int j = 0; j < rest_a.size(); j++){
				add = coder.getFromLocationName(rest_a.get(j), 3, ua.latitude - .25, ua.longitude - .25, ua.latitude + .25, ua.longitude + .25);
            for (int i = 0; i < add.size(); i++) {
            	LatLng a = new LatLng(add.get(i).getLatitude(), add.get(i).getLongitude());
            	map.addMarker(new MarkerOptions().position(a).title(rest_a.get(j)));
            }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
