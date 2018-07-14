package edu.arizona.foodtracker;
import java.util.ArrayList;


public class ObjectParser 
{
	private ArrayList<ItemObject> objects;
	private ItemObject current;
	
	public ObjectParser()
	{
		objects = new ArrayList<ItemObject>();
	}
	
	public ArrayList<ItemObject> getObjects()
	{
		return this.objects;
	}
	
	public void updateObjectsFromOutput( String line )
	{
		String part = line.trim();
		if ( part.charAt( 0 ) == '<' )
		{
			if ( part.charAt( 1 ) != '/' )
			{
				String name = part.substring( 1, part.length() - 1 );
				current = ItemObject.itemFactory( name );
			}
			else
			{
				objects.add( current );
				current = null;
			}
		}
		else
		{
			if ( current != null )
			{
				current.withAttribute( part );
			}
		}
	}
	
	public void print()
	{
		for( ItemObject object : objects )
		{
			System.out.println( object );
		}
	}
}
