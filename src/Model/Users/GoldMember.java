package Model.Users;

//Gold: Gold members get a 15% discount on all items. Also, they can rent a maximum of 5 things at a time. Lastly,
// when they return a rented item, they receive two credits (per item).

public class GoldMember implements Membership {

    //attributes
    public static final double DISCOUNT = 0.85; //15% discount of a price is price*0.85
    public static final int RENTING_LIMIT = 5; //the limit of items that a gold customer is allowed to rent is 5
    public static final int CREDITS_PER_ITEM = 2; //amount of credits a gold customer gets per item they return is 2


    //constructor
    public GoldMember() {

    }
    @Override
    public double getDiscountedPrice(double rentCost) {
        double discPrice = rentCost * DISCOUNT;
        return discPrice;
    }

    @Override
    public boolean allowedToRent(Customer customer){
        boolean allowedToRent;

        if ( customer.getItemsCurrentlyRenting().size() < RENTING_LIMIT ) {
            allowedToRent = true;
        } else {
            allowedToRent = false;
        }

        return allowedToRent;
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
}

