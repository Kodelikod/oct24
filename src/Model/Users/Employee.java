package Model.Users;

import Exceptions.EmptyNameException;
import Utility.Scan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {

    //attributes
    private int employeeId;
    private String name;
    private String password;
    private int birthYear;
    private String address;
    private double grossSalary;
    private boolean manager;
    final double TAX = 0.70;
    final int BONUS_PAC_1 = 4000;
    final int BONUS_PAC_2 = 6000;
    final int BONUS_PAC_3 = 7500;

    //constructor
    public Employee(int employeeId, String name, String password, int birthYear, String address, double grossSalary,
                    boolean manager) throws EmptyNameException {
        this.employeeId = employeeId;
        this.password = password;
        if(name.isEmpty()) {
            throw new EmptyNameException("Invalid data. Employee name cannot be empty.");
        }else{ this.name = name;}
        this.birthYear = birthYear;
        this.address = address;
        if(grossSalary < 0) {
            throw new EmptyNameException("Invalid data. Employee salary cannot be negative.");
        }else{ this.grossSalary = grossSalary;}
        this.manager = manager;
    }



    //setters and getters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public int getAge(int birthYear){ //https://youtu.be/6cp4P4XZ9hE
        Date currentDate = new Date(); // created new object currentDate from imported class Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");       //imported
        // SimpleDateFormat package for getting current year
        int currentYear = Integer.parseInt(dateFormat.format(currentDate));       //previous line gives current year in variable type string, used Integer.parseInt to turn string into int

        int age = currentYear - birthYear;

        return age;
    }


    public double getNetSalary(){
        double netSal = 0;

        if ( this.getGrossSalary()<100000 ){
            netSal = this.grossSalary + getBonus();
        } else if( this.getGrossSalary() >= 100000 ){
            netSal = this.grossSalary * TAX + getBonus();
        }

        return netSal;
    }


    public double getBonus(){
        double bonus = 0;

        if (getAge(birthYear) < 22) {
            bonus = BONUS_PAC_1;
           // Scan.print("The employee's bonus salary is " + BONUS_PAC_1 + " SEK.");
        } else if (getAge(birthYear) >= 22 && getAge(birthYear) <= 30) {
            bonus = BONUS_PAC_2;
           // Scan.print("The employee's bonus salary is " + BONUS_PAC_2 + " SEK.");
        } else {
            bonus = BONUS_PAC_3;
           // Scan.print("The employee's bonus salary is " + BONUS_PAC_3 + " SEK.");
        }

        return bonus;
    }



    @Override
    public String toString() {
        String empOrMan;
        if (manager=false){
            empOrMan = "Employee";
        } else {
            empOrMan = "Manager";
        }
        return empOrMan + " name: " + name +
                Scan.EOL + empOrMan + " ID: " + employeeId + Scan.EOL + "Password: " + password + Scan.EOL + "Birth year: "
                + birthYear + " (age: " + getAge(birthYear) +
                ")" + Scan.EOL + "Address: " + address +
                Scan.EOL + "Monthly gross salary: " + grossSalary + " SEK" + Scan.EOL;
    }
}


