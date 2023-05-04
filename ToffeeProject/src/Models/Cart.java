package Models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int ID;
    private List<Order> Orders;
    private static int LastID = 0;

    Cart(){
        LastID++;
        ID = LastID;
        this.Orders = new ArrayList<>();
    }

    public void addOrder(Order order){
        this.Orders.add(order);
    }

    public List<Order> getOrders(){
        return this.Orders;
    }
    public int getID(){
        return this.ID;
    }
}
