


//--------------------------------------------ALTERNATIV PÅ MENY-----------------------------------------------

/*import Controller.ControllerCustomer;
import Controller.ControllerEmployee;
import Controller.ControllerManager;
import Utility.Scan;
import View.MainView;

public class CRAYCRAYCLASS {

    public void managerMenu(ControllerEmployee controllerEmployee, ControllerManager controllerManager,
                            ControllerCustomer controllerCustomer, MainView viewMain) {

        String password = Scan.readLine("Please enter your password:");
        if (password.equals("admin1234")) {

            while (viewMain.isRunning()) {

                String stringInput = Scan.readLine("Manager Screen - Type one of the options below:" + Scan.EOL +
                        "1. Employee options" + Scan.EOL +
                        "2. Retrieve rent history options" + Scan.EOL+
                        "3. Return to main menu" + Scan.EOL);

                int intInput = (Integer.parseInt(stringInput));
                switch (intInput) {
                    case 1:
                        employeeOptions();
                        break;
                    case 2:
                        retrieveRentHistory();
                        break;
                    case 3:
                        viewMain.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
                        break;

                    default:
                        System.out.println("Invalid input, please try again.");
                }
            }
        } else {
            System.out.println("Invalid Password");
            viewMain.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
        }

        public void employeeOptions() {

            String stringInput = Scan.readLine(" Employee Options - Type one of the options below:" + Scan.EOL +

                    "1. Register employee" + Scan.EOL +
                    "2. Remove an employee" + Scan.EOL +
                    "3. View all employees" + Scan.EOL +
                    "4. Get employee net salary" + Scan.EOL +
                    "5. Get employee salary bonus" + Scan.EOL +
                    "6. Return to Main Menu" + Scan.EOL);

            int intInput1 = (Integer.parseInt(stringInput));
            switch (intInput1) {
                case 1:
                    controllerManager.registerEmployee();
                    break;
                case 2:
                    controllerManager.removeEmployee();
                    break;
                case 3:
                    controllerManager.employeeViewList();
                    break;
                case 4:
                    controllerManager.getNetSalary();
                    break;
                case 5:
                    controllerManager.getEmployeeBonus();
                    break;
                case 6:
                    viewMain.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
                    break;

                default:
                    System.out.println("Invalid input, please try again.");
            }
            public void retrieveHistoryOptions() {

                String stringInput2 = Scan.readLine(" Retrieve history options - Type one of the options below:" + Scan.EOL +
                        "1. View all rent transactions" + Scan.EOL +
                        "2. Retrieve item with highest rent profit" + Scan.EOL +
                        "3. Retrieve how often each item is rented" + Scan.EOL +
                        "4. Retrieve the best customer" + Scan.EOL +
                        "5. Return to Main Menu" + Scan.EOL);

                int intInput2 = (Integer.parseInt(stringInput));
                switch (intInput2) {
                    case 1:
                        // view all rent transactions
                        break;
                    case 2:
                        // retrieve item with highest rent profit
                        break;
                    case 3:
                        //retrieve hoe often each item is rented
                        break;
                    case 4:
                        //retrieve the best customer
                        break;
                    case 5:
                        viewMain.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
                        break;

                    default:
                        System.out.println("Invalid input, please try again.");
                }

            }
}
*/

