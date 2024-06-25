package com.mycompany.flightreservationredux;

/*
  import packages
 */

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The FlightreservationreduxFrame class serves as the User Interface for the Flight Reservation Redux application.
 * 
 * @author Jose Antoinne Templonuevo
 * @version 1.0
 */
class FlightReservationFrame extends JFrame {
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private final ArrayList<Customer> customers;
    private final ArrayList<Booking> bookings;

    private final ListCustomersPanel listCustomersPanel;
    private final ListBookingsPanel listBookingsPanel;

    public DataCache dataCache;

    public FlightReservationFrame() {
        dataCache = new DataCache();
        customers = dataCache.getAllCustomers(); // Load customers from cache
        bookings = dataCache.getAllBookings();   // Load bookings from cache

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new MainMenuPanel(this), "MainMenu");
        mainPanel.add(new AddCustomerPanel(this), "AddCustomer");
        listCustomersPanel = new ListCustomersPanel(this);
        mainPanel.add(listCustomersPanel, "ListCustomers");
        mainPanel.add(new AddBookingPanel(this), "AddBooking");
        listBookingsPanel = new ListBookingsPanel(this);
        mainPanel.add(listBookingsPanel, "ListBookings");

        add(mainPanel);
        setTitle("Flight Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public void showPanel(String panelName) {
        if (panelName.equals("ListCustomers")) {
            listCustomersPanel.updateCustomerList();
        } else if (panelName.equals("ListBookings")) {
            listBookingsPanel.updateBookingList();
        }
        cardLayout.show(mainPanel, panelName);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        dataCache.putCustomer(customer); // Save customer to cache
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        dataCache.putBooking(booking); // Save booking to cache
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}


/**
 * Panel for the main menu of the Flight Reservation System GUI.
 */
class MainMenuPanel extends JPanel {  
    public MainMenuPanel(FlightReservationFrame frame) { 
        setLayout(new GridLayout(5, 2));  

        JButton addCustomerButton = new JButton("Add Customer");
        addCustomerButton.addActionListener(e -> frame.showPanel("AddCustomer"));

        JButton listCustomersButton = new JButton("List Customers");
        listCustomersButton.addActionListener(e -> frame.showPanel("ListCustomers"));

        JButton addBookingButton = new JButton("Add Booking");
        addBookingButton.addActionListener(e -> frame.showPanel("AddBooking"));

        JButton listBookingsButton = new JButton("List Bookings");
        listBookingsButton.addActionListener(e -> frame.showPanel("ListBookings"));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        add(addCustomerButton);
        add(listCustomersButton);
        add(addBookingButton);
        add(listBookingsButton);
        add(exitButton);
    }
}

/**
 * Panel for adding a new customer to the Flight Reservation System GUI.
 */
class AddCustomerPanel extends JPanel {
    public AddCustomerPanel(FlightReservationFrame frame) {
        setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Customer Name:");
        JTextField nameField = new JTextField();

        JButton addButton = new JButton("Add Customer");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Customer name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Customer customer = new Customer(name);
                frame.addCustomer(customer);
                JOptionPane.showMessageDialog(this, "Customer added successfully.");
                frame.showPanel("MainMenu");
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> frame.showPanel("MainMenu"));

        add(nameLabel);
        add(nameField);
        add(addButton);
        add(cancelButton);
    }
}


/**
 * Panel for listing customers in the Flight Reservation System GUI.
 */
class ListCustomersPanel extends JPanel {
    private final JList<Customer> customerList;
    private final FlightReservationFrame frame;

    public ListCustomersPanel(FlightReservationFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        customerList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(customerList);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.showPanel("MainMenu"));

