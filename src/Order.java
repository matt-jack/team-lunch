/** Object to represent an order from a single restaurant
 * 
 * @author chrx
 *
 */
public class Order {
	
	// none of the parameters should ever need to be changed after the order
	// is created
	private Restaurant restaurant;
	
	private int regularMeals;
	private int vegetarianMeals;
	private int glutenFreeMeals;
	private int fishFreeMeals;
	private int nutFreeMeals;
	
	/** Standard constructor which initializes class variables to supplied values
	 * 
	 * @param r
	 * @param rm
	 * @param vm
	 * @param gfm
	 * @param ffm
	 * @param nfm
	 */
	public Order(Restaurant r, int rm, int vm, int gfm, int ffm, int nfm) {
		
		this.restaurant = r;
		
		this.regularMeals = rm;
		this.vegetarianMeals = vm;
		this.glutenFreeMeals = gfm;
		this.fishFreeMeals = ffm;
		this.nutFreeMeals = nfm;
	}	
	
	public Restaurant getRestaurant() {
		return this.restaurant;
	}
	
	public int getRegularMeals() {
		return this.regularMeals;
	}
	public int getVegetarianMeals() {
		return this.vegetarianMeals;
	}
	public int getGlutenFreeMeals() {
		return this.glutenFreeMeals;
	}
	public int getFishFreeMeals() {
		return this.fishFreeMeals;
	}
	public int getNutFreeMeals() {
		return this.nutFreeMeals;
	}
	
	
}
