import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class OrderEngineTest {
	
	@Test
	/** A regular order that should succeed
	 * 
	 */
	public void testCompleteOrder() {
		
		ArrayList<Restaurant> testRestaurants = new ArrayList<Restaurant>();

		// cheat-sheet for meal types:     r, r,  v,  gf, ff, nf
		testRestaurants.add(new Restaurant(4, 30, 10, 10, 10, 10, "Restaurant 1"));
		testRestaurants.add(new Restaurant(5, 10, 20, 0,  5,  0, "Restaurant 2"));
		testRestaurants.add(new Restaurant(2, 6,  0,  5,  20, 0, "Restaurant 3"));
		testRestaurants.add(new Restaurant(1, 50, 50, 50, 50, 50, "Restaurant 3"));
		
		Directory restaurants = new Directory(testRestaurants);
		
		// define a test order
		int regularMeals = 45;
		int vegetarianMeals = 4;
		int glutenFreeMeals = 12;
		int fishFreeMeals = 8;
		int nutFreeMeals = 10;
		OrderList testOrderList = 
				OrderEngine.createOrderList(restaurants, regularMeals, 
						vegetarianMeals, glutenFreeMeals, fishFreeMeals, nutFreeMeals);
		
		assertEquals(testOrderList.orders.size(), 3);
		assertEquals(testOrderList.incompleteOrder, false);
		
		assertEquals(testOrderList.orders.get(0).restaurant.rating, 5);
		assertEquals(testOrderList.orders.get(1).restaurant.rating, 4);
		assertEquals(testOrderList.orders.get(2).restaurant.rating, 2);
		
		assertEquals(testOrderList.orders.get(0).getRegularMeals(), 10);
		assertEquals(testOrderList.orders.get(0).getVegetarianMeals(), 4);
		assertEquals(testOrderList.orders.get(0).getGlutenFreeMeals(), 0);
		assertEquals(testOrderList.orders.get(0).getFishFreeMeals(), 5);
		assertEquals(testOrderList.orders.get(0).getNutFreeMeals(), 0);
		
		assertEquals(testOrderList.orders.get(1).getRegularMeals(), 30);
		assertEquals(testOrderList.orders.get(1).getVegetarianMeals(), 0);
		assertEquals(testOrderList.orders.get(1).getGlutenFreeMeals(), 10);
		assertEquals(testOrderList.orders.get(1).getFishFreeMeals(), 3);
		assertEquals(testOrderList.orders.get(1).getNutFreeMeals(), 10);
		
		assertEquals(testOrderList.orders.get(2).getRegularMeals(), 5);
		assertEquals(testOrderList.orders.get(2).getVegetarianMeals(), 0);
		assertEquals(testOrderList.orders.get(2).getGlutenFreeMeals(), 2);
		assertEquals(testOrderList.orders.get(2).getFishFreeMeals(), 0);
		assertEquals(testOrderList.orders.get(2).getNutFreeMeals(), 0);
	}
	
	@Test
	/** An order which should fail (not enough meals in stock across all restaurants)
	 * 
	 */
	public void testIncompleteOrder() {
		
		ArrayList<Restaurant> testRestaurants = new ArrayList<Restaurant>();
		
		// cheat-sheet for meal types:     r, r,  v,  gf, ff, nf
		testRestaurants.add(new Restaurant(5, 10, 10, 10, 10, 10, "Restaurant 1"));
		testRestaurants.add(new Restaurant(3, 10, 10, 10,  0,  10, "Restaurant 2"));
		
		Directory restaurants = new Directory(testRestaurants);
		
		// define a test order
		int regularMeals = 15; // R1: 10, R2: 5
		int vegetarianMeals = 20; // R1: 10, R2: 10
		int glutenFreeMeals = 5; // R1: 5, R2: 0
		int fishFreeMeals = 20; // not enough in stock, R1: 10, r2: 0
		int nutFreeMeals = 1; // R1: 1, R2: 0
		OrderList testOrderList = 
				OrderEngine.createOrderList(restaurants, regularMeals, 
						vegetarianMeals, glutenFreeMeals, fishFreeMeals, nutFreeMeals);
		
		assertEquals(testOrderList.orders.size(), 2);
		assertEquals(testOrderList.incompleteOrder, true);
		
		assertEquals(testOrderList.orders.get(0).restaurant.rating, 5);
		assertEquals(testOrderList.orders.get(1).restaurant.rating, 3);
		
		assertEquals(testOrderList.orders.get(0).getRegularMeals(), 10);
		assertEquals(testOrderList.orders.get(0).getVegetarianMeals(), 10);
		assertEquals(testOrderList.orders.get(0).getGlutenFreeMeals(), 5);
		assertEquals(testOrderList.orders.get(0).getFishFreeMeals(), 10);
		assertEquals(testOrderList.orders.get(0).getNutFreeMeals(), 1);
		
		assertEquals(testOrderList.orders.get(1).getRegularMeals(), 5);
		assertEquals(testOrderList.orders.get(1).getVegetarianMeals(), 10);
		assertEquals(testOrderList.orders.get(1).getGlutenFreeMeals(), 0);
		assertEquals(testOrderList.orders.get(1).getFishFreeMeals(), 0);
		assertEquals(testOrderList.orders.get(1).getNutFreeMeals(), 0);
	}
}
