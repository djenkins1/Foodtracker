package edu.arizona.foodtracker;

import java.io.Serializable;

public abstract class ItemObject implements Serializable 
{
	private static final long serialVersionUID = 7421868360170881384L;

	public abstract void withAttribute( String line );

	public static ItemObject itemFactory(String name) 
	{
		if ( name.equals( "restaurant" ) )
		{
			return new Restaurant();
		}
		
		if ( name.equals( "menu_item" ) )
		{
			return new Menu_Item();
		}
		
		return null;
	}
	
}
