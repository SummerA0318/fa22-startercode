/*
  Name: Yubing Lin
  PID:  A16994291
 */

/**
 * An abstract class for two kinds of passengers' methods
 * @author Yubing Lin
 * @since  Oct 14, 2022
 */
public abstract class Passenger {

    // instance variables
    protected String username;
    protected String bio;
    protected int passengerID;

    /**
     * Constructor for the passenger class that initializes name and bio
     * @param username the name of passenger
     * @param bio the bio of passenger
     * @throws IllegalArgumentException if username or bio is null
     */
    public Passenger(String username, String bio) {
        if (username == null || bio == null) {
            throw new IllegalArgumentException();
        }
        this.username = username;
        this.bio = bio;
    }

    /**
     * Set the bio by the input bio
     * @param newBio a new bio to replace the current one
     * @throws IllegalArgumentException if newBio is null
     */
    public void setBio(String newBio) {
        if (newBio == null) {
            throw new IllegalArgumentException();
        }
        this.bio = newBio;
    }

    /**
     * Return the bio of the passenger
     * @return the passenger bio
     */
    public String displayBio() {
        return this.bio;
    }

    /**
     * Return the ID of passenger
     * @return the passenger ID
     */
    public Integer getPassengerID() {
        return this.passengerID;
    }

    /**
     * Return the name of passenger
     * @return the passenger username
     */
    public abstract String displayName();
}
