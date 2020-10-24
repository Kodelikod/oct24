package View;
import Controller.*;
import Model.Users.Employee;
import Utility.Scan;

import java.io.IOException;

public class ManagerView {
    //methods
    public void managerMenu(ControllerEmployee controllerEmployee, ControllerManager controllerManager,
                            ControllerCustomer controllerCustomer){
        try{
            boolean loggedIn = false;

            int id = Scan.readInt("Manager screen - Please enter your ID: ");

            Employee theManager = controllerManager.getEmployee(id);

            if (!(theManager==null) && theManager.isManager()){

                String password = Scan.readLine("Please enter your password:");
                if (password.equals(theManager.getPassword())) {
                    loggedIn = true;
                    while ( loggedIn ) {

                        String stringInput = Scan.readLine("Manager Screen - Type one of the options below:" + Scan.EOL +
                                "1. Add an employee" + Scan.EOL +
                                "2. Remove an employee" + Scan.EOL +
                                "3. View all employees" + Scan.EOL +
                                "4. Get employee net salary" + Scan.EOL +
                                "5. Get employee salary bonus" + Scan.EOL +
                                "6. View all rent transactions" + Scan.EOL +
                                "7. Retrieve item with highest rent profit" + Scan.EOL +
                                "8. Retrieve how often each item is rented" + Scan.EOL +
                                "9. Retrieve the best customer" + Scan.EOL +
                                "10. Import objects from file" + Scan.EOL +
                                "11. Export transactions to file" + Scan.EOL +
                                "12. Return to Main Menu" + Scan.EOL);

                        int intInput = (Integer.parseInt(stringInput));

                        switch (intInput) {
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
                                controllerManager.getBonus();
                            case 6:
                                controllerManager.viewAllTransactions();
                                break;
                            case 7:
                                controllerManager.getMostProfitableItem(controllerEmployee);
                                break;
                            case 8:
                                controllerManager.getItemRentFrequency(controllerEmployee);
                                break;
                            case 9:
                                controllerManager.getMostProfitableCustomer(controllerEmployee);
                                break;
                            case 10:
                                controllerManager.importFile(controllerEmployee);
                                break;
                            case 11:
                                controllerManager.exportTransactionsHistory();
                                break;
                            case 12:
                                loggedIn=false;
                                break;

                            default:
                                Scan.print("Invalid input, please try again.");
                        }
                    }
                } else {
                    Scan.print("Invalid Password");
                    //mainView.mainMenu(controllerEmployee, controllerManager, controllerCustomer);
                }
            } else {
                Scan.print("The manager ID you entered is not in our system");
            }
        } catch (Exception exception){
            Scan.print(exception.getMessage());
        }
    }
}


