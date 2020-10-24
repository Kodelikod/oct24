package Model.Users;

//Silver: Silver members get a 10% discount on rent for all products. Also, they can rent a maximum of 3 things
// (games and song albums combined e.g., 1 game and 2 song albums) at a time. When they return an item they rented,
// they get one credit (per item).

public class SilverMember implements Membership {

    //attributes
    public static final double DISCOUNT = 0.90; //10% discount of a price is price*0.9
    public static final int RENTING_LIMIT = 3; //the limit of items that a silver customer is allowed to rent is 3
    public static final int CREDITS_PER_ITEM = 1; //amount of credits a silver customer gets per item they return is 1


    @Override
    public double getDiscountedPrice(double totalRentFee) {
        double discPrice = totalRentFee * DISCOUNT;
        return discPrice;
    }

    @Override
    public boolean hitRentLimit(int numOfItems){
        if (numOfItems >= RENTING_LIMIT){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getCreditsPerItem() {
        int credits = CREDITS_PER_ITEM;
        return credits;
    }

    @Override
    public boolean allowedToRent(Customer customer) {
        boolean allowedToRent;

        if ( customer.getItemsCurrentlyRenting().size() < RENTING_LIMIT ) {
            allowedToRent = true;
        } else {
            allowedToRent = false;
        }

        return allowedToRent;
    }

}
