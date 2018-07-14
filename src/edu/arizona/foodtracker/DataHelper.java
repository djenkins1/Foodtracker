package edu.arizona.foodtracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper {

	private SQLiteDatabase dbc;
	private static final String DB_NAME = "foodtracker.db";
	private Context con;
	public static DataHelper dh;
	
	public DataHelper(Context con){
		this.con = con;
	}
	
	public static DataHelper getInstance(Context con) {
		if (dh == null) {
			dh = new DataHelper(con);
		}
		dh.openDatabase();

		return dh;
	}
	
	private void openDatabase(){
		OpenHelper op = new OpenHelper(this.con);
		dbc = op.getWritableDatabase();
	}
	
	private static class OpenHelper extends SQLiteOpenHelper {

		public OpenHelper(Context context) {
			super(context, DB_NAME, null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = "";
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public String getName() throws SQLException{
		return null;
	}
}
