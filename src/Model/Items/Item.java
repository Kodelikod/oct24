package Model.Items;

import Exceptions.NegativeValueException;
import Model.Rating;
import Exceptions.EmptyNameException;
import Exceptions.InvalidOperationException;
import Model.Transaction;
import Utility.Scan;
//import Utility.TimeCalc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public abstract class Item implements Comparable {

    //attributes
    private int itemId;
    private String title;
    private double dailyRentFee;
    private boolean available ;
    private double userRating;
    private int timesRented;
    private ArrayList<Rating> ratings;
    private int currentRenter;
    private int releaseYear;

    //constructor method
    public Item(int id, String title, double dailyRentFee, int releaseYear) throws RuntimeException{
        this.itemId = id;
        if(title.isEmpty()) {
            throw new EmptyNameException("Invalid data.");
        }else {this.title = title;}
        if (dailyRentFee < 0 ) {
            throw new NegativeValueException("Invalid data.");
        }else{ this.dailyRentFee = dailyRentFee;}
        this.available = true;
        this.timesRented = 0;
        this.ratings = new ArrayList<>();
        this.currentRenter = 0000;
        this.userRating = 0;
        this.releaseYear = releaseYear;
    }

    //getters and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDailyRentFee() {
        return dailyRentFee;
    }

    public void setDailyRentFee(double dailyRentFee) {
        this.dailyRentFee = dailyRentFee;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public int getTimesRented() {
        return timesRented;
    }

    public void setTimesRented(int timesRented) {
        this.timesRented = timesRented;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public int getCurrentRenter() {
        return currentRenter;
    }

    public void setCurrentRenter(int currentRenter) {
        this.currentRenter = currentRenter;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getTimesRented(ArrayList<Transaction> allTransactions){
        int timesRented = 0;
        for (Transaction transaction : allTransactions){
            if (transaction.getItem() == this){
                timesRented++;
            }
        }
        return timesRented;
    }

    public double getItemProfit(ArrayList<Transaction> allTransactions){
        double rentProfit = 0;
        for (Transaction transaction : allTransactions){
            if (transaction.getItem() == this){
                rentProfit = rentProfit + transaction.getTotalCost();
            }
        }
        return rentProfit;
    }

    //toString method
    @Override
    public String toString() {
        String availability;
        if (this.isAvailable()){
            availability = "Available";
        } else {
            availability = "Not available at the moment, currently rented by " + currentRenter;
        }
        return "Item ID: " + itemId +
                Scan.EOL + "Title: " + title + Scan.EOL +
                "Daily rent fee: " + dailyRentFee + Scan.EOL +
                "Availability: " + availability +
                Scan.EOL + "Times rented: " + timesRented + " times." + Scan.EOL +
                "Ratings: " + getRatings() +Scan.EOL;
    }

    //FRANCISCO: The totalRentFee calculation for returning a specific game should be in the game and not in the
    // controller. The game object has the information of its daily price, so it should be calculated in the Game object.
    //I suggest adjusting that in your GameController class. This argument also applies to Employee and the calculation
    //of its netSalary in the EmployeeController.

    //method to calculate daily rent fee
    public double totalRentFee() throws InvalidOperationException {

        LocalDate today = LocalDate.now(); //local date of today

        int year = Scan.readInt("Please enter the first day of renting, first enter the year (four digits): ");
        int month = Scan.readInt("Enter the month (two digits): ");
        int day = Scan.readInt("Enter the day of the month (two digits");

        LocalDate from = LocalDate.of(year, month, day);
        long noOfDaysBetween = ChronoUnit.DAYS.between(from, today); //get amount of days passed from "from" to "today"
        int days = (int)noOfDaysBetween; //converts the long value to an int
        if (days <= 0){
            throw new InvalidOperationException("Invalid operation. Upon returning an item, the number of days rented must be positive"); }

        //  double amountOfDays = time.daysBetween(); //calls a method that asks for date and counts passed days
        double totalRentFee = days * dailyRentFee;

        return totalRentFee;
    }

    public double averageRating() {
        int sum = 0;
        for(int i=0; i<this.ratings.size(); i++) {
            int number = ratings.get(i).getNumber();
            sum = sum + number;
        }
        double mean = ((double)sum) / (this.ratings.size()); // Källa: Lecture3_ 0909
        return mean;
    }

    public void searchItems(){  //Don't forget implementing this

    }
    @Override
    public int compareTo(Object object) {

        this.averageRating();
        return -1;
    }
}
