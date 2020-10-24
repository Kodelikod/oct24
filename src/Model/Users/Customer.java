package Model.Users;

import Model.Message;
import Model.Transaction;
import Utility.Scan;
import Model.Items.*;

import java.util.ArrayList;

public class Customer {

    //attributes
    private int userId;
    private String password;
    private String name;
    private ArrayList<Item> itemsCurrentlyRenting;
    private int totalRents;
    private int credits;
    private Membership membership;
    private ArrayList<Message> inbox;

    //constructor
    public Customer(int userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.itemsCurrentlyRenting = new ArrayList<>();
        this.totalRents = 0; // make this an arraylist?
        this.credits = 0;
        this.membership = new RegularCustomer();
        this.inbox = new ArrayList<>();
    }

    //getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItemsCurrentlyRenting() {
        return itemsCurrentlyRenting; }

    public void setItemsCurrentlyRenting(ArrayList itemsCurrentlyRenting) { this.itemsCurrentlyRenting = itemsCurrentlyRenting; }

    public int getTotalRents() {
        return totalRents;
    }

    public void setTotalRents(int totalRents) {
        this.totalRents = totalRents;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public ArrayList<Message> getInbox(){
        return inbox;
    }

    public double getCustomerProfit(ArrayList<Transaction> allTransactions){
        double totalProfit = 0;
        for (Transaction transaction : allTransactions){
            if (transaction.getCustomer() == this){
                totalProfit = totalProfit + transaction.getTotalCost();
            }
        }
        return totalProfit;
    }

    /*public int getAmntCurrentRents(){
        int amount = itemsCurrentlyRenting.size();
        return amount;
    }*/


    public String toString() {
        String membershipLvl = "";
        if (membership==null){
            membershipLvl = "regular";
        } else if ( membership instanceof SilverMember ){
            membershipLvl = "Silver";
        } else if ( membership instanceof GoldMember ){
            membershipLvl = "Gold";
        } else if ( membership instanceof PlatinumMember ){
            membershipLvl = "★ Platinum ★";
        }

        ArrayList<String> rentingNow = new ArrayList<>();
        for (Item item : itemsCurrentlyRenting){
            rentingNow.add(item.getItemId() + ", " + item.getTitle());
        }
        return "Customer ID: " + userId + Scan.EOL + "Name: " + name + Scan.EOL + "Password: " + password + Scan.EOL +
                "Currently renting: " + rentingNow + Scan.EOL + "Total amount of items rented: "
                + totalRents + Scan.EOL + "Credit points: " + credits + Scan.EOL + "Membership level: " + membershipLvl
                + Scan.EOL + "Discount: none" + Scan.EOL + "Renting limit: 1 " + Scan.EOL + "Gains 0 credits per item"
                +Scan.EOL;
    }

    public void upgradeMembership(){ //ska den ligga här?
        if (this.membership instanceof RegularCustomer){
            this.membership = new SilverMember();
            Scan.print("This customer is now a Silver member");
        } else if (this.membership instanceof SilverMember) {
            this.membership = new GoldMember();
            Scan.print("This customer is now a Gold member");
        } else if (this.membership instanceof GoldMember) {
            this.membership = new PlatinumMember();
            Scan.print("This customer is now a Platinum member");
        } else if (this.membership instanceof PlatinumMember) {
            Scan.print("This customer has already achieved our highest membership status.");
        } else {
            Scan.print("This customer appears to not be in our system, please contact customer support.");
        }
    }
}