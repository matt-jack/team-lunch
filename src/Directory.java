import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/** Basically a List of 
 * 
 * @author chrx
 *
 */
public class Directory {
	
	public ArrayList<Restaurant> restaurantList;
	
	// generate 20 random restaurants
	public Directory() {
		
		restaurantList = new ArrayList<Restaurant> ();
		
		for(int i = 0; i < 20; i++) {
			
			int rating = ThreadLocalRandom.current().nextInt(6);
			int regularMealStock = ThreadLocalRandom.current().nextInt(50);
			int vegetarianMealStock = ThreadLocalRandom.current().nextInt(50);
			int glutenFreeMealStock = ThreadLocalRandom.current().nextInt(50);
			int fishFreeMealStock = ThreadLocalRandom.current().nextInt(50);
			int nutFreeMealStock = ThreadLocalRandom.current().nextInt(50);
			
			restaurantList.add(new Restaurant(
								rating,
								regularMealStock,
								vegetarianMealStock,
								glutenFreeMealStock,
								fishFreeMealStock,
								nutFreeMealStock,
								"Restaurant " + String.valueOf(i+1)));
		}
	}
	
	public Directory(ArrayList<Restaurant> r) {
		this.restaurantList = r;
	}
	
	
}
