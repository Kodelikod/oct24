package Controller;

import Model.Items.Album;
import Model.Items.Game;
import Model.Items.Item;
import Model.Message;
import Model.Rating;
import Model.Transaction;
import Model.Users.*;
import Exceptions.InvalidOperationException;
import Utility.Scan;

import java.util.ArrayList;

public class ControllerCustomer {

    //attributes
    private ArrayList<Message> allMessages;
    private ArrayList<Message> myInbox;

    //constructor method
    public ControllerCustomer() {

        this.allMessages = new ArrayList<>();
        this.myInbox = new ArrayList<>();
    }

    //getters
    public ArrayList<Message> getAllMessages() {
        return allMessages;
    }
    public ArrayList<Message> getMyInbox() {
        return myInbox;
    }

    ////////////////////////////////////////////////METHODS ITEM ///////////////////////////////////////////////////////

  /*  public boolean allowedToRent(Customer customer){

        boolean canRent;
        int currentlyRenting = customer.getAmntCurrentRents();


        if (customer.getMembership()==null){
            canRent = currentlyRenting==0;
        } else {
            canRent = !(customer.getMembership().hitRentLimit( currentlyRenting ));
        }

        if (!canRent){
            Scan.print("You have reached your renting limit. Apply for an upgrade or return a rented item.");
        }
        return canRent;
    }
*/

    //method to rent item (both game and album) - failed to make this one functional but we will keep working on it
    public void rentItem(Customer customer, String albumOrGame, ControllerEmployee controllerEmployee) {

        boolean allowedToRent; //has the customer reached renting limit or not?
        if (customer.getMembership()==null){
            allowedToRent = customer.getMembership().allowedToRent(customer);
        } else {
            allowedToRent = customer.getMembership().allowedToRent(customer);
        }

        if (!allowedToRent){

            Scan.print("You have reached your renting limit for your membership level. Apply for a membership upgrade " +
                    "or return a rented item.");

        } else {

            int itemId = Scan.readInt("Enter the four digit ID of the " + albumOrGame + " that you're " +
                    "searching for: ");

            Item item = controllerEmployee.findItemById(itemId);

            if (item==null) { //check if the ID the user entered is valid

                Scan.print("The item ID you entered does not exist in our system");

            } else {

                if ( !item.isAvailable() ){ //check if the item is available

                    Scan.print("This item is not available. It's currently rented by another member with the ID "
                            + item.getCurrentRenter());

                } else if( item.isAvailable() ){

                    item.setAvailable(false); // calling searchFeature method to find album/game and make the item unavailable for rent
                    item.setCurrentRenter(customer.getUserId());

                    customer.getItemsCurrentlyRenting().add( item );
                    customer.setTotalRents(+1); //update customers renting history

                    Scan.print("You have now rented the " + albumOrGame + " " + item.getTitle() + " with the ID " + item.getItemId()
                            + "\nEnjoy!");
                }
            }
        }
    }


    //return rented item method - failed to make this functional but working on it.
    public void returnItem(Customer customer, ControllerEmployee controllerEmployee, ControllerManager controllerManager) {

        // step 1: which item?
        Item item;
        try {
            do {
                int itemId = Scan.readInt("Enter the four digit ID of the item that you want to return: ");
                item = controllerEmployee.findItemById(itemId);
            } while (item == null);

            int newCredits = customer.getMembership().getCreditsPerItem(); //retrieve amount of credit that the membership type gives

            customer.setCredits( + newCredits ); // add the newCredits to members total credits

            item.setAvailable(true);
            item.setCurrentRenter(0000);

            customer.getItemsCurrentlyRenting().remove( item ); //removes returned item from customers list of current rents

            double discountedPrice = getDiscountedPrice( customer, item );

            double finalPrice = getFinalPrice( customer, discountedPrice);

            storeTransaction(item, customer, finalPrice, controllerManager);


            String answer = askForRating();
            answer = answer.toLowerCase().trim();
            if (answer.equals("yes")) {
                leaveReview(item);
            } else if (!answer.equals("no")) {
                askForRating();
            }

        } catch (InvalidOperationException invalidOperationException) {
            Scan.print(invalidOperationException.getMessage());
        }
    }

