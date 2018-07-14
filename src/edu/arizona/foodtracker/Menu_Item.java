package edu.arizona.foodtracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Menu_Item extends ItemObject implements Serializable
{
	private static final long serialVersionUID = -6223640117347501485L;

	private String color;
	
	private String name;
	
	private String category;
	
	private double price;
	
	private int calories;
	
	private double totalFat;
	
	private double saturatedFat;
	
	public Menu_Item()
	{
		color = "";
		name = "";
		category = "";
		price = 0.0;
	}
	
	public Menu_Item withColor( String color )
	{
		this.color = color;
		return this;
	}
	
	public Menu_Item withName( String name )
	{
		this.name = name;
		return this;
	}
	
	public Menu_Item withCategory( String category )
	{
		this.category = category;
		return this;
	}
	
	public Menu_Item withPrice( String price )
	{
		double myPrice = Double.parseDouble( price );
		this.price = myPrice;
		return this;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public String getCategory()
	{
		return this.category;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getTotalFat()
	{
		return this.totalFat;
	}
	
	public double getSaturatedFat()
	{
		return this.saturatedFat;
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
		
		if ( property.equals( "color" ) )
		{
			this.withColor( info );
		}
		
		if ( property.equals( "category" ) )
		{
			this.withCategory( info );
		}
		
		if ( property.equals( "price" ) )
		{
			this.withPrice( info );
		}
		
		if ( property.equals( "calories" ) )
		{
			this.withCalories( Integer.parseInt( info ) );
		}
		
		if ( property.equals( "total_fat" ) )
		{
			this.withTotalFat( Double.parseDouble( info ) );
		}
		
		if ( property.equals( "sat_fat" ) )
		{
			this.withSaturatedFat( Double.parseDouble( info ) );
		}
	}	
	
	public int getCalories()
	{
		return this.calories;
	}
	
	private Menu_Item withCalories(int calories) {
		this.calories = calories;
		return this;
		
		
	}

	private Menu_Item withSaturatedFat(double fat) {
		this.saturatedFat = fat;
		return this;
		
	}

	private Menu_Item withTotalFat(double fat) {
		this.totalFat = fat;
		return this;
		
	}

	public String toString()
	{
		return this.name;
	}
	
	private static Comparator<Menu_Item> getSorter( String option )
	{
		Comparator<Menu_Item> compare = null;
		option = option.toLowerCase();
		if ( option.equals( "calories" ) )
		{
			compare = sortByCalories;
		}
		
		if ( option.equals( "price" ) )
		{
			compare = sortByPrice;
		}
		
		if ( option.equals( "fat" ) )
		{
			compare = sortByFat;
		}
		
		if ( option.equals( "saturated_fat" ) )
		{
			compare = sortBySatFat;
		}		
		
		if ( option.equals( "color" ) )
		{
			compare = sortByColor;
		}
		
		return compare;
	}
	
	public ArrayList<Menu_Item> sortBy( String option, boolean haveAscendingOrder, ArrayList<Menu_Item> items )
	{
		Comparator<Menu_Item> compare = getSorter( option );
		if ( compare != null )
		{
			Collections.sort( items, compare );
		}
		else
		{
			System.out.println( "BAD SORTING VALUE: " + option );
		}
		
		if ( !haveAscendingOrder )
		{
			Collections.reverse( items );
		}
		
		return items;
	}
	
	private static int compareThese( double left, double right )
	{
		if ( left < right )
		{
			return -1;
		}
		
		if ( left > right )
		{
			return 1;
		}
		
		return 0;
	}
	
	public static Comparator<Menu_Item> sortByCalories = new Comparator<Menu_Item>(){
		public int compare(Menu_Item left, Menu_Item right) 
		{
			return Menu_Item.compareThese( left.calories, right.calories );
		}
		
	};
	
	public static Comparator<Menu_Item> sortByPrice = new Comparator<Menu_Item>(){
		public int compare(Menu_Item left, Menu_Item right)
		{
			return Menu_Item.compareThese( left.price, right.price );
		}
	};
	
	public static Comparator<Menu_Item> sortByFat = new Comparator<Menu_Item>(){
		public int compare(Menu_Item left, Menu_Item right)
		{
			return Menu_Item.compareThese( left.totalFat, right.totalFat );
		}
	};
	
	public static Comparator<Menu_Item> sortBySatFat = new Comparator<Menu_Item>(){
		public int compare(Menu_Item left, Menu_Item right)
		{
			return Menu_Item.compareThese( left.saturatedFat, right.saturatedFat );
		}
	};
	
	public static Comparator<Menu_Item> sortByColor = new Comparator<Menu_Item>(){
		public int compare(Menu_Item left, Menu_Item right)
		{
			ArrayList<String> colors = new ArrayList<String>();
			colors.add( "green" );
			colors.add( "yellow" );
			colors.add( "red" );
			int firstPos = colors.indexOf( left.color.toLowerCase() );
			int secondPos = colors.indexOf( right.color.toLowerCase() );
			return Menu_Item.compareThese( firstPos, secondPos );
			
		}		
	};

	private static ArrayList<String> filterOptions;
	
	public static ArrayList<String> filterOptions()
	{
		if ( filterOptions == null )
		{
			filterOptions = new ArrayList<String>();
			String[] options = { "<" , ">" , ">=" , "<=" };
			for( String option : options )
			{
				filterOptions.add( option );
			}
		}
		return filterOptions;
	}
	
	private static ArrayList<String> filterTypes;
	
	public static ArrayList<String> filterTypes()
	{
		if ( filterTypes == null )
		{
			filterTypes = new ArrayList<String>();
			String[] names = { "Price" , "Calories" , "Fat" };
			for( String name : names )
			{
				filterTypes.add( name );
			}
		}
		return filterTypes;
	}	
	
	
	private static Menu_Item createItem( int value )
	{
		Menu_Item item = new Menu_Item();
		item.withPrice( value + "" ).withCalories( value ).withSaturatedFat( value ).withTotalFat( value );
		return item;
	}
	
	private static boolean comparisonPasses( Comparator<Menu_Item> compareBy, Menu_Item first, Menu_Item second, boolean isSmaller, boolean andEqual )
	{
		int value = compareBy.compare( first, second );
		//the first and second values are equal, and we want items that are equal so return true
		if ( value == 0 && andEqual )
		{
			return true;
		}
		
		//the first value is bigger then the second, and we want items that are bigger so return true
		if ( value > 0 && !isSmaller )
		{
			return true;
		}
		
		//the first value is smaller, and we want items that are smaller so return true
		if ( value < 0 && isSmaller )
		{
			return true;
		}
		
		return false;
	}
	
	public static ArrayList<ItemObject> filterBy( ArrayList<ItemObject> items, String option, int value, boolean isSmaller, boolean andEqual )
	{
		Comparator<Menu_Item> compareBy = getSorter( option );
		Menu_Item dummy = createItem( value );
		ArrayList<ItemObject> toReturn = new ArrayList<ItemObject>();
		for( ItemObject item : items )
		{
			if ( comparisonPasses( compareBy, (Menu_Item) item, dummy, isSmaller, andEqual ) )
			{
				toReturn.add( item );
			}
		}
		
		return toReturn;
	}
	
	public ArrayList<ItemObject> filterByCaloriesAndFat(  ArrayList<ItemObject> items )
	{
		ArrayList<ItemObject> toReturn = filterBy( items, "calories" , 500, true, true );
		toReturn = filterBy( toReturn, "total_fat" , 300 , true, true );
		return toReturn;
	}
	
	private static boolean optionFactory( boolean greaterThan , String option)
	{
		if ( option.equals( ">" ) || option.equals( ">=" ) )
		{
			if ( option.equals( ">" ) && !greaterThan )
			{
				return false;
			}
			
			return true;
		}
		
		if ( option.equals( "<" ) || option.equals( "<=" ) )
		{
			if ( option.equals( "<=" ) && !greaterThan )
			{
				return true;
			}
			
			return false;
		}
		
		if ( option.equals( "=" ) && !greaterThan )
		{
			return true;
		}
		
		return false;
	}
	
	private static ArrayList<ItemObject> sortMe( ArrayList<ItemObject> unsorted, String type )
	{
		ArrayList<Menu_Item> items = new ArrayList<Menu_Item>();
		for( ItemObject obj : unsorted )
		{
			items.add( (Menu_Item) obj );
		}
		
		Collections.sort( items, getSorter( type ) );
		ArrayList<ItemObject> toReturn = new ArrayList<ItemObject>();
		toReturn.addAll( items );
		return toReturn;
	}

	public static ArrayList<ItemObject> filterOut(ArrayList<ItemObject> copyItems, String option, String type,int value) 
	{
		ArrayList<ItemObject> toReturn = new ArrayList<ItemObject>();
		if ( !filterOptions().contains( option ) || !filterTypes().contains( type ) || value < 0 || copyItems == null || copyItems.size() == 0  )
		{
			System.out.println( "BAD" );
			System.out.println( "Option: " + option );
			System.out.println( "Type: " + type );
			System.out.println( "Value: " + value );
			System.out.println( "Item: " + copyItems.toString() );
			return toReturn;
		}
		
		boolean greaterThan = optionFactory( true, option );
		boolean equalTo = optionFactory( false , option );
		toReturn = filterBy( copyItems, type, value, !greaterThan, equalTo );
		toReturn = sortMe( toReturn, type );
		
		return toReturn;
	}

	public static ArrayList<ItemObject> best(ArrayList<ItemObject> items,int limit, String option) 
	{
		ArrayList<ItemObject> all = sortMe( items, option );
		return new ArrayList<ItemObject>(all.subList( 0, limit ));
	}
}
