/**
 * TODO
 * @author TODO
 * @since  TODO
 */
public class PremiumVehicle extends Vehicle{

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The input vehicle is not a premium vehicle.";
    private static final String [] PREMIUM_VEHICLE_BRAND =
            new String[] {"lamborghini", "ferrari", "bmw", "mercedes","audi"};

    public PremiumVehicle(String VehicleBrand)
            throws OperationDeniedException {
        /*TODO*/
    }

    public boolean addPassengerToVehicle(Passenger p)
            throws OperationDeniedException {
        /*TODO*/
        return false;
    }

    // bmw01 (Premium) [2022-10-08]: [<Value Member> Yunyi]
    public String getVehicleInfo() {
        /*TODO*/
        return null;
    }
}