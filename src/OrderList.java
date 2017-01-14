import java.util.ArrayList;

/** Stores a boolean value to show whether the order is complete or not
 * 
 * @author Matthew
 *
 */
public class OrderList {
	
	public ArrayList<Order> orders;
	public boolean incompleteOrder;
	
	public OrderList() {
		this.orders = new ArrayList<Order>();
		this.incompleteOrder = true;
	}
}
