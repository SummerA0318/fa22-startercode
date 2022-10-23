/*
  Name: Yubing Lin
  PID:  A16994291
 */

/**
 * A class for value passengers to set up and change title
 * @author Yubing Lin
 * @since  Oct 14, 2022
 */
public class ValuePassenger extends Passenger{

    // instance variable
    private String customTitle;

    /**
     * Constructor for Value Passenger that initializes a passenger
     * while sets its ID to be 1
     * @param username the name of passenger
     * @param bio the bio of passenger
     * @throws IllegalArgumentException if username or bio is null
     */
    public ValuePassenger(String username, String bio){
        super(username, bio);
        this.customTitle = "Value Passenger";
        this.passengerID = 1;
    }

    /**
     * Return the name of passenger
     * @return the passenger username
     */
    public String displayName() {
        return "<" + this.customTitle + "> " + this.username;
    }

    /**
     * Set the customTitle by the input title
     * @param newTitle the new title to replace the current one
     */
    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }
}
