package View;
import Controller.*;
import Utility.Scan;
import Utility.Mock;

public class MainView {

    //attributes
    private ManagerView managerView = new ManagerView();
    private EmployeeView employeeView = new EmployeeView();
    private CustomerView customerView = new CustomerView();
    private ControllerManager controllerManager = new ControllerManager();
    private ControllerEmployee controllerEmployee = new ControllerEmployee();
    private ControllerCustomer controllerCustomer = new ControllerCustomer();
    private boolean running = true;
    private Mock mock = new Mock();

    //getters & setters
    public ManagerView getManagerView() { return managerView; }
    public void setManagerView(ManagerView managerView) { this.managerView = managerView; }
    public EmployeeView getEmployeeView() { return employeeView; }
    public void setEmployeeView(EmployeeView employeeView) { this.employeeView = employeeView; }
    public CustomerView getCustomerView() { return customerView; }
    public void setCustomerView(CustomerView customerView) { this.customerView = customerView; }
    public ControllerManager getControllerManager() { return controllerManager; }
    public void setControllerManager(ControllerManager controllerManager) { this.controllerManager = controllerManager; }
    public ControllerEmployee getControllerEmployee() { return controllerEmployee; }
    public void setControllerEmployee(ControllerEmployee controllerEmployee) { this.controllerEmployee = controllerEmployee; }
    public ControllerCustomer getControllerCustomer() { return controllerCustomer; }
    public void setControllerCustomer(ControllerCustomer controllerCustomer) { this.controllerCustomer = controllerCustomer; }
    public boolean isRunning() { return running; }
    public void setRunning(boolean running) { this.running = running; }

    //methods
    public void welcome(/*ControllerEmployee controllerEmployee, ControllerManager controllerManager, ControllerCustomer controllerCustomer*/) {
        Scan.print("                                                                       \n" +
                "8 888888888o.            .8.          8 888888888o. 8888888 8888888888 \n" +
                "8 8888    `^888.        .888.         8 8888    `88.      8 8888       \n" +
                "8 8888        `88.     :88888.        8 8888     `88      8 8888       \n" +
                "8 8888         `88    . `88888.       8 8888     ,88      8 8888       \n" +
                "8 8888          88   .8. `88888.      8 8888.   ,88'      8 8888       \n" +
                "8 8888          88  .8`8. `88888.     8 888888888P'       8 8888       \n" +
                "8 8888         ,88 .8' `8. `88888.    8 8888`8b           8 8888       \n" +
                "8 8888        ,88'.8'   `8. `88888.   8 8888 `8b.         8 8888       \n" +
                "8 8888    ,o88P' .888888888. `88888.  8 8888   `8b.       8 8888       \n" +
                "8 888888888P'   .8'       `8. `88888. 8 8888     `88.     8 8888       ");
        Scan.print(Scan.EOL + "Main Menu:");
        Scan.print("Welcome to DART, your good old game rental system. The competition has no steam to keep up!"
                + Scan.EOL);
        mock.mock(controllerEmployee, controllerManager, controllerCustomer);
        mainMenu(controllerEmployee, controllerManager, controllerCustomer);
    }

    public void mainMenu(ControllerEmployee controllerEmployee, ControllerManager controllerManager, ControllerCustomer controllerCustomer) {
        while(running){
            String stringInput = Scan.readLine("Main Menu - Please specify your role by entering one of the options given:" + Scan.EOL +
                    "1. Enter M for Manager" + Scan.EOL +
                    "2. Enter E for Employee" + Scan.EOL +
                    "3. Enter C for Customer" + Scan.EOL +
                    "4. Enter X to exit the system" + Scan.EOL);

            switch (stringInput.toUpperCase().trim()) {
                case "M":
                    managerView.managerMenu(controllerEmployee, controllerManager, controllerCustomer);
                    break;
                case "E":
                    employeeView.employeeMenu(controllerEmployee, controllerManager, controllerCustomer);
                    break;
                case "C":
                    customerView.customerMenu(controllerEmployee, controllerManager, controllerCustomer);
                    break;
                case "X":
                    Scan.closeScanner();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }
}