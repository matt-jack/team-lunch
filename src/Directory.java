import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/** Basically a list of restaurants, but with an option to construct a 5 restaurant
 *  directory with random restaurants (i.e. each restaurant has a random rating
 *  and random meal stocks (within set limits for each type of meal)
 * 
 * @author chrx
 *
 */
public class Directory {
	
	public ArrayList<Restaurant> restaurantList;
	
	// generate 5 random restaurants
	public Directory() {
		
		restaurantList = new ArrayList<Restaurant> ();
		
		for(int i = 0; i < 5; i++) {
			
			restaurantList.add(new Restaurant("Restaurant " + String.valueOf(i+1)));
		}
	}
	
	public Directory(ArrayList<Restaurant> r) {
		this.restaurantList = r;
	}
	
	
}
