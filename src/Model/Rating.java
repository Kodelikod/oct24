package Model;

public class Rating {

    //attributes
    private int number; // STRING?
    private String review;

    //constructor
    public Rating(int number, String review) {
        this.number = number;
        this.review = review;
    }

    //getters &setters
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
}