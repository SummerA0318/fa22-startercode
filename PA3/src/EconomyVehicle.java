/*
  Name: Yubing Lin
  PID:  A16994291
 */

/**
 * A class for economy vehicles to set up and assign passengers to it
 * @author Yubing Lin
 * @since  Oct 14, 2022
 */
public class EconomyVehicle extends Vehicle{

    /**
     * Constructor for Economy Vehicle that initializes a vehicle while sets its ID to be 0
     * @param VehicleName the name of the vehicle to be initialized
     * @throws IllegalArgumentException if the given VehicleName is null
     */
    public EconomyVehicle(String VehicleName) {
        super(VehicleName);
        this.vehicleID = 0;
    }

    /**
     * Add a passenger to the economy vehicle
     * @param p the passenger to add to the vehicle
     * @return true if the passenger is not in vehicle, false otherwise
     */
    public boolean addPassengerToVehicle(Passenger p){
        if (this.currentPassengers.contains(p)) {
            return false;
        } else {
            this.currentPassengers.add(p);
            this.passengerNames.add(p.displayName());
            return true;
        }
    }

    /**
     * Return the info about vehicle containing vehicle name, date and current passengers
     * @return the vehicle info in format "name [date]: [passenger]"
     */
    // civic [2022-10-08]: [Steven]
    public String getVehicleInfo() {
        return this.getVehicleName() + " [" + this.getDate()
                + "]: " + this.passengerNames;
    }
}
