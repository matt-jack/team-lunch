import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class OrderEngine {
	
	private static Directory restaurants;
	
	public static void main(String args[]) {
				
		// generate a random restaurant directory
		restaurants = new Directory();
		
		// generate a new order based off of random meals needs
		int regularMealsOrdered = ThreadLocalRandom.current().nextInt(50);
		int vegetarianMealsOrdered = ThreadLocalRandom.current().nextInt(50);
		int glutenFreeMealsOrdered = ThreadLocalRandom.current().nextInt(50);
		int fishFreeMealsOrdered = ThreadLocalRandom.current().nextInt(50);
		int nutFreeMealsOrdered = ThreadLocalRandom.current().nextInt(50);
		
		OrderList orderList = createOrderList(restaurants, regularMealsOrdered, 
				vegetarianMealsOrdered, glutenFreeMealsOrdered, fishFreeMealsOrdered, 
				nutFreeMealsOrdered);
		
		printOrderList(orderList, regularMealsOrdered, vegetarianMealsOrdered, 
				glutenFreeMealsOrdered, fishFreeMealsOrdered, nutFreeMealsOrdered);
			
	}
	
	// Turns the given meal order into a list of individual restaurant orders 
	// prioritized by restaurant rating
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
			
			//TODO: Check if the else block is really needed (I suspect not)
			int regularMealsNeeded = regularMealsOrdered - regularMealOrdersPlaced;
			if(regularMealsNeeded > 0)
				placeOrder = true;
			else
				regularMealsNeeded = 0;
			
			int vegetarianMealsNeeded = vegetarianMealsOrdered - vegetarianMealOrdersPlaced;
			if(vegetarianMealsNeeded > 0)
				placeOrder = true;
			else
				vegetarianMealsNeeded = 0;
			
			int glutenFreeMealsNeeded = glutenFreeMealsOrdered - glutenFreeMealOrdersPlaced;
			if(glutenFreeMealsNeeded > 0) 
				placeOrder = true;
			else
				glutenFreeMealsNeeded = 0;
			
			int fishFreeMealsNeeded = fishFreeMealsOrdered - fishFreeMealOrdersPlaced;
			if(fishFreeMealsNeeded > 0) 
				placeOrder = true;
			else
				fishFreeMealsNeeded = 0;
			
			int nutFreeMealsNeeded = nutFreeMealsOrdered - nutFreeMealOrdersPlaced;
			if(nutFreeMealsNeeded > 0)
				placeOrder = true;
			else
				nutFreeMealsNeeded = 0;
			
			// if more orders actually need to be placed ...
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
				
				
				// add the new order to the list, then update how many orders have been placed
				orderList.orders.add(new Order(r,
						regularMealsNeeded, 
						vegetarianMealsNeeded, 
						glutenFreeMealsNeeded, 
						fishFreeMealsNeeded, 
						nutFreeMealsNeeded));
				
				regularMealOrdersPlaced += regularMealsNeeded;
				vegetarianMealOrdersPlaced += vegetarianMealsNeeded;
				glutenFreeMealOrdersPlaced += glutenFreeMealsNeeded;
				fishFreeMealOrdersPlaced += fishFreeMealsNeeded;
				nutFreeMealOrdersPlaced += nutFreeMealsNeeded;
			}
			
			else
				// if no order was placed then it's time to return
				return orderList;
		}
		
		// if the loop completed without returning a complete order then
		// there is not enough meals in stock
		orderList.incompleteOrder = true;
		return orderList;
	}
	
	public static void printOrderList(OrderList orderList,
		int regularMealsOrdered,
		int vegetarianMealsOrdered,
		int glutenFreeMealsOrdered,
		int fishFreeMealsOrdered,
		int nutFreeMealsOrdered ) {
		
		System.out.println("Total Order:");
		System.out.println("Regular meals: "+String.valueOf(regularMealsOrdered));
		System.out.println("Vegetarian meals: "+String.valueOf(vegetarianMealsOrdered));
		System.out.println("Gluten free meals: "+String.valueOf(glutenFreeMealsOrdered));
		System.out.println("Fish free meals: "+String.valueOf(fishFreeMealsOrdered));
		System.out.println("Nut free meals: "+String.valueOf(nutFreeMealsOrdered));
		System.out.println("");
		System.out.println("Order complete: "+String.valueOf(orderList.incompleteOrder));
		
		System.out.println("Restaurant Orders:\n");
		for(Order o : orderList.orders) {
			System.out.println(o.restaurant.getName());
			System.out.println("Rating: "+String.valueOf(o.restaurant.rating));
			System.out.println("Regular meals ordered: " 
								+ String.valueOf(o.getRegularMeals()));
			System.out.println("Vegetarian meals ordered: " 
					+ String.valueOf(o.getVegetarianMeals()));
			System.out.println("Gluten free meals ordered: " 
					+ String.valueOf(o.getGlutenFreeMeals()));
			System.out.println("Fish free meals ordered: " 
					+ String.valueOf(o.getFishFreeMeals()));
			System.out.println("Nut free meals ordered: " 
					+ String.valueOf(o.getNutFreeMeals()));
			
			System.out.println("");

		}
 	}
	
	
}
