package Model.Items;

import Utility.Scan;

public class Game extends Item {

    private String genre;

    public Game(int id, String title, double dailyRentFee, int releaseYear, String genre) throws RuntimeException {
        super(id, title, dailyRentFee, releaseYear);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        String availability;
        if (this.isAvailable()){
            availability = "Available";
        } else {
            availability = "Not available at the moment, currently rented by " + super.getCurrentRenter();
        }
        return "Game ID: " + super.getItemId() +
                Scan.EOL + "Title: " + super.getTitle() + Scan.EOL + "Genre: " + genre + Scan.EOL +
                "Daily rent fee: " + super.getDailyRentFee() + Scan.EOL +
                "Availability: " + availability +
                Scan.EOL;
    }


}
