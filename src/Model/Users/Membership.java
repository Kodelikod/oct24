package Model.Users;

public interface Membership {

    double getDiscountedPrice(double totalRentFee);
    boolean hitRentLimit(int numOfItems);
    int getCreditsPerItem();
    boolean allowedToRent(Customer customer);



}
