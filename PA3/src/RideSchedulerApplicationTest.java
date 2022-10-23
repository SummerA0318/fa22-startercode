/*
  Name: Yubing Lin
  PID:  A16994291
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Junit test cases for Passengers, Vehicles and Schedules
 * @author Yubing Lin
 * @since  Oct 16, 2022
 */
public class RideSchedulerApplicationTest {
    protected static final String DENIED_PASSENGER_GROUP =
            "This operation is disabled in your passenger group.";
    private static final String INVALID_INPUT =
            "The input vehicle is not a premium vehicle.";
    private final String MISMATCH_MSG =
            "Each passenger should have one vehicle matched.";
    private final String INVALID_ACTION =
            "Not able to perform proper assignment.";
    ValuePassenger yunyi;
    StandardPassenger viren;
    PremiumVehicle pv1;
    EconomyVehicle ev1;
    LocalDate date = LocalDate.now();


    @Before
    public void setup() throws OperationDeniedException {
        yunyi = new ValuePassenger("Yunyi", "Tutor");
        viren = new StandardPassenger("Viren", "Tutor");
        pv1 = new PremiumVehicle("bmw01");
        ev1 = new EconomyVehicle("normal");
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestValuePassengerThrowsIAE(){
        yunyi = new ValuePassenger("Yunyi",null);
    }

    @Test
    public void TestPremiumVehicleThrowsODE(){
        try {
            pv1.addPassengerToVehicle(viren);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(DENIED_PASSENGER_GROUP, ode.getMessage());
        }
    }

    @Test
    public void TestEconomyVehicle1() {
        ev1 = new EconomyVehicle("vehicle");
        assertEquals("normal", ev1.getVehicleName());
        assertEquals((Integer)0, ev1.getVehicleID());
        assertEquals("normal [" + date + "]: []", ev1.getVehicleInfo());
        ArrayList<Passenger> empty = new ArrayList<Passenger>();
        assertEquals(empty, ev1.getCurrentPassengers());
        assertEquals(ev1.getDate(), date);
        assertEquals(true, ev1.addPassengerToVehicle(yunyi));
        empty.add(yunyi);
        assertEquals("normal [" + date + "]: [<Value Passenger> Yunyi]",
                ev1.getVehicleInfo());
        assertEquals(empty, ev1.getCurrentPassengers());
    }

    @Test
    public void TestEconomyVehicle2() {
        ev1 = new EconomyVehicle("vehicle");
        assertEquals(true, ev1.addPassengerToVehicle(viren));
        assertEquals(false, ev1.addPassengerToVehicle(viren));
        assertEquals("vehicle", ev1.getVehicleName());
        assertEquals("vehicle [" + date + "]: [Viren]", ev1.getVehicleInfo());
        assertEquals((Integer)0, ev1.getVehicleID());
        assertEquals(ev1.getDate(), date);
        ArrayList<Passenger> empty = new ArrayList<Passenger>();
        empty.add(viren);
        assertEquals(empty, ev1.getCurrentPassengers());
    }

    @Test
    public void TestEconomyVehicle3() {
        ev1 = new EconomyVehicle("Vehicle");
        assertEquals(ev1.getVehicleName(), "Vehicle");
        assertEquals(ev1.getDate(), date);
        assertEquals((Integer)0, ev1.getVehicleID());
        assertEquals(true, ev1.addPassengerToVehicle(viren));
        assertEquals(true, ev1.addPassengerToVehicle(yunyi));
        ArrayList<Passenger> empty = new ArrayList<Passenger>();
        empty.add(viren);
        empty.add(yunyi);
        assertEquals(empty, ev1.getCurrentPassengers());
        assertEquals("Vehicle [" + date + "]: [Viren, <Value Passenger> Yunyi]",
                ev1.getVehicleInfo());
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestEconomyVehicleThrowIAE() {
        ev1 = new EconomyVehicle(null);
    }

    @Test
    public void TestPremiumVehicle1() throws OperationDeniedException {
        assertEquals("bmw01", pv1.getVehicleName());
        assertEquals((Integer)1, pv1.getVehicleID());
        assertEquals("bmw01 (Premium) [" + date + "]: []", pv1.getVehicleInfo());
        ArrayList<Passenger> empty = new ArrayList<Passenger>();
        assertEquals(empty, pv1.getCurrentPassengers());
        assertEquals(pv1.getDate(), date);
        assertEquals(true, pv1.addPassengerToVehicle(yunyi));
        empty.add(yunyi);
        assertEquals("bmw01 (Premium) [" + date + "]: [<Value Passenger> Yunyi]",
                pv1.getVehicleInfo());
        assertEquals(empty, pv1.getCurrentPassengers());
    }

    @Test
    public void TestPremiumVehicle2() throws OperationDeniedException {
        pv1 = new PremiumVehicle("Bmw01 brown");
        assertEquals(true, pv1.addPassengerToVehicle(yunyi));
        assertEquals(false, pv1.addPassengerToVehicle(yunyi));
        assertEquals("Bmw01 brown", pv1.getVehicleName());
        assertEquals("Bmw01 brown (Premium) [" + date + "]: [<Value Passenger> Yunyi]",
                pv1.getVehicleInfo());
        assertEquals((Integer)1, pv1.getVehicleID());
        assertEquals(pv1.getDate(), date);
        ArrayList<Passenger> empty = new ArrayList<Passenger>();
        empty.add(yunyi);
        assertEquals(empty, pv1.getCurrentPassengers());
    }

    @Test
    public void TestPremiumVehicle3() throws OperationDeniedException {
        pv1 = new PremiumVehicle("AUDI76");
        ValuePassenger yubing = new ValuePassenger("Yubing", "Student");
        assertEquals(pv1.getVehicleName(), "AUDI76");
        assertEquals(pv1.getDate(), date);
        assertEquals((Integer)1, pv1.getVehicleID());
        assertEquals(true, pv1.addPassengerToVehicle(yubing));
        assertEquals(true, pv1.addPassengerToVehicle(yunyi));
        ArrayList<Passenger> empty = new ArrayList<Passenger>();
        empty.add(yubing);
        empty.add(yunyi);
        assertEquals(empty, pv1.getCurrentPassengers());
        assertEquals("AUDI76 (Premium) [" + date + "]: [<Value Passenger> Yubing," +
                        " <Value Passenger> Yunyi]", pv1.getVehicleInfo());
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestPremiumVehicleThrowIAE() throws OperationDeniedException {
        pv1 = new PremiumVehicle(null);
    }

    @Test
    public void TestPremiumVehicleThrowODE(){
        try {
            pv1 = new PremiumVehicle("bwm01");
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    @Test
    public void TestPremiumVehicleAddThrowODE() {
        try {
            pv1.addPassengerToVehicle(viren);
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(DENIED_PASSENGER_GROUP, ode.getMessage());
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestStandardPassengerSetBioIAE() {
        viren.setBio(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestStandardPassengerIAE1() {
        viren = new StandardPassenger(null, "Tutor");
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestStandardPassengerIAE2() {
        viren = new StandardPassenger("Viren", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestStandardPassengerIAE3() {
        viren = new StandardPassenger(null, null);
    }

    @Test
    public void TestStandardPassenger1() {
        assertEquals("Tutor", viren.displayBio());
        viren.setBio("tutor");
        assertEquals("tutor", viren.displayBio());
        assertEquals((Integer)0, viren.getPassengerID());
        assertEquals("Viren", viren.displayName());
    }

    @Test
    public void TestStandardPassenger2() {
        StandardPassenger ben = new StandardPassenger("Ben", "Godfather");
        assertEquals("Godfather", ben.displayBio());
        ben.setBio("Cursenday");
        assertEquals("Cursenday", ben.displayBio());
        assertEquals((Integer)0, ben.getPassengerID());
        assertEquals("Ben", ben.displayName());
    }

    @Test
    public void TestStandardPassenger3() {
        StandardPassenger summer = new StandardPassenger("Summer", "Student");
        assertEquals("Student", summer.displayBio());
        summer.setBio("Lawyer");
        assertEquals("Lawyer", summer.displayBio());
        assertEquals((Integer)0, summer.getPassengerID());
        assertEquals("Summer", summer.displayName());
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestValuePassengerSetBioIAE() {
        yunyi.setBio(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestValuePassengerIAE1() {
        yunyi = new ValuePassenger(null, "Tutor");
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestValuePassengerIAE2() {
        yunyi = new ValuePassenger("Yunyi", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestValuePassengerIAE3() {
        yunyi = new ValuePassenger(null, null);
    }

    @Test
    public void TestValuePassenger1() {
        assertEquals("Tutor", yunyi.displayBio());
        yunyi.setBio("tutor");
        assertEquals("tutor", yunyi.displayBio());
        assertEquals((Integer)1, yunyi.getPassengerID());
        assertEquals("<Value Passenger> Yunyi", yunyi.displayName());
        yunyi.setCustomTitle("VIP");
        assertEquals("<VIP> Yunyi", yunyi.displayName());
    }

    @Test
    public void TestValuePassenger2() {
        ValuePassenger ben = new ValuePassenger("Ben", "Godfather");
        assertEquals("Godfather", ben.displayBio());
        ben.setBio("Cursenday");
        assertEquals("Cursenday", ben.displayBio());
        assertEquals((Integer)1, ben.getPassengerID());
        assertEquals("<Value Passenger> Ben", ben.displayName());
        ben.setCustomTitle("No title");
        assertEquals("<No title> Ben", ben.displayName());
    }

    @Test
    public void TestValuePassenger3() {
        ValuePassenger summer = new ValuePassenger("Summer", "Student");
        assertEquals("Student", summer.displayBio());
        summer.setBio("Lawyer");
        assertEquals("Lawyer", summer.displayBio());
        assertEquals((Integer)1, summer.getPassengerID());
        assertEquals("<Value Passenger> Summer", summer.displayName());
        summer.setCustomTitle("Alexander");
        assertEquals("<Alexander> Summer", summer.displayName());
    }

    @Test
    public void TestStandardRide1() throws OperationDeniedException {
        StandardRide sr = new StandardRide();
        ArrayList<Passenger> twoPassengers = new ArrayList<Passenger>();
        ArrayList<Vehicle> twoVehicles = new ArrayList<Vehicle>();
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(twoPassengers, sr.getPassengers());
        assertEquals(twoVehicles, sr.getVehicles());
        assertEquals(expected, sr.getRecords());
        assertEquals(true, sr.addPassenger(yunyi));
        assertEquals(false, sr.addPassenger(yunyi));
        assertEquals(true, sr.addPassenger(viren));
        assertEquals(true, sr.addVehicle(ev1));
        assertEquals(true, sr.addVehicle(pv1));
        assertEquals(false, sr.addVehicle(pv1));
        twoPassengers.add(yunyi);
        twoPassengers.add(viren);
        twoVehicles.add(ev1);
        twoVehicles.add(pv1);
        assertEquals(twoPassengers, sr.getPassengers());
        assertEquals(twoVehicles, sr.getVehicles());
        sr.assignPassengerToVehicle();
        expected.add("normal ["+date+"]: [Viren]");
        expected.add("bmw01 (Premium) ["+date+"]: [<Value Passenger> Yunyi]");
        assertEquals(expected, sr.getRecords());
    }

    @Test
    public void TestStandardRide2() throws OperationDeniedException {
        StandardRide sr = new StandardRide();
        sr.addVehicle(ev1);
        sr.addPassenger(yunyi);
        sr.assignPassengerToVehicle();
        ArrayList<Passenger> twoPassengers = new ArrayList<Passenger>();
        ArrayList<Vehicle> twoVehicles = new ArrayList<Vehicle>();
        twoPassengers.add(yunyi);
        twoVehicles.add(ev1);
        assertEquals(twoPassengers, sr.getPassengers());
        assertEquals(twoVehicles, sr.getVehicles());
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("normal ["+date+"]: [<Value Passenger> Yunyi]");
        assertEquals(expected, sr.getRecords());
    }

    @Test
    public void TestStandardRide3() throws OperationDeniedException {
        StandardRide sr = new StandardRide();
        StandardPassenger sp1 = new StandardPassenger("Yubing", "Student");
        ValuePassenger vp1 = new ValuePassenger("ben", "Godfather");
        ValuePassenger vp2 = new ValuePassenger("summer", "lawyer");
        EconomyVehicle ev2 = new EconomyVehicle("SUV");
        EconomyVehicle ev3 = new EconomyVehicle("Tuguan");
        PremiumVehicle pv2 = new PremiumVehicle("Lamborghini Black");
        vp1.setCustomTitle("No title");
        sr.addPassenger(yunyi);
        sr.addPassenger(viren);
        sr.addPassenger(sp1);
        sr.addPassenger(vp1);
        sr.addPassenger(vp2);
        sr.addVehicle(ev1);
        sr.addVehicle(ev2);
        sr.addVehicle(ev3);
        sr.addVehicle(pv1);
        sr.addVehicle(pv2);
        sr.assignPassengerToVehicle();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("normal ["+date+"]: [Viren]");
        expected.add("SUV ["+date+"]: [Yubing]");
        expected.add("Tuguan ["+date+"]: [<Value Passenger> Yunyi]");
        expected.add("bmw01 (Premium) ["+date+"]: [<No title> ben]");
        expected.add("Lamborghini Black (Premium) ["+date+"]: [<Value Passenger> summer]");
        assertEquals(expected, sr.getRecords());
    }

    @Test
    public void TestStandardRideAddODE() {
        StandardRide sr = new StandardRide();
        sr.addPassenger(yunyi);
        sr.addPassenger(viren);
        sr.addVehicle(ev1);
        try {
            sr.assignPassengerToVehicle();
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(MISMATCH_MSG, ode.getMessage());
        }
    }

    @Test
    public void TestStandardRideAddODE2() {
        StandardRide sr = new StandardRide();
        sr.addPassenger(viren);
        sr.addVehicle(pv1);
        try {
            sr.assignPassengerToVehicle();
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_ACTION, ode.getMessage());
        }
    }

    @Test
    public void TestShareableRide1() throws OperationDeniedException {
        ShareableRide sr = new ShareableRide();
        ValuePassenger vp1 = new ValuePassenger("ben", "Godfather");
        ValuePassenger vp2 = new ValuePassenger("summer", "lawyer");
        ArrayList<Passenger> twoPassengers = new ArrayList<Passenger>();
        ArrayList<Vehicle> vehicle = new ArrayList<Vehicle>();
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(twoPassengers, sr.getPassengers());
        assertEquals(vehicle, sr.getVehicles());
        assertEquals(expected, sr.getRecords());
        vp1.setCustomTitle("No title");
        assertEquals(true, sr.addPassenger(yunyi));
        assertEquals(false, sr.addPassenger(yunyi));
        assertEquals(true, sr.addPassenger(vp1));
        assertEquals(true, sr.addPassenger(vp2));
        assertEquals(true, sr.addVehicle(ev1));
        twoPassengers.add(yunyi);
        twoPassengers.add(vp1);
        twoPassengers.add(vp2);
        vehicle.add(ev1);
        assertEquals(twoPassengers, sr.getPassengers());
        assertEquals(vehicle, sr.getVehicles());
        sr.assignPassengerToVehicle();
        expected.add("normal ["+date+"]: [<Value Passenger> Yunyi," +
                " <No title> ben, <Value Passenger> summer]");
        assertEquals(expected, sr.getRecords());
    }

    @Test
    public void TestShareableRide2() throws OperationDeniedException {
        ShareableRide sr = new ShareableRide();
        ValuePassenger vp1 = new ValuePassenger("ben", "Godfather");
        ValuePassenger vp2 = new ValuePassenger("summer", "lawyer");
        ValuePassenger vp3 = new ValuePassenger("Yubing", "Student");
        ValuePassenger vp4 = new ValuePassenger("Mint", "Docter");
        ValuePassenger vp5 = new ValuePassenger("Robin", "Knight");
        ValuePassenger vp6 = new ValuePassenger("Solomon", "Astrologer");
        ArrayList<Passenger> twoPassengers = new ArrayList<Passenger>();
        ArrayList<Vehicle> vehicle = new ArrayList<Vehicle>();
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(twoPassengers, sr.getPassengers());
        assertEquals(vehicle, sr.getVehicles());
        assertEquals(expected, sr.getRecords());
        vp1.setCustomTitle("No title");
        assertEquals(true, sr.addPassenger(yunyi));
        assertEquals(true, sr.addPassenger(vp1));
        assertEquals(true, sr.addPassenger(vp2));
        assertEquals(true, sr.addPassenger(vp3));
        assertEquals(true, sr.addPassenger(vp4));
        assertEquals(true, sr.addPassenger(vp5));
        assertEquals(true, sr.addPassenger(vp6));
        assertEquals(true, sr.addVehicle(pv1));
        assertEquals(true, sr.addVehicle(ev1));
        twoPassengers.add(yunyi);
        twoPassengers.add(vp1);
        twoPassengers.add(vp2);
        twoPassengers.add(vp3);
        twoPassengers.add(vp4);
        twoPassengers.add(vp5);
        twoPassengers.add(vp6);
        vehicle.add(pv1);
        vehicle.add(ev1);
        assertEquals(twoPassengers, sr.getPassengers());
        assertEquals(vehicle, sr.getVehicles());
        sr.assignPassengerToVehicle();
        expected.add("bmw01 (Premium) ["+date+"]: [<Value Passenger> Yunyi," +
                " <No title> ben, <Value Passenger> summer, <Value Passenger> Yubing," +
                " <Value Passenger> Mint]");
        expected.add("normal ["+date+"]: [<Value Passenger> Robin, <Value Passenger> Solomon]");
        assertEquals(expected, sr.getRecords());
    }

    @Test
    public void TestShareableRide3() throws OperationDeniedException {
        ShareableRide sr = new ShareableRide();
        ValuePassenger vp1 = new ValuePassenger("ben", "Godfather");
        ValuePassenger vp2 = new ValuePassenger("summer", "lawyer");
        ValuePassenger vp3 = new ValuePassenger("Yubing", "Student");
        ValuePassenger vp4 = new ValuePassenger("Mint", "Docter");
        ValuePassenger vp5 = new ValuePassenger("Robin", "Knight");
        ValuePassenger vp6 = new ValuePassenger("Solomon", "Astrologer");
        ValuePassenger vp7 = new ValuePassenger("KP", "Crow");
        ValuePassenger vp8 = new ValuePassenger("Winter", "Author");
        ValuePassenger vp9 = new ValuePassenger("Ball", "Sister");
        ValuePassenger vp10 = new ValuePassenger("Sylvia", "Friend");
        ValuePassenger vp11 = new ValuePassenger("Rose", "Knight");
        PremiumVehicle pv2 = new PremiumVehicle("Lamborghini Black");
        ArrayList<String> expected = new ArrayList<String>();
        sr.addPassenger(yunyi);
        sr.addPassenger(vp1);
        sr.addPassenger(vp2);
        sr.addPassenger(vp3);
        sr.addPassenger(vp4);
        sr.addPassenger(vp5);
        sr.addPassenger(vp6);
        sr.addPassenger(vp7);
        sr.addPassenger(vp8);
        sr.addPassenger(vp9);
        sr.addPassenger(vp10);
        sr.addPassenger(vp11);
        sr.addVehicle(pv1);
        sr.addVehicle(ev1);
        sr.addVehicle(pv2);
        sr.assignPassengerToVehicle();
        expected.add("bmw01 (Premium) ["+date+"]: [<Value Passenger> Yunyi," +
                " <Value Passenger> ben, <Value Passenger> summer, <Value Passenger> Yubing," +
                " <Value Passenger> Mint]");
        expected.add("normal ["+date+"]: [<Value Passenger> Robin, <Value Passenger> Solomon," +
                " <Value Passenger> KP, <Value Passenger> Winter, <Value Passenger> Ball]");
        expected.add("Lamborghini Black (Premium) ["+date+"]: [<Value Passenger> Sylvia, " +
                "<Value Passenger> Rose]");
        assertEquals(expected, sr.getRecords());
    }

    @Test
    public void TestShareableRideAddODE() {
        ShareableRide sr = new ShareableRide();
        try {
            sr.addPassenger(viren);
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(DENIED_PASSENGER_GROUP ,ode.getMessage());
        }
    }

    @Test
    public void TestShareableRideAssignODE() throws OperationDeniedException {
        ShareableRide sr = new ShareableRide();
        ValuePassenger vp3 = new ValuePassenger("Yubing", "Student");
        ValuePassenger vp1 = new ValuePassenger("ben", "Godfather");
        ValuePassenger vp2 = new ValuePassenger("summer", "lawyer");
        ValuePassenger vp4 = new ValuePassenger("Mint", "Docter");
        ValuePassenger vp5 = new ValuePassenger("Robin", "Knight");
        ValuePassenger vp6 = new ValuePassenger("Solomon", "Astrologer");
        sr.addPassenger(vp1);
        sr.addPassenger(vp2);
        sr.addPassenger(vp3);
        sr.addPassenger(vp4);
        sr.addPassenger(vp5);
        sr.addPassenger(vp6);
        sr.addVehicle(pv1);
        try {
            sr.assignPassengerToVehicle();
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_ACTION ,ode.getMessage());
        }
    }
}
