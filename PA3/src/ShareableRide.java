/*
  Name: Yubing Lin
  PID:  A16994291
 */
import java.util.ArrayList;

/**
 * A class for the shareable rides to be assigned appropriate passengers and record assignments
 * @author Yubing Lin
 * @since Oct 16, 2022
 */

public class ShareableRide implements RideScheduler{

    private static final String DENIED_PASSENGER_GROUP =
            "This operation is disabled in your passenger group.";
    private final String INVALID_ACTION =
            "Not able to perform proper assignment.";
    private final int CARPOOL_LIMIT = 5;

    private ArrayList<Vehicle> vehicles;
    private ArrayList<Passenger> passengers;
    private ArrayList<String> assignments;

    /**
     * Constructor for Shareable Ride that initializes the vehicles, passengers and assignments
     */
    public ShareableRide(){
        this.vehicles = new ArrayList<Vehicle>();
        this.passengers = new ArrayList<Passenger>();
        this.assignments = new ArrayList<String>();
    }

    /**
     * Return the Vehicles in a ride
     * @return the list of vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
     * Return the Passengers in a ride
     * @return the list of passengers
     */
    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }

    /**
     * Add a passenger to the ride
     * @param p the passenger to be added
     * @return true if the passenger is not in vehicle, false otherwise
     * @throws OperationDeniedException if p is not a value passenger that is eligible for
     * shareable rides, throw the message of DENIED_PASSENGER_GROUP
     */
    public boolean addPassenger(Passenger p) throws OperationDeniedException {
        if (!(p instanceof ValuePassenger)) {
            throw new OperationDeniedException(DENIED_PASSENGER_GROUP);
        }
        if (this.passengers.contains(p)) {
            return false;}
        else {
            this.passengers.add(p);
            return true;
        }
    }

    /**
     * Add a vehicle to the ride
     * @param v the vehicle to be added
     * @return true if the passenger is not in vehicle, false otherwise
     */
    public boolean addVehicle(Vehicle v) {
        if (this.vehicles.contains(v)) {
            return false;}
        else {
            this.vehicles.add(v);
            return true;
        }
    }

    /**
     * Assign all the passengers in the ride to vehicles
     * @throws OperationDeniedException if the number of passengers is larger than 5 times the
     * number of vehicles, throw the message of INVALID_ACTION
     */
    public void assignPassengerToVehicle() throws OperationDeniedException {
        if (this.passengers.size() > this.vehicles.size() * this.CARPOOL_LIMIT) {
            throw new OperationDeniedException(INVALID_ACTION);
        }
        int numInOne = 0;
        int indexVehicle = 0;
        // Assign passengers to each vehicle until one has five passengers in it then assign
        // to the next vehicle
        for (int i=0; i<this.passengers.size(); i++) {
            if (numInOne < this.CARPOOL_LIMIT) {
                this.vehicles.get(indexVehicle).addPassengerToVehicle(this.passengers.get(i));
                numInOne += 1;
            } else {
                indexVehicle += 1;
                this.vehicles.get(indexVehicle).addPassengerToVehicle(this.passengers.get(i));
                numInOne = 1;
            }
        }
        // Add the info of all the vehicles with passengers in it to the assignments since
        // if they have passengers then they have been assigned.
        for (Vehicle v: this.vehicles) {
            if (v.getCurrentPassengers().size() != 0) {
                this.assignments.add(v.getVehicleInfo());
            }
        }
    }

    /**
     * Return the records of assignments
     * @return the assignment of passengers to vehicles
     */
    public ArrayList<String> getRecords() {
        return this.assignments;
    }
}
