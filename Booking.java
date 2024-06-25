package com.mycompany.flightreservationredux;

/**
 * The Booking class represents a booking made by a customer for a flight.
 * It contains information such as booking ID, customer ID, booking day, booking time, country, and city.
 * 
 * @author Jose Antoinne Templonuevo
 * @version 1.0
 */
public class Booking {
    private int id;
    private final String day;
    private final String time;
    private final String country;
    private final String city;
    private final int customer_Id;

    /**
     * Constructs a Booking object with the specified parameters.
     *
     * @param id         The booking ID.
     * @param customer_Id The customer ID associated with the booking.
     * @param day        The day of the booking.
     * @param time       The time of the booking.
     * @param country    The country of the booking.
     * @param city       The city of the booking.
     */
    public Booking(int id, int customer_Id, String day, String time, String country, String city) {
        this.id = id;
        this.customer_Id = customer_Id;
        this.day = day;
        this.time = time;
        this.country = country;
        this.city = city;
    }

    /**
     * Constructs a Booking object with the specified parameters.
     *
     * @param customer_Id The customer ID associated with the booking.
     * @param day        The day of the booking.
     * @param time       The time of the booking.
     * @param country    The country of the booking.
     * @param city       The city of the booking.
     */
    public Booking(int customer_Id, String day, String time, String country, String city) {
        this.customer_Id = customer_Id;
        this.day = day;
        this.time = time;
        this.country = country;
        this.city = city;
    }

    /**
     * Sets the booking ID.
     *
     * @param id The booking ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the booking ID.
     *
     * @return The booking ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the customer ID associated with the booking.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customer_Id;
    }

    /**
     * Retrieves the day of the booking.
     *
     * @return The day of the booking.
     */
    public String getDay() {
        return day;
    }

    /**
     * Retrieves the time of the booking.
     *
     * @return The time of the booking.
     */
    public String getTime() {
        return time;
    }

    /**
     * Retrieves the country of the booking.
     *
     * @return The country of the booking.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Retrieves the city of the booking.
     *
     * @return The city of the booking.
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns a string representation of the Booking object.
     *
     * @return A string containing the booking details.
     */
    @Override
    public String toString() {
        return "Booking{" +
                "Booking ID=" + id +
                ", Customer ID=" + customer_Id +
                ", Day='" + day + '\'' +
                ", Time='" + time + '\'' +
                ", Country='" + country + '\'' +
                ", City='" + city + '\'' +
                '}';
    }
}