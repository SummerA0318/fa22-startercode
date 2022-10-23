/*
  Name: Yubing Lin
  PID:  A16994291
 */

/**
 * A class for standard passengers to set up
 * @author Yubing Lin
 * @since  Oct 14, 2022
 */
public class StandardPassenger extends Passenger{

    /**
     * Constructor for Standard Passenger that initializes a passenger
     * while sets its ID to be 0
     * @param username the name of passenger
     * @param bio the bio of passenger
     * @throws IllegalArgumentException if username or bio is null
     */
    public StandardPassenger(String username, String bio){
        super(username, bio);
        this.passengerID = 0;
    }

    /**
     * Return the name of passenger
     * @return the passenger username
     */
    public String displayName() {
        return this.username;
    }
}
