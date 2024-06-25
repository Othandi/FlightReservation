package com.mycompany.flightreservationredux;

/**
 * The Customer class represents a customer entity with an ID and a name.
 * It provides constructors to create a customer with just a name or with both an ID and a name.
 * Getter and setter methods are provided for both fields, allowing access and modification.
 * Additionally, it overrides the toString() method to provide a custom string representation of a Customer object.
 *
 * @author Jose Antoinne Templonuevo
 * @version 1.0
 */

public class Customer {
    private int id;
    private String name;

    /**
     * Constructs a customer with the given name.
     *
     * @param name The name of the customer
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * Constructs a customer with the given ID and name.
     *
     * @param id   The ID of the customer
     * @param name The name of the customer
     */
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * Retrieves the ID of the customer.
     *
     * @return The ID of the customer
     */
    public int getId(){
        return id;
    }
    
    /**
     * Sets the ID of the customer.
     *
     * @param id The ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return The name of the customer
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the customer.
     *
     * @param name The name to set
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Returns a string representation of the customer.
     *
     * @return A string representation of the customer
     */
     @Override
    public String toString() {
         return "Customer{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                '}';
    }
}
