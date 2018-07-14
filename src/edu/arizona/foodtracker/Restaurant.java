package edu.arizona.foodtracker;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant extends ItemObject implements Serializable
{
	private static final long serialVersionUID = 774129514321702397L;

	private String name;
	
	private int id;
	
	private String url;
	
	private String color;
	
	private ArrayList<ItemObject> items;
	
	public Restaurant()
	{
		name = "";
		url = "";
		items = new ArrayList<ItemObject>();
		color = "";
	}
	
	public Restaurant withName( String name )
	{
		this.name = name;
		return this;
	}
	
	public Restaurant withID( int id )
	{
		this.id = id;
		return this;
	}
	
	public Restaurant withUrl( String url )
	{
		this.url = url;
		return this;
	}
	
	public Restaurant addItem( ItemObject item )
	{
		this.items.add( item );
		return this;
	}
	
	public Restaurant withColor( String color )
	{
		this.color = color;
		return this;
	}
	
	public ArrayList<ItemObject> getItems()
	{
		return this.items;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public String getURL()
	{
		return this.url;
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getName()
	{
		return this.name;
	}

	@Override
	public void withAttribute(String line) 
	{
		int stop = line.indexOf( ":" );
		line = line.trim();
		String property = line.substring( 0 , stop ).trim();
		String info = line.substring( stop + 1 ).trim();
		if ( property.equals( "name" ) )
		{
			this.withName( info );
		}
		
		if ( property.equals( "id" ) )
		{
			int id = Integer.parseInt( info );
			this.withID( id );
		}
		
		if ( property.equals( "url" ) )
		{
			this.withUrl( info );
		}
		
		if ( property.equals( "color" ) )
		{
			this.withColor( info );
		}
		
	}
	
	public Restaurant withItems( ArrayList<ItemObject> items )
	{
		this.items = items;
		return this;
	}
	
	public String toString()
	{
		/*
		String toReturn = "";
		toReturn += ( "Restaurant: " + name );
		toReturn += "\n";
		toReturn += "ID: " + id;
		toReturn += "\n";
		toReturn += "URL: " + url;
		toReturn += "\n";
		return toReturn;
		*/
		return this.name;
	}
}
