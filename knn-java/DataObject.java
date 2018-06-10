/**
 * 	 @author Matt Kearns
 * 	 mdk2mc@virginia.edu
 * 
 *   A DataObject is a representation of a data item that contains 
 *   a category (either cat1 or cat2) and a location of the 2D Euclidean 
 *   plane (x, y). A data item can format itself for printing and has 
 *   the ability to report its distance from another DataObject.
 */
public class DataObject 
{
	private String category;
	private double x, y;
	
	/**
	 *   This is an explicit-value constructor that is used to
	 *   initialize instances of the DataObject class.
	 * 
	 *   @param category is the category of the DataObject
	 *   @param x is its x position
	 *   @param y is its y position
	 */
	DataObject (String category, double x, double y)
	{
		this.setCategory(category);
		this.setX(x);
		this.setY(y);
	}

	/**
	 *   getCategory() reports the category 
	 *   of calling DataObject.
	 * 
	 *   @return category of calling DataObject
	 */
	public String getCategory() 
	{
		return category;
	}
	
	/**
	 *   getX() reports the calling DataObject's 
	 *   x position.
	 * 
	 *   @return the x position of calling DataObject
	 */
	public double getX() 
	{
		return x;
	}
	
	/**
	 *   getY() reports the calling DataObject's 
	 *   y position.
	 * 
	 *   @return the y position of calling DataObject
	 */
	public double getY() 
	{
		return y;
	}
	
	/**
	 *   setCategory() sets the calling DataObject's 
	 *   category.
	 */
	public void setCategory(String category) 
	{
		this.category = category;
	}

	/**
	 *   setX() sets the calling DataObject's 
	 *   x position.
	 */
	public void setX(double x) 
	{
		this.x = x;
	}

	/**
	 *   setY() sets the calling DataObject's 
	 *   y position.
	 */
	public void setY(double y) 
	{
		this.y = y;
	}
	
	/* Utility Methods */
	
	/**
	 *  toString() prints information about this DataObject.
	 *  category x y
	 */
	public String toString()
	{
		return category + " " + x + " " + y;
	}
	
	/**
	 *  toString(DataObject) prints information about this DataObject.
	 *  category x y distanceToOtherDataObject
	 */
	public String toString(DataObject other)
	{
		return category + " " + x + " " + y + " " + String.format("%.2f", distanceTo(other));
	}
	
	/**
	 *   distanceTo(DataObject) returns the distance from this
	 *   DataObject to the other DataObject.
	 * 
	 *   @param other is the other DataObject
	 *   @return distance from this object to other object
	 */
	public double distanceTo(DataObject other)
	{
		return Math.sqrt(Math.pow((other.getY() - this.y), 2) + Math.pow((other.getX() - this.x), 2));
	}

}
