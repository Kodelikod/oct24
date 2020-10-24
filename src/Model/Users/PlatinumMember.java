package Model.Users;

//Premium: Premium members get a 25% discount on all items. Also, they can rent a maximum of 7 items at a time. Lastly,
// when they return a rented game, they receive three credits (per item).


public class PlatinumMember implements Membership {

    //attributes
    public static final double DISCOUNT = 0.75; //25% discount of a price is price*0.75
    public static final int RENTING_LIMIT = 7; //the limit of items that a platinum customer is allowed to rent is 7
    public static final int CREDITS_PER_ITEM = 3; //amount of credits a platinum customer gets per item they return is 3


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