    public double getFinalPrice(Customer customer, double discountedPrice){

        if (customer.getCredits() >= 5) { //condition: customer reached 5 credits
            discountedPrice = 0;
            customer.setCredits(-5);
            Scan.print("Congratulations! You have reached the amount of credits to rent this item for free!"
                    + "\nYou now have " + customer.getCredits() + " credit points" );
        } else {
            Scan.print("Your total is: " + discountedPrice + " SEK");
        }

        double finalPrice = discountedPrice;

        return finalPrice;
    }

    public double getDiscountedPrice(Customer customer, Item item){

        double preDiscount = item.totalRentFee(); //calling the totalRentFee method and assigning the return value
        double postDiscount = customer.getMembership().getDiscountedPrice(preDiscount); //call method to calculate price after discount

        return postDiscount;
    }

    public void storeTransaction(Item item, Customer customer, double totalCost, ControllerManager controllerManager){
        Transaction newTransaction = new Transaction(item, customer, totalCost);
        controllerManager.getAllTransactions().add(newTransaction);

    }

    public String askForRating() {
        String answer = Scan.readLine("Would you like to rate the item? Please enter Yes or No. ");
        return answer;
    }

    public void leaveReview(Item item) {
        int rating;
        String review = "";

        do {
            rating = Scan.readInt("Please rate the item 0-5!");

        } while (5 < rating || rating < 0);

        String answer = Scan.readLine("Would you also like to leave a written review? Please enter Yes or No.");

        if (answer.toLowerCase().trim().equals("yes")) {
            review = Scan.readLine("Please type your written review here: ");
        }

        Rating itemRating = new Rating(rating, review);
        item.getRatings().add(itemRating);

    }

    //apply for membership upgrade
    public void applyForUpgrade(Customer customer, ControllerEmployee controllerEmployee) {
        if (customer.getMembership() instanceof RegularCustomer) {
            controllerEmployee.getUpgradeRequests().add(customer);
            Scan.print("You have now applied for a Silver membership." + Scan.EOL);
        } else if (customer.getMembership() instanceof SilverMember) {
            controllerEmployee.getUpgradeRequests().add(customer);
            Scan.print("You have now applied for a Gold membership." + Scan.EOL);
        } else if (customer.getMembership() instanceof GoldMember){
            controllerEmployee.getUpgradeRequests().add(customer);
            Scan.print("You have now applied for a Platinum membership." + Scan.EOL);
        } else if (customer.getMembership() instanceof PlatinumMember){
            Scan.print("You are already a platinum member. This is it, you already made it. Celebrate!" + Scan.EOL);
        }

        /*for (int i = 0; i < allCustomers.size(); i++) {

            if (customer instanceof Customer) {
                upgradeRequests.add(customer);
                Scan.print("You have now applied for a Silver membership." + Scan.EOL);

                //} else if (customer instanceof SilverMember) {
                upgradeRequests.add(customer);
                Scan.print("You have now applied for a Gold membership." + Scan.EOL);

                //} else if (customer instanceof GoldMember) {
                upgradeRequests.add(customer);
                Scan.print("You have now applied for a Platinum membership" + Scan.EOL);
                isTargetIdFound = true;
            } else {
                Scan.print("You're already a platinum customer, there is no membership level higher than this." +
                        " Congratulations, you made it. " + Scan.EOL);
            }
        }*/
    }

    public void viewItemList(ControllerEmployee controllerEmployee) {
        for (int i = 0; i < controllerEmployee.getAllItems().size(); i++) {
            Scan.print(controllerEmployee.getAllItems().get(i).toString());
        }
    }

