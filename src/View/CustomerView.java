package View;
import Controller.*;
import Model.Users.Customer;
import Utility.Scan;

import java.util.ArrayList;

public class CustomerView {

    public void customerMenu(ControllerEmployee controllerEmployee, ControllerManager controllerManager,
                             ControllerCustomer controllerCustomer) {


        boolean loggedIn = false;
        Customer theCustomer = null;
        ArrayList<Customer> allCustomers = controllerEmployee.getAllCustomers(); // retrieve allCustomers list and assign it to "allCustomers"

        // String id = Scan.readLine("Enter your four digit ID: ");
        int id = Scan.readInt("Enter your four digit ID: ");

        theCustomer = controllerEmployee.getCustomerById(id);

        if (!(theCustomer == null)) {

            Scan.print("Welcome " + theCustomer.getName() + "!");

            String password = Scan.readLine("To log in, enter your password: "); //ask for password and store it in "password"
            boolean passwordMatch = (password.equals(theCustomer.getPassword())); //does "theCustomer"s password match with input password?

            if (passwordMatch == true) {  //passwordMatch is true

                loggedIn = true;

                while (loggedIn) {
                    int inputCustomerMenu = Scan.readInt( Scan.EOL +
                            "Type one of the options below:" + Scan.EOL +
                            "1. Rent an item" + Scan.EOL +
                            "2. Return an item" + Scan.EOL +
                            "3. View list of all items" + Scan.EOL +
                            "4. Send a message" + Scan.EOL +
                            "5. Read a message" + Scan.EOL +
                            "6. Remove message" + Scan.EOL +
                            "7. Request membership upgrade" + Scan.EOL +
                            "8. Search games by genre " + Scan.EOL +
                            "9. Search song albums by year" + Scan.EOL +
                            "10. View Games based on ratings" + Scan.EOL +
                            "11. View Albums based on ratings" + Scan.EOL +
                            "12. Log out and return to main menu" + Scan.EOL);

                    switch (inputCustomerMenu) {
                        case 1:
                            int chooseItem = Scan.readInt("Press 1 for Game or press 2 for Album"); //
                            if (chooseItem == 1) {
                                controllerCustomer.rentItem(theCustomer, "game", controllerEmployee);
                            } else if (chooseItem == 2) {
                                controllerCustomer.rentItem(theCustomer, "album", controllerEmployee);
                            } else {
                                Scan.print("Invalid input, please try again.");
                            }
                            break;

                        case 2:
                            controllerCustomer.returnItem(theCustomer, controllerEmployee, controllerManager);
                            break;
                        case 3:
                            controllerCustomer.viewItemList(controllerEmployee);
                            break;
                        case 4:
                            controllerCustomer.sendMessage(theCustomer, controllerEmployee);
                            break;
                        case 5:
                            controllerCustomer.viewUnMessage(theCustomer);
                            break;
                        case 6:
                            controllerCustomer.removeMessage(theCustomer);
                            break;
                        case 7:
                            controllerCustomer.applyForUpgrade(theCustomer, controllerEmployee);
                            break;
                        case 8:
                            controllerCustomer.showOptionsGame(controllerEmployee); // itemSearchByGenreYear?
                            break;
                        case 9:
                            controllerCustomer.showOptionsAlbum(controllerEmployee); // itemSearchByGenreYear?
                            break;
                        case 10:
                            controllerEmployee.gamesByRatings();
                            break;
                        case 11:
                            controllerEmployee.albumsByRatings();
                            break;
                        case 12:
                            loggedIn = false;
                            //mainView.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
                            break;
                        default:
                            Scan.print("Invalid input, please try again.");
                            break;
                    } //end of switch

                }  //end of while

            } else { //if wrong password is entered
                Scan.print("You entered the wrong password. :/ Back to main menu!");
              //  mainView.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
            }

        } else { //if the ID doesn't match with any customer
            Scan.print("The id you entered does not match with any customer account in our system.");
         //   mainView.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
        }
    }
}
