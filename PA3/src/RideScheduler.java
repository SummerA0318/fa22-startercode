/*
  Name: Yubing Lin
  PID:  A16994291
 */
import java.util.ArrayList;
/**
 * A interface for passengers to schedule ride
 * @author Yubing Lin
 * @since  Oct 17, 2022
 */

public interface RideScheduler {
    /**
     * Return the Vehicles in a ride
     * @return the list of vehicles
     */
    ArrayList<Vehicle> getVehicles();

    /**
     * Return the Passengers in a ride
     * @return the list of passengers
     */
    ArrayList<Passenger> getPassengers();

    /**
     * Add a passenger to the ride
     * @param p the passenger to be added
     * @return true if the passenger is not in vehicle, false otherwise
     * @throws OperationDeniedException if the passenger doesn't fulfill requirement to be added
     */
    boolean addPassenger(Passenger p) throws OperationDeniedException;

    /**
     * Add a vehicle to the ride
     * @param v the vehicle to be added
     * @return true if the passenger is not in vehicle, false otherwise
     */
    boolean addVehicle(Vehicle v);

    /**
     * Assign all the passengers in the ride to vehicles
     * @throws OperationDeniedException if the passengers doesn't match the vehicles
     * in number or eligibility
     */
    void assignPassengerToVehicle() throws OperationDeniedException;

    /**
     * Return the records of assignments
     * @return the assignment of passengers to vehicles
     */
    ArrayList<String> getRecords();
}