    //////////////////////////////MESSAGING FEATURES///////////////////////////////////////////////////////////////////

    //method to send message
    public void sendMessage (Customer customer, ControllerEmployee controllerEmployee) {
        Message newMessage = new Message(0,0, false, null);
        String message;

        int recId = Scan.readInt("Please type the id of the recipient: ");

        Customer recipient = controllerEmployee.getCustomerById(recId);

        if (!(recipient==null)){
            message = Scan.readLine("Type your message: ");

            newMessage.setSenderID(customer.getUserId());

            newMessage.setRecipientID(recId);

            newMessage.setMessage(message);

            allMessages.add(newMessage);

            Scan.print("Message sent!");
        } else {
            Scan.print("The ID you entered does not match with any customer in our system");
            //take customer to previous page
        }

       /* for (int i = 0; i < allCustomers.size() && !isTargetIdFound; i++) {
            if (allCustomers.get(i).getUserId() == recId) {
                Scan.scanLine(); //this is necessary!!!! DONT REMOVE bc the program ends without it
                message = Scan.readLine("Type your message: ");

                newMessage.setSenderID(customer.getUserId());

                newMessage.setRecipientID(recId);

                newMessage.setMessage(message);

                allMessages.add(newMessage);

                isTargetIdFound = true;

                Scan.print(newMessage.toString() + Scan.EOL + "This message is now sent");
                //insert method here that takes user back to their own login page
            }
        }
        if (!isTargetIdFound) {
            int option = Scan.readInt("ID not found. " + Scan.EOL + "Press 1 to try again with valid ID " +
                    "or press 2 to go back to your login page");
            if (option == 1) {
                sendMessage(senderId);
            } else {
                //insert method here that takes user to their own login page
            }
        }*/
    }

    //method to view all unread/new messages
    public void viewUnMessage (Customer customer) {
        ArrayList<Message> unreadInbox = new ArrayList<>();
        ArrayList<Message> inbox = customer.getInbox();
        int i=0;
        while (i<inbox.size() && !inbox.get(i).isRead()){
            int listLine = 1;
            unreadInbox.add(inbox.get(i));
            Scan.print(listLine + ". " + unreadInbox.get(i).getSenderID());
            listLine++;
            i++;
        }

        /*for (int i = 0; i < allMessages.size() && allMessages.get(i).getRecipientID() == recId &&   //messages to the right recipient and is also unread
                allMessages.get(i).isRead() == false; i++) {
            Scan.print(listLine + ". " + allMessages.get(i).getSenderID() + Scan.EOL);
            unreadInbox.add(allMessages.get(i));
            listLine++;
        }*/
        if (!unreadInbox.isEmpty()) {
            int option = Scan.readInt("Enter the number of the message that you want to open: ");
            Scan.print(unreadInbox.get(option - 1).toString());
            unreadInbox.get(option - 1).setRead(true);
        } else {
            Scan.print("There are no unread messages in your inbox!");
        }

        int newOption = Scan.readInt("Press 1 to go back to your inbox, or press 2 to go back to you login page");

        if (newOption == 1) {
            viewUnMessage(customer);
        } else {
            //method here that takes the user back to their login page
        }
    }



    //method for removing a message
    public void removeMessage(Customer customer) {
        viewMyInbox(customer);
        int option = Scan.readInt("Enter the number of the message that you want to remove: ") - 1;

        allMessages.remove(myInbox.get(option));
        myInbox.remove(myInbox.get(option));

        Scan.print("The message you wanted to remove has been deleted." + Scan.EOL);
        for (int i = 0; i <myInbox.size() ; i++) { //print the list without the after message removal
            Scan.print(myInbox.get(i).toString());
        }

        //!!call method here to take user back to their login page
    }

