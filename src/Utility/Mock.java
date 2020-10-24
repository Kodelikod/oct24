package Utility;

import Controller.ControllerCustomer;
import Controller.ControllerEmployee;
import Controller.ControllerManager;
import Model.Items.Album;
import Model.Items.Game;
import Model.Items.Item;
import Model.Message;
import Model.Transaction;
import Model.Users.Customer;
import Model.Users.Employee;

import java.util.ArrayList;

public class Mock {
    //ControllerEmployee controllerEmployee = new ControllerEmployee();
    //ControllerManager controllerManager = new ControllerManager();
    //ControllerCustomer controllerCustomer = new ControllerCustomer();

    public void mock(ControllerEmployee controllerEmployee, ControllerManager controllerManager, ControllerCustomer controllerCustomer){

        ArrayList<Customer> allCustomers  = controllerEmployee.getAllCustomers();
        Customer customer1 = new Customer(2000, "1234", "Beyoncé");
        customer1.setUserId(2000);
        customer1.setPassword("1234");
        customer1.setName("Beyoncé");
        //customer1.setItemsCurrentlyRenting(null);
        customer1.setTotalRents(0);
        customer1.setCredits(0);

        Customer customer2 = new Customer(2001, "1234", "Creed");
        customer2.setUserId(2001);
        customer2.setPassword("1234");
        customer2.setName("Creed");
       // customer2.setItemsCurrentlyRenting(null);
        customer2.setTotalRents(6);
        customer2.setCredits(3);

        allCustomers.add(customer2);
        allCustomers.add(customer1);

        //allItems = controllerEmployee.getAllItems();

        Item album1 = new Album(1004,"Ctrl",0,0,"SZA");
        album1.setItemId(1004);
        album1.setTitle("Ctrl");
        album1.setDailyRentFee(40);
        album1.setAvailable(true);
        ((Album) album1).setArtist("SZA");
        ((Album) album1).setReleaseYear(2017);
        controllerEmployee.getAllItems().add(album1);

        Item game2 = new Game(0,"The Last of Us",0,0,"survival");
        game2.setItemId(1001);
        game2.setTitle("The Last Of Us");
        ((Game) game2).setGenre("survival");
        game2.setDailyRentFee(40);
        game2.setAvailable(true);
        controllerEmployee.getAllItems().add(game2);

        Item album2 = new Album(0,"Lemonade",0,0,"");
        album2.setItemId(1005);
        album2.setTitle("Lemonade");
        album2.setDailyRentFee(30);
        album2.setTimesRented(0);
        ((Album) album2).setArtist("Beyoncé");
        ((Album) album2).setReleaseYear(2016);

        controllerEmployee.getAllItems().add(album2);

        Item album3 = new Album(1006, "I like it when you sleep, for you are so beautiful yet so unaware of it", 35, 2016,"The 1975");
        album3.setItemId(1006);
        album3.setTitle("I like it when you sleep, for you are so beautiful yet so unaware of it");
        album3.setDailyRentFee(35);
        album3.setAvailable(true);
        album3.setCurrentRenter(0000);
        ((Album) album3).setArtist("The 1975");
        ((Album) album3).setReleaseYear(2016);

        controllerEmployee.getAllItems().add(album3);

        Item album4 = new Album(1007, "Oxnard", 35, 2018, "Anderson Paak.");
        album4.setItemId(1007);
        album4.setTitle("Oxnard");
        album4.setDailyRentFee(35);
        album4.setAvailable(true);
        album4.setCurrentRenter(0000);
        ((Album) album4).setArtist("Anderson Paak.");
        ((Album) album4).setReleaseYear(2018);

        controllerEmployee.getAllItems().add(album4);

        Item album5 = new Album(0, "SAWAYAMA", 0, 2020,"Rina Sawayama");
        album5.setItemId(1009);
        album5.setTitle("SAWAYAMA");
        album5.setDailyRentFee(35);
        album5.setAvailable(true);
        album5.setCurrentRenter(0000);
        ((Album) album5).setArtist("Rina Sawayama");
        ((Album) album5).setReleaseYear(2020);
        
        controllerEmployee.getAllItems().add(album5);

        Item game3 = new Game(0,"Horizon: Zero Dawn",0,2017,"action");
        game3.setItemId(1008);
        game3.setTitle("Horizon: Zero Dawn");
        game3.setDailyRentFee(35);
        game3.setAvailable(true);
        game3.setCurrentRenter(0000);
        ((Game) game3).setGenre("action");

        controllerEmployee.getAllItems().add(game3);





        ArrayList<Employee> allEmployees = controllerManager.getAllEmployees();
        Employee employee1 = new Employee(1000, "Michael", "1234", 1995, "Castle Black", 50000, true);
        employee1.setEmployeeId(1000);
        employee1.setName("Michael");
        employee1.setPassword("1234");
        employee1.setBirthYear(1964);
        employee1.setAddress("Scranton");
        employee1.setGrossSalary(40000);
        employee1.setManager(true);

        allEmployees.add(employee1);

        Employee employee2 = new Employee(1001, "Tyrion", "234567", 1994, "Kings Landing", 30000, false);
        employee2.setEmployeeId(1001);
        employee2.setName("Dwight");
        employee2.setPassword("1234");
        employee2.setBirthYear(1967);
        employee2.setAddress("Beet farm");
        employee2.setGrossSalary(36000);
        employee2.setManager(true);

        allEmployees.add(employee2);

        Employee employee3 = new Employee(1002, "Jim", "", 0, "", 34000, false);
        employee3.setEmployeeId(1002);
        employee3.setName("Jim");
        employee2.setPassword("1234");
        employee2.setBirthYear(1979);
        employee3.setAddress("Scranton");
        employee3.setGrossSalary(34000);
        employee3.setManager(false);
        allEmployees.add(employee3);


        Employee employee4 = new Employee(1002, "Pam", "", 0, "", 34000, false);
        employee4.setEmployeeId(1003);
        employee4.setName("Pam");
        employee4.setPassword("1234");
        employee4.setBirthYear(1979);
        employee4.setAddress("Scranton");
        employee4.setGrossSalary(34000);
        employee4.setManager(false);
        allEmployees.add(employee4);




        Message message1 = new Message(0, 0,false,"");
        message1.setMessage("dan nicky dem bobbies");
        message1.setRecipientID(2000);
        message1.setSenderID(2001);
        message1.setRead(false);
        customer1.getInbox().add(message1);

        Message message2 = new Message(0, 0,false,"");
        message2.setMessage("just found out the world doesnt revolve around me. shocked and upset");
        message2.setRecipientID(2001);
        message2.setSenderID(2000);
        message2.setRead(false);
        customer2.getInbox().add(message2);

        Transaction transaction1 = new Transaction(album1, customer1, 134);
        Transaction transaction2 = new Transaction(game2, customer2, 200);

        ArrayList<Transaction> allTransactions = controllerManager.getAllTransactions();

        allTransactions.add(transaction1);
        allTransactions.add(transaction2);




        System.out.println("IS list customer list empty: " + allCustomers.isEmpty());
/*

        for (int i=0; i<allItems.size(); i++){
            System.out.println(allItems.get(i).toString());
        }
*/



        for (int i=0; i<allCustomers.size(); i++){
            System.out.println(allCustomers.get(i).toString());
        }






    }


}
