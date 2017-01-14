import java.util.ArrayList;

/** Contains a list of orders and a boolean value to show whether the order 
 *  is complete or not
 *  
 * @author chrx
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
