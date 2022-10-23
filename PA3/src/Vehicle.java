/*
  Name: Yubing Lin
  PID:  A16994291
 */
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * An abstract class for two kinds of vehicles' methods
 * @author Yubing Lin
 * @since  Oct 14, 2022
 */

public abstract class Vehicle {

    private LocalDate date;
    private final String vehicle;
    protected final ArrayList<Passenger> currentPassengers;
    protected final ArrayList<String> passengerNames;
    protected int vehicleID;

    /**
     * Constructor for the vehicle class that initializes date, vehicle and passengers
     * @param VehicleName the name of constructed vehicle
     * @throws IllegalArgumentException if the given VehicleName is null
     */
    public Vehicle(String VehicleName) {
        if (VehicleName == null) {
            throw new IllegalArgumentException();
        }
        this.date = LocalDate.now();
        this.vehicle = VehicleName;
        this.currentPassengers = new ArrayList<Passenger>();
        this.passengerNames = new ArrayList<String>();
    }

    /**
     * Return the date of vehicle being constructed
     * @return the vehicle date
     */
    public LocalDate getDate(){
        return this.date;
    }

    /**
     * Return the name of vehicle being consturcted
     * @return the vehicle name
     */
    public String getVehicleName(){
        return this.vehicle;
    }

    /**
     * Return the current passengers on the vehicle
     * @return the vehicle passengers
     */
    public ArrayList<Passenger> getCurrentPassengers(){
        return this.currentPassengers;
    }

    /**
     * Return the id of vehicle
     * @return the vehicle ID as Integer
     */
    public Integer getVehicleID() {
        return (Integer)this.vehicleID;
    }

    /**
     * Add a passenger to the vehicle
     * @param p the passenger to add to the vehicle
     * @return true if the passenger is not in vehicle, false otherwise
     * @throws OperationDeniedException if the passenger doesn't fulfill requirement to be added
     */
    public abstract boolean addPassengerToVehicle(Passenger p)
            throws OperationDeniedException;

    /**
     * Return the info about vehicle containing vehicle name, date and current passengers
     * @return the vehicle info
     */
    public abstract String getVehicleInfo();
}
