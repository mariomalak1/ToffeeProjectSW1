package Models;

import Controllers.CandyController;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int ID;
    private List<Order> Orders;
    private List<Voucher> Vouchers;
    private int CustomerID;
    private boolean Finished;
    private static int LastID = 0;

    public Cart(int customerId){
        LastID++;
        CustomerID = customerId;
        ID = LastID;
        this.Orders = new ArrayList<>();
        this.Vouchers = new ArrayList<>();
        Finished = false;
    }

    public void addOrder(Order order){
        this.Orders.add(order);
    }

    public List<Order> getOrders(){
        return this.Orders;
    }

    public void setOrders(List<Order>orders){
        Orders = orders;
    }

    public Order getOrder(int orderId){
        for (Order order : this.Orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null; // Voucher with voucherId not found
    }

    public void deleteOrder(Order order){
        this.Orders.remove(order);
    }

    public void deleteOrders(){
        this.Orders.clear();
    }

    public int getID(){
        return this.ID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public List<Voucher> getVouchers() {
        return Vouchers;
    }

    public void addVoucher(Voucher vou) {
        this.Vouchers.add(vou);
    }

    public void deleteVoucher(Voucher vou){
        this.Vouchers.remove(vou);
    }

    public void deleteVouchers(){
        this.Vouchers.clear();
    }

    public Voucher getVoucher(int voucherId) {
        for (Voucher voucher : this.Vouchers) {
            if (voucher.getId() == voucherId) {
                return voucher;
            }
        }
        return null; // Voucher with voucherId not found
    }

    public void setID(int id){
        ID = id;
    }

    public boolean isFinished() {
        return Finished;
    }

    public void setFinished(boolean finished){
        Finished = finished;
    }

    public double getTotalCostOfCart(){
        double total = 0;
        for (Order order: Orders) {
            Candy candyOfOrder = new CandyController().getCandyDetails(order.getCandyId());
            total += (order.getQuantity() * candyOfOrder.getPrice());
        }
        return total;
    }
}