/*//method to search by genre or year
    public void itemSearch(){
        int option = Scan.readInt("Press 1 to find game by genre or press 2 to find album by release year: ");
        if (option==1){
            Scan.print("1. Action" + Scan.EOL + "2. Survival"); //change to a arraylist of genres
            int choice = Scan.readInt("Enter number of genre ");
            String genre = null;
            if (choice == 1){
                genre = "action";
            } else if (choice == 2){
                genre = "survival";

            }

            for(int i=0; i<allItems.size(); i++){
                Item game = allItems.get(i);

                if(game instanceof Game && ((Game) game).getGenre().equals(genre)){
                    Scan.print(game.toString());
                }
            }
        }else{
            int year = Scan.readInt("Enter the release year (four digits): ");
            for(int i=0; i<allItems.size(); i++){
                Item album = allItems.get(i);

                if(album instanceof Album && ((Album) album).getReleaseYear()==year){
                    Scan.print(album.toString());
                }

            }

        }
        }


    public void rentAlbum(Customer customer) {
        Item item = searchFeature("album"); //get the album item from the searchFeature method
        item.setAvailable(false); // calling search feature method to find album and make the album unavailable for rent

        customer.setRentingNow(+1); //update the customers credit status
        customer.setTotalRents(+1); //update customers renting history

        Scan.print("You have now rented the album " + item.getTitle() + " with the ID " + item.getId() + Scan.EOL);
        //call method here to take the customer back to their login page
    }

    public void rentGame(Customer customer) {
        Item item = searchFeature("game"); //get the game item from the searchFeature method
        item.setAvailable(false); // calling search feature method to find album and make the album unavailable for rent

        customer.setRentingNow(+1); //update the customers credit status
        customer.setTotalRents(+1); //update customers renting history

        Scan.print("You have now rented the game " + item.getTitle() + " with the ID " + item.getId() + Scan.EOL);

        //call method here to take the customer back to their login page
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////

   //method to rent item (both game and album) - failed to make this one functional but we will keep working on it
    public void rentItem(Customer customer, String albumOrGame) {
        Item item = searchFeature(albumOrGame); //get the item from the searchFeature method
        item.setAvailable(false); // calling searchFeature method to find album/game and make the item unavailable for rent

        customer.setRentingNow(+1); //update the customers credit status
        customer.setTotalRents(+1); //update customers renting history

        Scan.print("You have now rented the " + albumOrGame + " " + item.getTitle() + " with the ID " + item.getId()
                + Scan.EOL + "Enjoy!" + Scan.EOL);

        //call method here to take the customer back to their login page
    }




        /*if (managerPassword.equals("admin1234")) {
                    System.out.println("Manager Screen - Type one of the options below:");
                    System.out.println("1. Add an employee");
                    System.out.println("2. Remove an employee");
                    System.out.println("3. View all employees");
                    System.out.println("4. Calculate employee net salary");
                    System.out.println("5. Calculate employee salary bonus");
                    System.out.println("6. Return to Main Menu");*/

                /*    int optionM = Scan.readInt("");
                    if (optionM == 1) {
                        controllerManager.registerEmployee();
                    } else if (optionM == 2) {
                        controllerManager.removeEmployee();
                    } else if (optionM == 3) {
                        controllerManager.employeeViewList();
                    } else if (optionM == 4) {
                        controllerManager.calcNetSalary();
                    } else if (optionM == 5) {
                        controllerManager.employeeBonus();
                    } else if (optionM == 6) {
                        viewMain.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
                    } else {
                        System.out.println("Invalid input, please try again");
                        managerMenu(/*controllerEmployee, controllerManager, controllerCustomer, viewMain);
                    }
                } else {
                    System.out.println("Invalid password");
                }
    }*/

//rentingLimit = customer.getMembership()
       /* Item item = searchFeature(albumOrGame); //get the item from the searchFeature method



        //number of items this customer is curremtly renting
        int currentlyRenting;

        if (customer.getItemsCurrentlyRenting()==null){
            currentlyRenting=0;
        } else {
            currentlyRenting = customer.getItemsCurrentlyRenting().size();
        }



        //Makes sure this customer is not renting more items simultaneously than his membership status allows
        boolean rentLimitReached;
        if (customer.getMembership()==null){
            if (currentlyRenting>1){
                rentLimitReached=true;
            } else {
                rentLimitReached=false;
            }
        } else{
            rentLimitReached = customer.getMembership().hitRentLimit( currentlyRenting );
        }

        if (!rentLimitReached) {
            Scan.print("We're afraid you have reached the maximum number of items you can rent simultaneously. " +
                    "If you would like to rent more items at any given time, please apply for a membership upgrade.");

        } else if (!(item==null)) {
            item.setAvailable(false); // calling searchFeature method to find album/game and make the item unavailable for rent
            item.setCurrentRenter(customer.getUserId());

            customer.getItemsCurrentlyRenting().add( item );
            customer.setTotalRents(+1); //update customers renting history

            Scan.print("You have now rented the " + albumOrGame + " " + item.getTitle() + " with the ID " + item.getItemId()
                    + Scan.EOL + "Enjoy!" + Scan.EOL);

        } else {
            Scan.print("The item ID you entered does not exist in our system");
        }*/

/*  public boolean matchingId(int searchID){

        boolean targetFound = false;

        for (int i = 0; i < allCustomers.size() && !targetFound; i++) {
            if (allCustomers.get(i).getUserId() == searchID){
                targetFound = true;
            }
        }if(!targetFound) {
            System.out.println("Id not found.");
            targetFound= false;
        }
        return targetFound;
    }*/