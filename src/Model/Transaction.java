package Model;

import Model.Items.Item;
import Model.Users.Customer;

public class Transaction {

    //attributes
    private Item item;
    private Customer customer;
    private double totalCost;

    //constructor
    public Transaction(Item item, Customer customer, double totalCost) {
        this.item = item;
        this.customer = customer;
        this.totalCost = totalCost;
    }

    //getters and setters
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    //toString
    @Override
    public String toString() {
        return customer.getUserId() + ", " + item.getItemId() + ", " + item.getTitle() + ", " + totalCost + "SEK";
    }
}

