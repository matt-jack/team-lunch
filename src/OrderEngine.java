import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class OrderEngine {
	
	private static Directory restaurants;
	
	/** Calls OrderEngine.createOrderList with a random order, then calls
	 *  OrderEngine.printOrderList to output the resulting restaurant
	 *  order breakdown
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
				
		// generate a random restaurant directory
		restaurants = new Directory();
		
		// generate a new order based off of random meals needs
		int regularMealsOrdered = ThreadLocalRandom.current().nextInt(50);
		int vegetarianMealsOrdered = ThreadLocalRandom.current().nextInt(20);
		int glutenFreeMealsOrdered = ThreadLocalRandom.current().nextInt(20);
		int fishFreeMealsOrdered = ThreadLocalRandom.current().nextInt(10);
		int nutFreeMealsOrdered = ThreadLocalRandom.current().nextInt(5);
		
		OrderList orderList = createOrderList(restaurants, regularMealsOrdered, 
				vegetarianMealsOrdered, glutenFreeMealsOrdered, fishFreeMealsOrdered, 
				nutFreeMealsOrdered);
		
		printOrderList(orderList, regularMealsOrdered, vegetarianMealsOrdered, 
				glutenFreeMealsOrdered, fishFreeMealsOrdered, nutFreeMealsOrdered);
			
	}
	
	/** Sorts the provided directory's restaurant list by restaurant rating in
	 *  descending order, then generates a list of individual restaurant orders
	 *  prioritized by restaurant rating to complete the complete team lunch
	 *  order with the tastiest food, IF POSSIBLE (5 restaurants are generated
	 *  by Directory, and it's entirely possible that not enough desired meals
	 *  are in stock).
	 * 
	 * @param directory
	 * @param regularMealsOrdered
	 * @param vegetarianMealsOrdered
	 * @param glutenFreeMealsOrdered
	 * @param fishFreeMealsOrdered
	 * @param nutFreeMealsOrdered
	 * @return
	 */
	public static OrderList createOrderList(Directory directory, 
							int regularMealsOrdered, 
							int vegetarianMealsOrdered,
							int glutenFreeMealsOrdered, 
							int fishFreeMealsOrdered, 
							int nutFreeMealsOrdered) {
		
		// sort restaurants by rating
		directory.restaurantList.sort(
				
				new Comparator<Restaurant>() {
					
					@Override
					public int compare(Restaurant r1, Restaurant r2) {
						return r2.rating - r1.rating;
					}
			
		});
		
		// counters to keep track of how many of each order has been placed
		int regularMealOrdersPlaced = 0;
		int vegetarianMealOrdersPlaced = 0;
		int glutenFreeMealOrdersPlaced = 0;
		int fishFreeMealOrdersPlaced = 0;
		int nutFreeMealOrdersPlaced = 0;
		
		OrderList orderList = new OrderList();
		
		// order as many meals as possible from the best restaurant, then remaining
		// meals from the next best, and so on...
		for(Restaurant r : directory.restaurantList) {
			
			// flag to keep track of whether another order even needs to be placed
			boolean placeOrder = false;
			
			// check if each type of meal is needed AND any are in stock
			
			// the reason to check the stock is to avoid a blank order, say if
			// 1 more nut free meal was all that was needed and the current
			// restaurant has none in stock
			int regularMealsNeeded = regularMealsOrdered - regularMealOrdersPlaced;
			if(regularMealsNeeded > 0 && r.regularMealStock > 0)
				placeOrder = true;
			
			int vegetarianMealsNeeded = vegetarianMealsOrdered - vegetarianMealOrdersPlaced;
			if(vegetarianMealsNeeded > 0 && r.vegetarianMealStock > 0)
				placeOrder = true;
			
			int glutenFreeMealsNeeded = glutenFreeMealsOrdered - glutenFreeMealOrdersPlaced;
			if(glutenFreeMealsNeeded > 0 && r.glutenFreeMealStock > 0) 
				placeOrder = true;
			
			int fishFreeMealsNeeded = fishFreeMealsOrdered - fishFreeMealOrdersPlaced;
			if(fishFreeMealsNeeded > 0 && r.fishFreeMealStock > 0) 
				placeOrder = true;
			
			int nutFreeMealsNeeded = nutFreeMealsOrdered - nutFreeMealOrdersPlaced;
			if(nutFreeMealsNeeded > 0 && r.nutFreeMealStock > 0)
				placeOrder = true;
			
			// if more orders actually need to be placed and some meals are in stock ...
			if(placeOrder) {
				
				// adjust for how many meals are actually available
				if(regularMealsNeeded > r.regularMealStock)
					regularMealsNeeded = r.regularMealStock;
				if(vegetarianMealsNeeded > r.vegetarianMealStock)
					vegetarianMealsNeeded = r.vegetarianMealStock;
				if(glutenFreeMealsNeeded > r.glutenFreeMealStock)
					glutenFreeMealsNeeded = r.glutenFreeMealStock;
				if(fishFreeMealsNeeded > r.fishFreeMealStock)
					fishFreeMealsNeeded = r.fishFreeMealStock;
				if(nutFreeMealsNeeded > r.nutFreeMealStock)
					nutFreeMealsNeeded = r.nutFreeMealStock;
				
				
				// add the new order to the list, 
				// then update how many orders have been placed
				orderList.orders.add(new Order(r,
						regularMealsNeeded, 
						vegetarianMealsNeeded, 
						glutenFreeMealsNeeded, 
						fishFreeMealsNeeded, 
						nutFreeMealsNeeded));
				
				// update the amount of orders placed
				regularMealOrdersPlaced += regularMealsNeeded;
				vegetarianMealOrdersPlaced += vegetarianMealsNeeded;
				glutenFreeMealOrdersPlaced += glutenFreeMealsNeeded;
				fishFreeMealOrdersPlaced += fishFreeMealsNeeded;
				nutFreeMealOrdersPlaced += nutFreeMealsNeeded;
			}
		}
		
		// check if the order was completed
		if(regularMealOrdersPlaced == regularMealsOrdered
				&& vegetarianMealOrdersPlaced == vegetarianMealsOrdered
				&& glutenFreeMealOrdersPlaced == glutenFreeMealsOrdered
				&& fishFreeMealOrdersPlaced == fishFreeMealsOrdered
				&& nutFreeMealOrdersPlaced == nutFreeMealsOrdered) {
			orderList.incompleteOrder = false;
		}
										
		return orderList;
	}
	
	/** Prints information about the order including whether or not it was complete,
	 *  how many restaurants were ordered from, and what each individual restaurant
	 *  order consisted of.
	 * 
	 * @param orderList
	 * @param regularMealsOrdered
	 * @param vegetarianMealsOrdered
	 * @param glutenFreeMealsOrdered
	 * @param fishFreeMealsOrdered
	 * @param nutFreeMealsOrdered
	 */
	public static void printOrderList(OrderList orderList,
		int regularMealsOrdered,
		int vegetarianMealsOrdered,
		int glutenFreeMealsOrdered,
		int fishFreeMealsOrdered,
		int nutFreeMealsOrdered ) {
		
		System.out.println("Total Order\n");
		System.out.println("Regular meals: "+String.valueOf(regularMealsOrdered));
		System.out.println("Vegetarian meals: "+String.valueOf(vegetarianMealsOrdered));
		System.out.println("Gluten free meals: "+String.valueOf(glutenFreeMealsOrdered));
		System.out.println("Fish free meals: "+String.valueOf(fishFreeMealsOrdered));
		System.out.println("Nut free meals: "+String.valueOf(nutFreeMealsOrdered)+"\n");
		System.out.println("Order complete: "+String.valueOf(!orderList.incompleteOrder));
		System.out.println("Restarant Orders: "+String.valueOf(orderList.orders.size()
				+ "\n"));
		System.out.println("===================================\n\n");
				
		
		System.out.println("Restaurant Orders:\n\n");
		for(Order o : orderList.orders) {
			
			System.out.println(o.getRestaurant().getName());
			
			System.out.println("Rating: "+String.valueOf(o.getRestaurant().rating));
			
			System.out.println("Stock:");
			System.out.println("Regular meals in stock: "+o.getRestaurant().regularMealStock);
			System.out.println("Vegetarian meals in stock: "+o.getRestaurant().vegetarianMealStock);
			System.out.println("Gluten free meals in stock: "+o.getRestaurant().glutenFreeMealStock);
			System.out.println("Fish free meals in stock: "+o.getRestaurant().fishFreeMealStock);
			System.out.println("Nut free meals in stock: "+o.getRestaurant().nutFreeMealStock
					+ "\n");
			
			System.out.println("Order");
			System.out.println("Regular meals ordered: " 
								+ String.valueOf(o.getRegularMeals()));
			System.out.println("Vegetarian meals ordered: " 
					+ String.valueOf(o.getVegetarianMeals()));
			System.out.println("Gluten free meals ordered: " 
					+ String.valueOf(o.getGlutenFreeMeals()));
			System.out.println("Fish free meals ordered: " 
					+ String.valueOf(o.getFishFreeMeals()));
			System.out.println("Nut free meals ordered: " 
					+ String.valueOf(o.getNutFreeMeals()) + "\n\n");
			

		}
 	}
	
	
}