    public void viewMyInbox (Customer customer){
        int i = 0;

        ArrayList<Message> inbox = customer.getInbox();

        while (i<inbox.size()){
            Scan.print(i+1 + ". " + inbox.get(i).getSenderID());
            i++;
        }

        int option = Scan.readInt("Enter the number of the message you want to open");

        Scan.print(inbox.get(option-1).toString());
        inbox.get(option-1).setRead(true);

        int nextOption = Scan.readInt(Scan.EOL + "Press 1 to go back to inbox, press 2 to to go back to previous " +
                "page");
        if (nextOption==1){
            viewMyInbox(customer);
        } else {
            //call method here to take back to previous page
        }


       /* for (int i = 0; i < allMessages.size() && allMessages.get(i).getRecipientID() == userId; i++) {
            Scan.print(listLine + ". " + allMessages.get(i).getSenderID() + Scan.EOL);
            myInbox.add(allMessages.get(i));
            listLine++;
        }*/
    }



    //method to see items sorted by ratings and year
    //again, failed to implement this one properly, more homework.....
    public void showOptionsGame(ControllerEmployee controllerEmployee) {
        ArrayList<Item> allItems = controllerEmployee.getAllItems();
        ArrayList<String> genres = new ArrayList();
        int numOfGenres = 0;

        for (Item item : allItems) {
            if (item instanceof Game) {
                if (!genres.contains(((Game) item).getGenre())) {
                    genres.add(((Game) item).getGenre());
                }
            }
        }
        for (String genre : genres) {
            numOfGenres++;
            System.out.println(numOfGenres + ". " + genre);
        }

        String userChoice = Scan.readLine("Type which genre you want to search for:");

        for (Item item : allItems) {
            if (item instanceof Game) {
                if (((Game) item).getGenre().toLowerCase().equals(userChoice)){
                    this.toString();
                }
            }
        }
    }


    public void showOptionsAlbum (ControllerEmployee controllerEmployee) {
        ArrayList<Item> allItems = controllerEmployee.getAllItems();
        ArrayList<Integer> years = new ArrayList();
        int numOfYears = 0;

        for (Item item : allItems) {
            if (item instanceof Album) {
                if (!years.contains(((Album) item).getReleaseYear())){
                    years.add(((Album) item).getReleaseYear());
                }
            }
        }

        for (int year : years) {
            numOfYears++;
            System.out.println(numOfYears + ". " + year);

            String userChoice = Scan.readLine("Which year do you want to search for:");

            for(Item item : allItems){
                if (item instanceof Album) {
                    if (Integer.parseInt(userChoice) == ((Album) item).getReleaseYear()) {
                        this.toString();
                    }
                }
            }
        }
    }

    /*public void showRatingsListGame () {
        ArrayList<Game> allGamesByRating = new ArrayList<>();

        for (Item item : allItems) {
            if (item instanceof Game) {
                allGamesByRating.add(this.item);
            }
        }


        //EPIC FEATURE 11! Woohoo! No, it also does not work properly.....
        // we really didn't get this one hehe
        Collections.sort(allGamesByRating, new Comparator<Game>() {
            @Override
            public int compare(Game game1, Game game2) {
                return Double.valueOf(game1.averageRating()).compareTo(game2.averageRating());
            }
        });

        for (int i = 0; i < allGamesByRating.size(); i++) {
            Scan.print(allGamesByRating);
        }

        public void showRatingsListAlbum () {
            ArrayList<Album> allAlbumsByRating = new ArrayList<>();

            for (Item item : allItems) {
                if (item instanceof Album) {
                    allAlbumsByRating.add(item);
                }
            }

            Collections.sort(allAlbumsByRating, new Comparator<Album>() {
                @Override
                public int compare(Album album1, Album album2) {
                    return Double.valueOf(album1.averageRating()).compareTo(album2.averageRating()); // inte bara game 1 & 2?
                }
            });

            for (int i = 0; i < allAlbumsByRating.size(); i++) {
                Scan.print(allAlbumsByRating);
            }*/

}

