/*
  Name: Yubing Lin
  PID:  A16994291
 */
import java.util.ArrayList;

/**
 * A class for standard rides get appropriate passengers assigned and record the assignments
 * @author Yubing Lin
 * @since Oct 14, 2022
 */

public class StandardRide implements RideScheduler{

    private final String MISMATCH_MSG =
            "Each passenger should have one vehicle matched.";
    private final String INVALID_ACTION =
            "Not able to perform proper assignment.";
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Passenger> passengers;
    private ArrayList<String> assignments;

    /**
     * Constructor for Standard Ride that initializes the vehicles, passengers and assignments
     */
    public StandardRide(){
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
     */
    public boolean addPassenger(Passenger p) {
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
     * @throws OperationDeniedException if the number of passengers doesn't match the
     * number of vehicles, throw the message of MISMATCH_MSG
     * @throws OperationDeniedException if the number of standard passengers is more than
     * the number of standard vehicles, throw the message of INVALID_ACTION
     */
    public void assignPassengerToVehicle() throws OperationDeniedException {
        if (this.vehicles.size() != this.passengers.size()) {
            throw new OperationDeniedException(MISMATCH_MSG);
        }
        ArrayList<Passenger> standardP = new ArrayList<Passenger>();
        ArrayList<Passenger> valueP = new ArrayList<Passenger>();
        ArrayList<Vehicle> standardV = new ArrayList<Vehicle>();
        ArrayList<Vehicle> premiumV = new ArrayList<Vehicle>();
        // Loop over all the passengers and divide them into value passengers and standard
        // passengers the two lists by their passenger ID
        for (Passenger p: this.passengers) {
            if ((int)p.getPassengerID() == 0) {
                standardP.add(p);
            } else {
                valueP.add(p);
            }
        }
        // Loop over all the vehicles and divide them into premium vehicles and economy
        // vehicles the two lists by their vehicle ID
        for (Vehicle v: this.vehicles) {
            if ((int)v.getVehicleID() == 0) {
                standardV.add(v);
            } else {
                premiumV.add(v);
            }
        }
        if (standardP.size() > standardV.size()) {
            throw new OperationDeniedException(INVALID_ACTION);
        }
        // First assign all the standard passengers to standard vehicles
        for (int i=0; i<standardP.size(); i++) {
            standardV.get(i).addPassengerToVehicle(standardP.get(i));
            this.assignments.add(standardV.get(i).getVehicleInfo());
        }
        // Then assign the remaining standard vehicles with value passengers
        int index = standardP.size();
        while (standardV.size() - index != 0) {
            standardV.get(index).addPassengerToVehicle(valueP.get(index - standardP.size()));
            this.assignments.add(standardV.get(index).getVehicleInfo());
            index += 1;
        }
        // Then assign the remaining value passengers to premium vehicles
        index = index  - standardP.size();
        for (int j=0; j<premiumV.size(); j++) {
            premiumV.get(j).addPassengerToVehicle(valueP.get(index));
            this.assignments.add(premiumV.get(j).getVehicleInfo());
            index += 1;
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
