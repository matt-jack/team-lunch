
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
	
	public String getName() {
		return this.name;
	}
	
}
