/*
  Name: Yubing Lin
  PID:  A16994291
 */

/**
 * A class for Premium vehicles to set up, and make sure only value passengers are added to it
 * @author Yubing Lin
 * @since  Oct 14, 2022
 */
public class PremiumVehicle extends Vehicle{

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The input vehicle is not a premium vehicle.";
    private static final String [] PREMIUM_VEHICLE_BRAND =
            new String[] {"lamborghini", "ferrari", "bmw", "mercedes","audi"};
    private static final String DENIED_PASSENGER_GROUP =
            "This operation is disabled in your passenger group.";

    /**
     * Constructor for Premium Vehicle that initializes a vehicle while sets its ID to be 1
     * @param VehicleName the name of the vehicle to be initialized
     * @throws IllegalArgumentException if the given VehicleName is null
     * @throws OperationDeniedException if the VehicleName is not a premium vehicle name in
     * PREMIUM_VEHICLE_BRAND, throw the message of INVALID_INPUT
     */
    public PremiumVehicle(String VehicleName)
            throws OperationDeniedException {
        super(VehicleName);
        boolean premium = false;
        // Set a premium boolean to be false at first, if VehicleName matches any premium
        // vehicle name, change it to be true.
        for (String s: PREMIUM_VEHICLE_BRAND) {
            if (this.getVehicleName().toLowerCase().contains(s)) {
                premium = true;
            }
        }
        // Use the premium boolean to determine if the exception should be thrown
        if (!premium) {
            throw new OperationDeniedException(INVALID_INPUT);
        }
        this.vehicleID = 1;
    }

    /**
     * Add a passenger to the premium vehicle
     * @param p the passenger to add to the vehicle
     * @return true if the passenger is not in vehicle, false otherwise
     * @throws OperationDeniedException if p is not a value passenger that is eligible to
     * be added, throw the message of DENIED_PASSENGER_GROUP
     */
    public boolean addPassengerToVehicle(Passenger p)
            throws OperationDeniedException {
        if (!(p instanceof ValuePassenger)) {
            throw new OperationDeniedException(DENIED_PASSENGER_GROUP);
        }
        if (this.currentPassengers.contains(p)) {
            return false;}
        else {
            this.currentPassengers.add(p);
            this.passengerNames.add(p.displayName());
            return true;
        }
    }

    /**
     * Return the info about vehicle containing vehicle name, date and current passengers
     * @return the vehicle info in format "name (Premium) [date]: [<Title> passenger]"
     */
    // bmw01 (Premium) [2022-10-08]: [<Value Passenger> Yunyi]
    public String getVehicleInfo() {
        return this.getVehicleName() + " (Premium) [" + this.getDate()
                + "]: " + this.passengerNames;
    }
}
