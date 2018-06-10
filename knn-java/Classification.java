import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**  
 *   @author Matt Kearns 
 *   mdk2mc@virginia.edu
 * 
 *	 k-NN Classification
 *
 *   The k nearest neighbor algorithm allows for the category of
 *   unclassified data items to be determined based on the categories
 *   of its k nearest neighbors (nearest on the 2D Euclidean plane).
 *   In this program, the user will specify three pieces of information:
 *   k, M, and the name of the data file to be read. k is the number of 
 *   nearest neighbors to be used to classify subsequent data items
 *   supplied by the user. M is the sample space: the number of initial 
 *   data items from the data file available for comparison. This
 *   program prints the k nearest neighbors and their distances from the
 *   unclassified data item supplied by the user, the classification 
 *   determined from the k nearest neighbors, and the average distance 
 *   from the supplied data point to those in the sample space of
 *   categories one and two.
 */
public class Classification 
{
	public static void main(String[] args)
	{
		int k, M;
		ArrayList<DataObject> data = new ArrayList<DataObject>();
		DataObject nextDataPoint;
		File dataFile;
		Scanner fileReader, keyboard = new Scanner(System.in);
		StringTokenizer tokenizer;
		
		System.out.println("---------------------------------------------------\n");
		
		System.out.println("In this program, the user will specify" +
		                   " three pieces\nof information: k, M, and" +
						   " the name of the data file\nto be read.\n");
						   
		System.out.println("k is the number of nearest neighbors to" + 
						   " be used to\nclassify subsequent data items" + 
						   " supplied by the user.\n");
						   
		System.out.println("M is the sample space: the number of initial" + 
						   " data\nitems from the data file available for" + 
						   " comparison.\n");
						   
		System.out.println("This program prints the k nearest neighbors" + 
						   " and\ntheir distances from the unclassified data" + 
						   " item\nsupplied by the user, the classification" + 
						   " determined\nfrom the k nearest neighbors, and" + 
						   " the average\ndistance from the supplied data" + 
						   " point to those in\nthe sample space of categories" +  
						   " one and two.\n");
						   
		System.out.println("---------------------------------------------------\n");
		
		/* 
		 *   Prompt user for values of k, M. Get data file name. 
		 */
		
		System.out.print("Enter value of k: ");
		k = keyboard.nextInt();
		System.out.print("Enter value of M: ");
		M = keyboard.nextInt();
		keyboard.nextLine();                             // consume remaining newline character
		System.out.print("Enter data file name: ");
		dataFile = new File(keyboard.nextLine());
		
		/* 
		 *   Read file contents into data structure. Each data item is a DataObject (category, x, y) 
		 */ 
		
		try 
		{
			fileReader = new Scanner(dataFile);
			
			while(fileReader.hasNextLine() && M-- != 0) 
			{
				tokenizer = new StringTokenizer(fileReader.nextLine(), " ");
				data.add(new DataObject(tokenizer.nextToken(), 
						Double.parseDouble(tokenizer.nextToken()), 
						Double.parseDouble(tokenizer.nextToken())));
			}
		} 
		catch (FileNotFoundException e) 
		{	
			System.out.println(String.format("File %s does not exist. ", dataFile.getName()));
			System.exit(0);
		}
		
		/* 
		 *   Repeatedly prompt user for new data. User enters "1 1" to exit.
		 *   Determine category of new data based on categories of the k nearest neighbors.
		 */
		
		while (true)
		{
			System.out.print("Please enter the next data (x, y) item: ");
			
			tokenizer = new StringTokenizer(keyboard.nextLine(), " ");
			nextDataPoint = new DataObject("cat1", 
					Double.parseDouble(tokenizer.nextToken()), 
					Double.parseDouble(tokenizer.nextToken()));
			
			if (nextDataPoint.getX() == 1 && nextDataPoint.getY() == 1) break;
			
			/* 
			 *   (1) Sort data points by distance to new point.
			 *   (2) Determine category of new data from k nearest neighbors.
			 *   (3) Print results.
			 */
			
			data = UtilityFunctions.sortByShortestDistance(data, nextDataPoint);                   // (1)
			nextDataPoint.setCategory(UtilityFunctions.determineCategory(data, nextDataPoint, k)); // (2)
			UtilityFunctions.printResults(data, nextDataPoint, k);                                 // (3)
		}
		
		System.out.println("Goodbye.");
		keyboard.close();
	}
}
