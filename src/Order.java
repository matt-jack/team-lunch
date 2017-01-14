
public class Order {
	
	Restaurant restaurant;
	
	private int regularMeals;
	private int vegetarianMeals;
	private int glutenFreeMeals;
	private int fishFreeMeals;
	private int nutFreeMeals;
	
	public Order(Restaurant r, int rm, int vm, int gfm, int ffm, int nfm) {
		
		this.restaurant = r;
		
		this.regularMeals = rm;
		this.vegetarianMeals = vm;
		this.glutenFreeMeals = gfm;
		this.fishFreeMeals = ffm;
		this.nutFreeMeals = nfm;
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
