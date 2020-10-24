package Controller;

import Exceptions.NegativeValueException;
import Model.Items.Album;
import Model.Items.Game;
import Model.Items.Item;
import Model.Users.Customer;
import Exceptions.EmptyNameException;
import Utility.Scan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ControllerEmployee {

    //attributes
    private double totalRentProfit;

    private ArrayList<Item> allItems;
    private ArrayList<Customer> allCustomers;
    private ArrayList<Customer> upgradeRequests;

    //constructor method
    public ControllerEmployee(){
        this.allItems = new ArrayList<>();
        this.allCustomers = new ArrayList<>();
        this.upgradeRequests = new ArrayList<>();
    }

    //getters
    public ArrayList<Item> getAllItems() {
        return allItems;
    }

    public ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public ArrayList<Customer> getUpgradeRequests() {
        return upgradeRequests;
    }

    public void printTotalRentProfit() {
        Scan.print("The total rent profit is currently: " + totalRentProfit + " SEK");
    }
    //////////////////////////////////////CUSTOMER OPERATIONS///////////////////////////////////////////////////////////////

    public int customerIdGenerator(){

        int iD = 2000;

        if (allCustomers.isEmpty()){
            iD=1000; //if there are no items registered in the system, the first item's id will be 1000
        } else {
            iD = allCustomers.get(allCustomers.size() - 1).getUserId() + 1;  // else it will be the last registered item's id + 1
        }

        while (!(getCustomerById(iD)==null)){
            iD++;
        }

        return iD;
    }

    public void viewAllCustomers() { // Change to iterator

        for (Customer customer : allCustomers){
            Scan.print(customer.toString());
        }
    }

    public Customer getCustomerById(int id){

        Customer customer = null;

        Iterator<Customer> iterator = allCustomers.iterator();

        while (iterator.hasNext() && customer==null){
            Customer currentCustomer = iterator.next();
            if(currentCustomer.getUserId() == id){
                customer = currentCustomer;
            }
        }
        return customer;
    }

    public void registerCustomer() { //A customer has a unique id and a name and password

        String name = Scan.readLine("Registering new customer\nEnter the name of the customer: ");

        int id = customerIdGenerator();

        String password = Scan.readLine("Enter the password the customer wishes to have: ");

        Customer newCustomer = new Customer(id,password,name);
        allCustomers.add(newCustomer);
        System.out.println("You have successfully added a new customer:  \n" + newCustomer.toString());
    }

    //remove customer
    public void removeCustomer(){
        int idRemoval = Scan.readInt("Removing employee account \nEnter employee ID: ");

        Customer customer = getCustomerById(idRemoval);

        if (customer != null){
            allCustomers.remove(customer);
            Scan.print("Customer with ID" + idRemoval + " is now removed from the system.");
        } else {
            Scan.print("A customer with the ID you entered does not exist in out system.");
        }

    }

///////////////////////////////////////ITEM OPERATIONS///////////////////////////////////////////////////////////////////

    public void removeItem(){
        int itemId = Scan.readInt("Please enter the four digit ID of the item you want to remove: ");

        allItems.remove(findItemById(itemId)); //remove item from allItems arraylist, find item with findItemById method

        if (findItemById(itemId) instanceof Album){
            Scan.print("Album with ID " + itemId + " is now removed");
        } else if (findItemById(itemId) instanceof Game){
            Scan.print("Game with ID " + itemId + " is now removed");
        }

    }

    public Item findItemById(int itemId ){
        Item item = null;

        Iterator<Item> iterator = allItems.iterator();

        while(iterator.hasNext() && item==null) {
            Item currentItem = iterator.next();
            if (currentItem.getItemId() == itemId){
                item = currentItem;
            }
        }

        return item;
    }

    //ID generator
    public int itemIdGenerator(){

        int iD;

        if (allItems.isEmpty()){
            iD=1000; //if there are no items registered in the system, the first item's id will be 1000
        } else {
            iD = allItems.get(allItems.size() - 1).getItemId() + 1;  // else it will be the last registered item's id + 1
        }

        while ( findItemById(iD) != null ){ // make sure the ID isn't occupied
            iD++;
        }

        return iD;
    }

    //register new game
    public void registerGame() {
        try{

        Scan.print("Registering a new game");

        int gameId = itemIdGenerator();

        String title = Scan.readLine("Enter game title: ");

        double price = Scan.readDouble("Enter the daily rent price (in SEK) of this game: ");

        String genre = Scan.readLine("Enter genre: ");

        int releaseYear = Scan.readInt("Enter release year: ");

        Item newGame = new Game(gameId,title,price,releaseYear, genre);

        allItems.add(newGame);

        Scan.print("New game registered: \n"  + newGame.toString());

        } catch(EmptyNameException emptyNameException){
            System.out.println(emptyNameException.getMessage() + "Game name cannot be empty.");
        } catch (NegativeValueException negativeValueException){
            System.out.println(negativeValueException.getMessage() + "Game rent fee cannot be negative.");
        }
    }

    //register new album
    public void registerAlbum() {
        try{

        Scan.print("Registering a new album");

        int albumId = itemIdGenerator();

        String title = Scan.readLine("Enter the album title: ");

        String artist = Scan.readLine("Enter the artist of this album: ");

        int year = Scan.readInt("Enter the year this album was released: ");

        double price = Scan.readDouble("Enter the daily rent price (in SEK) of this album");

        Item newAlbum = new Album(albumId,title,price,year,artist);

        allItems.add(newAlbum);

        Scan.print("New album registered: " + Scan.EOL + newAlbum.toString());

        } catch (EmptyNameException emptyNameException){
            System.out.println(emptyNameException.getMessage() + "Album name cannot be empty.");
        } catch (NegativeValueException negativeValueException){
            System.out.println(negativeValueException.getMessage() + "Album rent fee cannot be negative.");
        }
    }

    // Sort by ratings (four methods)

    //takes a sorted list of all items and filters it to return only the games and their average ratings
    public ArrayList<Item> gamesByRatings() {
        ArrayList<Item> itemsSorted = sortByRatings();
        ArrayList<Item> gamesSorted = new ArrayList<>();

        for ( Item item : itemsSorted ) {
            if (item instanceof Game) {
                gamesSorted.add(item);
            }
        }

        for ( Item game : gamesSorted ) {
            Scan.print( "Title: " + game.getTitle() + System.lineSeparator() +
                        "ID: " + game.getItemId() + System.lineSeparator() +
                        "Avg. rating: " + game.averageRating() + System.lineSeparator());
        }
        return gamesSorted;
    }

    //takes a sorted list of all items and filters it to return only the albums
    public ArrayList<Item> albumsByRatings() {
        ArrayList<Item> itemsSorted = sortByRatings();
        ArrayList<Item> albumsSorted = new ArrayList<>();

        for ( Item item : itemsSorted ) {
            if (item instanceof Album) {
                albumsSorted.add(item);
            }
        }
        for ( Item album : albumsSorted ) {
            Scan.print( "Title: " + album.getTitle() + System.lineSeparator() +
                        "ID: " + album.getItemId() + System.lineSeparator() +
                        "Avg. rating: " + album.averageRating() + System.lineSeparator());
        }
        return albumsSorted;
    }

    //takes a sorted arraylist of all item's average ratings values from big-small
    //matches that order to the corresponding objects and returns that list of objects sorted by rating
    public ArrayList<Item> sortByRatings() {
        ArrayList<Item> itemsSorted = new ArrayList<>();
        //double rating = 0;

        //Receives the avg rating values of all objects sorted big-small
        ArrayList<Double> ratingsSorted = sortRatings();

        //Matches items with ratings and stores them in a sorted al
        for (int i = 0; i<ratingsSorted.size(); i++) {
            double currentRating = ratingsSorted.get(i);
            for (int j = 0; j <allItems.size(); j++) {
                Item currentItem = allItems.get(j);
                double currentItemAvg = allItems.get(j).averageRating();
                if (currentRating == currentItemAvg) {
                    if (!itemsSorted.contains(currentItem)) {
                        itemsSorted.add(currentItem);
                    }
                }
            }
        }
        return itemsSorted;
    }

    //takes all items' average ratings and sorts those values from big to small
    //returns a sorted list of all average ratings
    public ArrayList<Double> sortRatings() {
        ArrayList<Double> ratingsUnsorted = new ArrayList<>();
        double rating = 0;

        for ( int i = 0; i<allItems.size();i++) { //retrieves all items' ratings and add them to a list
            Item currentItem = allItems.get(i);
            rating = currentItem.averageRating();
            ratingsUnsorted.add(rating);
        }

        Collections.sort(ratingsUnsorted, Collections.reverseOrder()); //sorts the list from big to small

        ArrayList<Double> ratingsSorted = ratingsUnsorted; // changes the reference to better represent the list
        return ratingsSorted;
    }



    /////////////////////////////////////////MEMBERSHIP OPERATIONS/////////////////////////////////////////////////////

    public void viewUpgradeRequests(){
        int i=0;
        while (i<upgradeRequests.size()){
            int j=1;
            Scan.print(j + ". " + upgradeRequests.get(i).getUserId());
            j++;
            i++;
        }

        int option = Scan.readInt("Press 1 to accept upgrade requests or press 2 to decline");
        if (option == 1){
            int id = Scan.readInt("Enter the four digit id of the customer you would like to upgrade.");
            Customer customer = getCustomerById(id);
            customer.upgradeMembership();
            upgradeRequests.remove(customer);
        } else if (option == 2){
            int id = Scan.readInt("Enter the four digit id of the customer you would like to deny membership upgrade: ");
            Customer customer = getCustomerById(id);
            upgradeRequests.remove(customer);
        } else{
            Scan.print("Invalid input.");
        }

    }

    //METHOD HERE TO UPGRADE CUSTOMER
   /* public  void assignMembership(Customer customer) {

        for (int i = 0; i<allCustomers.size()&&!isTargetIdFound; i++) {
            Scan.print("You are currently managing the membership of" + customer.toString());
            String request = Scan.readLine("Enter 'yes' to approve or 'no' to decline:");
            if (request.toLowerCase().equals("yes")) {

                if (customer instanceof Customer) {
                    customer = new SilverMember(customer.getUserId(), customer.getPassword(),
                            customer.getName(), customer.getRentingNow(),
                            customer.getTotalRents(), customer.getCredits());
                    Scan.print("You have upgraded the customer to silver customer level.");

                } else if (customer instanceof SilverMember) {
                    customer = new GoldMember(customer.getUserId(), customer.getPassword(),
                            customer.getName(), customer.getRentingNow(),
                            customer.getTotalRents(), customer.getCredits());
                    Scan.print("You have upgraded the customer to gold customer level.");

                } else if (customer instanceof GoldMember) {
                    customer = new PlatinumMember(customer.getUserId(), customer.getPassword(),
                            customer.getName(), customer.getRentingNow(),
                            customer.getTotalRents(), customer.getCredits());
                    Scan.print("You have upgraded the customer to Platinum customer level.");

                }else if (request.toLowerCase().equals("no")) {
                    upgradeRequests.remove(customer);
                    System.out.println("You have declined the membership request");
                }
            }
            isTargetIdFound = true;
        }if(!isTargetIdFound) {
            Scan.print("Invalid input");

        }

        // lägg till getCreditsPerItem(); metod
    }*/
}