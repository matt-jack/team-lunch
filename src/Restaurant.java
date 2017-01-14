import java.util.concurrent.ThreadLocalRandom;

public class Restaurant {
	
	public int rating;
	
	public int regularMealStock;
	public int vegetarianMealStock;
	public int glutenFreeMealStock;
	public int fishFreeMealStock;
	public int nutFreeMealStock;
	
	private String name;
	
	public Restaurant(int r, int rms, int vms, int gfms,
						int ffms, int nfms, String n) {
		this.rating = r;
		this.regularMealStock = rms;
		this.vegetarianMealStock = vms;
		this.glutenFreeMealStock = gfms;
		this.fishFreeMealStock = ffms;
		this.nutFreeMealStock = nfms;
		this.name = n;
	}
	
	/** Initialize restaurant with name n and random paramaters
	 *  rating:  between 0 and 5
	 *  regularMealStock: between 0 and 50
	 *  vegetarianMealStock: between 0 and 20
	 *  glutenFreeMealStock: between 0 and 20
	 *  fishFreeMealStock: between 0 and 10
	 *  nutFreeMealStock: between 0 and 5 
	 * 
	 * @param n
	 */
	public Restaurant(String n) {
		this.rating = ThreadLocalRandom.current().nextInt(6);
		this.regularMealStock = ThreadLocalRandom.current().nextInt(50);
		this.vegetarianMealStock = ThreadLocalRandom.current().nextInt(20);
		this.glutenFreeMealStock = ThreadLocalRandom.current().nextInt(20);
		this.fishFreeMealStock = ThreadLocalRandom.current().nextInt(10);
		this.nutFreeMealStock = ThreadLocalRandom.current().nextInt(5);
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
}
