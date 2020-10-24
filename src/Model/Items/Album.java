package Model.Items;

//Each album registered in DART has a registered ID, a title, an artist, the year it was released, user rating, rent per
// day , and rent status. All these attributes are provided when the album is added for the first time, except
// for rent status which is always available when the album is added.

import Utility.Scan;

public class Album extends Item {

    //attributes
    private String artist;


    //constructor method

    public Album(int id, String title, double dailyRentFee, int releaseYear, String artist) throws RuntimeException {
        super(id, title, dailyRentFee, releaseYear);
        this.artist = artist;
    }



    //getters and setters
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }



    @Override
    public String toString() {
        String availability;
        if (this.isAvailable()){
            availability = "Available";
        } else {
            availability = "Not available at the moment, currently rented by " + super.getCurrentRenter();
        }
        return "Album ID: " + super.getItemId() +
                Scan.EOL + "Title: " + super.getTitle() + Scan.EOL + "Artist: " + artist + Scan.EOL + "Release Year: "
                + super.getReleaseYear() + Scan.EOL + "Daily rent fee: " + super.getDailyRentFee() + Scan.EOL +
                "Availability: " + availability +
                Scan.EOL;
    }
}
