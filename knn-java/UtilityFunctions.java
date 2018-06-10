import java.util.ArrayList;

/**
 *   @author Matt Kearns
 *   mdk2mc@virginia.edu
 * 
 *   UtilityFunctions is a class with some useful static 
 *   methods for doing the following:
 * 
 *   	(1) sort a list of DataObjects by shortest distance to given DataObject.
 * 		(2) get the average distance to DataObjects of a particular category.
 * 		(3) determine the category of a new DataObject by its k nearest neighbors.
 */
public class UtilityFunctions 
{
	/**
	 *   sortByShortestDistance() sorts a list of DataObjects by shortest 
	 *   distance to given DataObject.
	 * 
	 *   @param data is the list of DataObjects
	 *   @param point is the user-supplied DataObject
	 *   @return sorted list of DataObjects by distance
	 */
	public static ArrayList<DataObject> sortByShortestDistance(ArrayList<DataObject> data, DataObject point)
	{
		ArrayList<DataObject> sorted = new ArrayList<DataObject>();
		
		while (data.size() > 0)
		{
			int shortestIndex = 0;
			
			for (int i = 0; i < data.size(); i++)
			{
				if (data.get(i).distanceTo(point) < data.get(shortestIndex).distanceTo(point))
				{
					shortestIndex = i;
				}
			}
			
			sorted.add(data.get(shortestIndex));
			data.remove(shortestIndex);
		}
		
		return sorted;
	}
	
	/**
	 *   getAverageDistance() reports the average distance to DataObjects 
	 *   of a particular category.
	 * 
	 *   @param data is the list of DataObjects
	 *   @param dataValue is the user-supplied DataObject
	 *   @param category is cat1 or cat2
	 *   @return average distance to DataObjects of given category
	 */
	private static double getAverageDistance(ArrayList<DataObject> data, DataObject dataValue, String category) 
	{
		double sum = 0;
		int count = 0;
		
		for (int i = 0; i < data.size(); i++)
		{
			if (data.get(i).getCategory().equalsIgnoreCase(category)) 
			{
				sum += data.get(i).distanceTo(dataValue);
				count += 1;
			}
		}
		
		if (count == 0) return 0;
		else            return sum / count; 
	}
	
	/**
	 *   determineCategory() reports the determined category of a new DataObject
	 *   based on the categories of its k nearest neighbors.
	 * 
	 *   @param data is the list of DataObjects
	 *   @param dataValue is the user-supplied DataObject
	 *   @param k is the number of neighbors
	 *   @return determined category for new data item
	 */
	public static String determineCategory(ArrayList<DataObject> data, DataObject dataValue, int k) 
	{
		int cat1Count = 0, cat2Count = 0;
		
		for (int i = 0; i < k; i++)
		{
			if (data.get(i).getCategory().equalsIgnoreCase("cat1")) cat1Count += 1;
			else													cat2Count += 1;
		}
		
		if (cat1Count >= cat2Count) return "cat1";
		else                        return "cat2";
	}
	
	/**
	 *   printResults() prints the k neighbors, determined category, and the average
	 *   distances to items of cat1 and cat2 for the user-supplied data item.
	 * 
	 *   @param data is the list of DataObjects
	 *   @param dataValue is the user-supplied data item
	 *   @param k is the number of nearest neighbors
	 */
	public static void printResults(ArrayList<DataObject> data, DataObject dataValue, int k)
	{
		/* Print k nearest neighbors */
		for (int i = 0; i < k; i++) System.out.println(data.get(i).toString(dataValue));
		
		/* Print decision */
		System.out.println(String.format("Data item (%.1f, %.1f) assigned to: %s", dataValue.getX(), dataValue.getY(), dataValue.getCategory()));
		
		/* Print average distances */
		System.out.println(String.format("Average distance to cat1 items: %.2f", getAverageDistance(data, dataValue, "cat1")));
		System.out.println(String.format("Average distance to cat2 items: %.2f", getAverageDistance(data, dataValue, "cat2")));
	}

}
