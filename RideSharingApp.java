import java.util.*;

// Custom Exception for invalid ride types
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

// Abstract Class (Base Class for all rides)
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    protected double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    public String getDriverName() { return driverName; }
    public String getVehicleNumber() { return vehicleNumber; }
    public double getDistance() { return distance; }

    public abstract double calculateFare();
}

// Subclass for Bike Rides
class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }
    public double calculateFare() {
        return distance * 10; // ₹10 per km
    }
}

// Subclass for Car Rides
class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }
    public double calculateFare() {
        return distance * 20; // ₹20 per km
    }
}

// Main Application
public class RideSharingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Prompt for ride type
            System.out.print("Enter ride type (bike/car): ");
            String rideType = sc.nextLine().trim().toLowerCase();

            // Prompt for distance
            System.out.print("Enter distance in km: ");
            double distance = sc.nextDouble();
            sc.close();

            if (distance <= 0) {
                throw new IllegalArgumentException("Distance must be greater than 0");
            }
            sc.close();

            Ride ride;
            if (rideType.equals("bike")) {
                ride = new BikeRide("Ramesh", "WB 64 L 2221", distance);
            } else if (rideType.equals("car")) {
                ride = new CarRide("Suresh", "WB 64 X 1035", distance);
            } else {
                throw new InvalidRideTypeException("Invalid ride type! Choose 'bike' or 'car'.");
            }

            // Output
            System.out.println("\n--- Ride Details ---");
            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: ₹" + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
