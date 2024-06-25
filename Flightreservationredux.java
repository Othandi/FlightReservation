/**
 * The Flightreservationredux class serves as the entry point for the Flight Reservation Redux application.
 * It contains the main method which initializes the application by creating an instance of FlightReservationFrame
 * using SwingUtilities.invokeLater() to ensure the Swing components are initialized on the Event Dispatch Thread.
 * 
 * @author Jose Antoinne Templonuevo
 * @version 1.0
 */
package com.mycompany.flightreservationredux;

import javax.swing.SwingUtilities;


public class Flightreservationredux {
    /**
     * The main method of the Flightreservationredux class.
     * It initializes the Flight Reservation Redux application by creating an instance of FlightReservationFrame
     * using SwingUtilities.invokeLater() to ensure the Swing components are initialized on the Event Dispatch Thread.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        SwingUtilities.invokeLater(FlightReservationFrame::new); 
    }
}