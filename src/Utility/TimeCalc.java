package Utility;

//FRANCISCO: For the date, try using the LocalDate library in Java. The Date library is difficult to use and
// was later replaced by LocalDate in newer versions of Java since LocalDate has an implementation that is
// easier to use.

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*public class TimeCalc {

    public int daysBetween(){

        LocalDate today = LocalDate.now(); //local date of today

        int year = Scan.readInt("Please enter the first day of renting, first enter the year (four digits): ");
        int month = Scan.readInt("Enter the month (two digits): ");
        int day = Scan.readInt("Enter the day of the month (two digits");

        LocalDate from = LocalDate.of(year, month, day);
        long noOfDaysBetween = ChronoUnit.DAYS.between(from, today); //get amount of days passed from "from" to "today"
        int days = (int)noOfDaysBetween; //converts the long value to an int

        return days;
    }

}*/