        JButton deleteCustomerButton = new JButton("Delete Customer");
        deleteCustomerButton.addActionListener(e -> {
            Customer selectedCustomer = customerList.getSelectedValue();
            if (selectedCustomer != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the following customer?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        db.deleteCustomer(selectedCustomer.getId());
                    } catch (SQLException ex) {
                        frame.dataCache.removeCustomer(selectedCustomer.getId());
                    }
                    updateCustomerList();
                    JOptionPane.showMessageDialog(this, "Customer deleted successfully.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No customer selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(deleteCustomerButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateCustomerList() {
        ArrayList<Customer> customers = frame.getCustomers();
        Customer[] customerArray = new Customer[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            customerArray[i] = customers.get(i);
        }
        customerList.setListData(customerArray);
    }
}



/**
 * Panel for adding a new booking to the Flight Reservation System GUI.
 */
class AddBookingPanel extends JPanel {
    public AddBookingPanel(FlightReservationFrame frame) {
        setLayout(new GridLayout(7, 2));

        JLabel custNoLabel = new JLabel("Customer ID:");
        JTextField custNoField = new JTextField();

        JLabel dayLabel = new JLabel("Day:");
        JComboBox<String> dayComboBox = new JComboBox<>(new String[]{
                "April 22, 2024", "April 24, 2024", "April 26, 2024", "April 28, 2024"
        });

        JLabel timeLabel = new JLabel("Time:");
        JComboBox<String> timeComboBox = new JComboBox<>(new String[]{
                "10 AM", "12 PM", "2 PM", "4 PM", "6 PM"
        });

        JLabel countryLabel = new JLabel("Country:");
        JComboBox<String> countryComboBox = new JComboBox<>(new String[]{
                "Australia", "Canada", "USA", "Philippines"
        });

        JLabel cityLabel = new JLabel("City:");
        JComboBox<String> cityComboBox = new JComboBox<>(new String[]{
                "Melbourne", "Toronto", "New York", "Manila"
        });

        JButton addButton = new JButton("Add Booking");
        addButton.addActionListener(e -> {
            try {
                int custId = Integer.parseInt(custNoField.getText());
                String day = (String) dayComboBox.getSelectedItem();
                String time = (String) timeComboBox.getSelectedItem();
                String country = (String) countryComboBox.getSelectedItem();
                String city = (String) cityComboBox.getSelectedItem();
                Booking booking = new Booking(custId, day, time, country, city);
                frame.addBooking(booking);
                JOptionPane.showMessageDialog(this, "Booking added successfully.");
                frame.showPanel("MainMenu");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Customer ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> frame.showPanel("MainMenu"));

        add(custNoLabel);
        add(custNoField);
        add(dayLabel);
        add(dayComboBox);
        add(timeLabel);
        add(timeComboBox);
        add(countryLabel);
        add(countryComboBox);
        add(cityLabel);
        add(cityComboBox);
        add(addButton);
        add(cancelButton);
    }
}


/**
 * Panel for listing bookings in the Flight Reservation System GUI.
 */
class ListBookingsPanel extends JPanel {
    private final JList<Booking> bookingList;
    private final FlightReservationFrame frame;

    public ListBookingsPanel(FlightReservationFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        bookingList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(bookingList);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.showPanel("MainMenu"));

        JButton deleteBookingButton = new JButton("Delete Booking");
        deleteBookingButton.addActionListener(e -> {
            Booking selectedBooking = bookingList.getSelectedValue();
            if (selectedBooking != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the following booking?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        db.deleteBooking(selectedBooking.getId());
                    } catch (SQLException ex) {
                        frame.dataCache.removeBooking(selectedBooking.getId());
                    }
                    updateBookingList();
                    JOptionPane.showMessageDialog(this, "Booking deleted successfully.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No booking selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(deleteBookingButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateBookingList() {
        ArrayList<Booking> bookings = frame.getBookings();
        Booking[] bookingArray = new Booking[bookings.size()];
        for (int i = 0; i < bookings.size(); i++) {
            bookingArray[i] = bookings.get(i);
        }
        bookingList.setListData(bookingArray);
    }
}
