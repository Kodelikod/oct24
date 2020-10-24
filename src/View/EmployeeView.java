package View;

import Controller.ControllerCustomer;
import Controller.ControllerEmployee;
import Controller.ControllerManager;
import Model.Users.Employee;
import Utility.Scan;

public class EmployeeView {

    //methods
    public void employeeMenu(ControllerEmployee controllerEmployee, ControllerManager controllerManager,
                             ControllerCustomer controllerCustomer) {

        boolean loggedIn=false;

        int id = Scan.readInt("Employee Screen - To login, enter your ID:");
        Employee theEmployee = controllerManager.getEmployee(id);

        if (!(theEmployee==null)){
            String password = Scan.readLine("Please insert your password: ");
            if(theEmployee.getPassword().equals(password)) {
                loggedIn = true;
                while (loggedIn) {

                    String stringInput = Scan.readLine("Salutations " + theEmployee.getName() + "!" + Scan.EOL
                            + " - Type one of the options below:" + Scan.EOL +
                            "1. Register a game" + Scan.EOL +
                            "2. Register a song album" + Scan.EOL +
                            "3. Remove item" + Scan.EOL +
                            "4. Register customer" + Scan.EOL + "" +
                            "5. Remove customer" + Scan.EOL +
                            "6. View all customers" + Scan.EOL +
                            "7. Assign and modify membership" + Scan.EOL +
                            "8. Show total rent profit" + Scan.EOL +
                            "9. View all items" + Scan.EOL +
                            "10. Return to Main menu" + Scan.EOL);

                    int intInput = (Integer.parseInt(stringInput));

                    switch (intInput) {
                        case 1:
                            controllerEmployee.registerGame();
                            break;
                        case 2:
                            controllerEmployee.registerAlbum();
                            break;
                        case 3:
                            controllerEmployee.removeItem();
                            break;
                        case 4:
                            controllerEmployee.registerCustomer();
                            break;
                        case 5:
                            controllerEmployee.removeCustomer();
                            break;
                        case 6:
                            controllerEmployee.viewAllCustomers();
                            break;
                        case 7: controllerEmployee.viewUpgradeRequests(); //this method doesnt actually upgrade anyone just view requets
                            break;
                        case 8:
                            controllerEmployee.printTotalRentProfit();
                            break;
                        case 9:
                            controllerCustomer.viewItemList(controllerEmployee);
                            break;
                        case 10:
                            loggedIn=false;
                           // mainView.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
                            break;
                        default:
                            Scan.print("Invalid input, please try again.");
                    }
                }
            } else {
                Scan.print("Invalid Password");
                //mainView.mainMenu(controllerEmployee,controllerManager,controllerCustomer);
            }
        } else {
            Scan.print("The employee ID you entered does not exist in our system." + Scan.EOL);
           // mainView.mainMenu(controllerEmployee,controllerManager,controllerCustomer);
        }
    }
}

