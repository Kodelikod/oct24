package Controller;

import Model.Items.Album;
import Model.Items.Game;
import Model.Items.Item;
import Model.Transaction;
import Model.Users.Customer;
import Model.Users.Employee;
import Exceptions.EmptyNameException;
import Utility.Scan;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ControllerManager {
    private static final double TAX = 0.70;
    private static final int AMOUNT = 100000;
    //private Employee newEmployee;
    private ArrayList<Employee> allEmployees;
    private ArrayList<Transaction> allTransactions;
    private ControllerEmployee controllerEmployee;

    //constructor
    public ControllerManager() {
        this.allEmployees = new ArrayList<>();
        this.controllerEmployee = new ControllerEmployee();
        this.allTransactions = new ArrayList<>();
    }

    //getters
    public ArrayList<Employee> getAllEmployees() {
        return allEmployees;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public ControllerEmployee getControllerEmployee() {
        return controllerEmployee;
    }



    public void getItemRentFrequency(ControllerEmployee controllerEmployee){
        ArrayList<Item> allItems = controllerEmployee.getAllItems();

        for (Item item : allItems) {
            if (item.getTimesRented(allTransactions) > 0) {
                int itemId = item.getItemId();
                String itemTitle = item.getTitle();
                int timesRented = item.getTimesRented(allTransactions);

                Scan.print( "ID: " + itemId + System.lineSeparator() +  "Title: " + itemTitle +
                        System.lineSeparator() + "Times rented: " + timesRented );
            }
        }
    }


    public void getMostProfitableCustomer(ControllerEmployee controllerEmployee){
        ArrayList<Customer> allCustomers = controllerEmployee.getAllCustomers();
        Customer mostProfitableCustomer = null;
        double profit = 0;
        for (Customer customer : allCustomers){

            double currentCustomerProfit = customer.getCustomerProfit(allTransactions);

            if (currentCustomerProfit>profit){
                profit = currentCustomerProfit;
                mostProfitableCustomer = customer;

            }
        }

        if ( mostProfitableCustomer!=null ) {
            Scan.print("Total rent profit of most profitable customer: " + mostProfitableCustomer.getCustomerProfit(allTransactions) + " SEK"
                    + System.lineSeparator() + mostProfitableCustomer.toString() );
        } else {
            Scan.print("There are no profits from any customer yet");
        }
    }

    public void getMostProfitableItem(ControllerEmployee controllerEmployee){
        ArrayList<Item> allItems = controllerEmployee.getAllItems();
        Item mostProfitItem = null;
        double profit = 0;
        for (Item item : allItems){

            double currentItemProfit = item.getItemProfit(allTransactions);

            if (currentItemProfit>profit){
                profit = currentItemProfit;
                mostProfitItem = item;

            }
        }

        if ( mostProfitItem!=null ) {
            Scan.print( "Total rent profit of most profitable item: " + mostProfitItem.getItemProfit(allTransactions) + " SEK"
                    + System.lineSeparator() + mostProfitItem.toString() );
        } else {
            Scan.print( "There are no profits yet" );
        }
    }

    public void employeeViewList() { // Change to iterator
        for (int i = 0; i < allEmployees.size(); i++) {
            Scan.print(allEmployees.get(i).toString());
        }
    }

    public Employee getEmployee(int id){
        Employee employee = null;

        Iterator<Employee> iterator = allEmployees.iterator();

        while (iterator.hasNext() && employee==null){
            Employee currentEmployee = iterator.next();
            if (currentEmployee.getEmployeeId() == id){
                employee = currentEmployee;
            }
        }
        return employee;

    }

    //method to remove employee
    public void removeEmployee () {

        int idRemoval = Scan.readInt("Removing employee account " + Scan.EOL + "Enter employee ID: ");

        Employee employee = getEmployee(idRemoval);

        if (!(employee==null)){
            allEmployees.remove(employee);
        } else {
            Scan.print("The ID you entered does not match with any employee in our system");

        }
    }

    public int employeeIdGenerator(){

        int iD = 1000;

        if (allEmployees.isEmpty()){
            iD=1000; //if there are no items registered in the system, the first item's id will be 1000
        } else {
            iD = allEmployees.get(allEmployees.size() - 1).getEmployeeId() + 1;  // else it will be the last registered item's id + 1
        }

        while (!(getEmployee(iD)==null)){
            iD++;
        }

        return iD;

    }

    //register new employee
    public void registerEmployee() {
        try {

            int id = employeeIdGenerator();

            Scan.print("Creating an Employee \nPlease type the Employee's: ");
            Scan.print("ID: " + id);

            String name = Scan.readLine("Name:");

            int birthYear = Scan.readInt("Birth year: ");

            String address = Scan.readLine("Address: ");

            double salary = Scan.readDouble("Monthly gross salary:");

            String password = Scan.readLine("Lastly, enter the password the employee wishes to have: ");

            Employee newEmployee;

            int option = Scan.readInt("Is this employee in a manager position? If yes press 1, if no press 2");
            if (option == 1) {
                newEmployee = new Employee(id, name, password, birthYear, address, salary, true);
            } else {
                newEmployee = new Employee(id, name, password, birthYear, address, salary, false);
            }

            allEmployees.add(newEmployee);

            Scan.print("You have successfully added a new employee: \n"  + newEmployee.toString());

        } catch (EmptyNameException emptyNameException) {
            System.out.println(emptyNameException.getMessage());
        }
    }

    public void getNetSalary(){ //alternative to calcNetSalary
        int id = Scan.readInt("Please enter the id of the employee: ");

        Employee employee = getEmployee(id);
        double netSalary = employee.getNetSalary();

        Scan.print(employee.getName() + "'s net salary is " + netSalary  + " SEK. ");

    }

    public void getBonus(){

        int id = Scan.readInt("Please enter the id of the employee: ");

        Employee employee = getEmployee(id);

        double bonus = employee.getBonus();

        Scan.print(employee.getName() + "'s bonus is " + bonus  + " SEK.");


    }

    public void viewAllTransactions(){
        for ( Transaction transaction : allTransactions){
            Scan.print(transaction.toString());
        }
    }

    public void importFile(ControllerEmployee controllerEmployee) throws FileNotFoundException {


        /*try*/  Scanner scanner = new Scanner(new File("/Users/linaboman/IdeaProjects/DARTv6/23oct/out/production/23oct/text.txt"));//{

        while (scanner.hasNextLine()){

            String line = scanner.nextLine();
            String[] objectInfo = line.split(";");

            String type = objectInfo[0];

            if (type.equals("Customer")) {
                int id = controllerEmployee.customerIdGenerator();
                Customer customer = new Customer(id, objectInfo[2], objectInfo[1]);
                controllerEmployee.getAllCustomers().add(customer);

            } else if(type.equals("Employee")) {
                int id = employeeIdGenerator();
                int birthYear = Integer.parseInt(objectInfo[3]);
                double salary = Double.parseDouble(objectInfo[4]);
                Employee employee = new Employee(id, objectInfo[1], objectInfo[2], birthYear, objectInfo[4], salary, false);
                allEmployees.add(employee);

            }else if (type.equals("Manager")){
                int id = employeeIdGenerator();
                int birthYear = Integer.parseInt(objectInfo[3]);
                double salary = Double.parseDouble(objectInfo[5]);
                Employee manager = new Employee(id, objectInfo[1], objectInfo[2], birthYear, objectInfo[4], salary, true);
                allEmployees.add(manager);

            } else if (type.equals("Game")){
                int id = controllerEmployee.itemIdGenerator();
                double dailyRentFee = Double.parseDouble(objectInfo[2]);
                int releaseYear = Integer.parseInt(objectInfo[3]);
                Game game = new Game(id, objectInfo[1], dailyRentFee, releaseYear, objectInfo[4]);
                controllerEmployee.getAllItems().add(game);

            } else if (type.equals("Album")){
                int id = controllerEmployee.itemIdGenerator();
                double dailyRentFee = Double.parseDouble(objectInfo[2]);
                int releaseYear = Integer.parseInt(objectInfo[3]);
                Album album = new Album(id, objectInfo[1], dailyRentFee, releaseYear, objectInfo[4]);
                controllerEmployee.getAllItems().add(album);

            }
            scanner.close();
        }

        /*} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void exportTransactionsHistory() throws IOException {


        File output = new File("GODIBEG.csv");

        String result = "";
        for(Transaction transaction : allTransactions) {
            result += transaction.toString() + "\n";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        writer.write(result);
        writer.close();

    }


     /*   FileWriter fileWriter = new FileWriter("Employee.csv");

        //Adding the header
        fileWriter.append("Transactions");
        //New Line after the header
        fileWriter.append(System.lineSeparator());

        //Iterate the empList
        Iterator iterator = allTransactions.iterator();
        while(iterator.hasNext())
        {
            Transaction transaction = (Transaction)iterator.next();
            fileWriter.append(String.valueOf(transaction.getItem().getItemId()));
            fileWriter.append(";");
            fileWriter.append(String.valueOf(transaction.getCustomer().getUserId()));
            fileWriter.append(";");
            fileWriter.append(String.valueOf(transaction.getTotalCost()));

        }
        System.out.println("Write to CSV file Succeeded!!!");*/

       /* ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("TrAnSaCtIoNs.csv"));

        for (Transaction transaction : allTransactions) {
            os.writeObject(transaction);
        }
        os.close();*/


    public void exportFile()throws IOException {
        BufferedWriter bfWriter = new BufferedWriter(new FileWriter(new File("transactions")));
        for (Transaction transaction : allTransactions) {
            bfWriter.write(transaction.toString() + "\n");
        }
        bfWriter.close();
    }

    public void saveObjects() throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("TransactionHistory.txt")));
        output.writeObject( allTransactions );
        output.close();
    }

    /*public void importTransactions(ArrayList<Customer> allCustomers){
        File transactionFile = new File("trans");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(transactionFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] transactionInfo = line.split(";");

                if (transactionInfo[0].equals("Transaction")){

                    Transaction transaction = new Transaction(get);
                    allCustomers.add(customer);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}