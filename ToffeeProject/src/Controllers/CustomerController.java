package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import Repositories.CustomerRepository;
import Repositories.DatabaseInitializer;
import Models.Customer;

public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController() {
        this.customerRepository = new CustomerRepository();
    }

    public boolean createCustomer(String name, String email, String password, String phone, boolean admin) {
        try {
            Customer customer = new Customer(name, email, password ,phone, admin);
            customerRepository.addCustomer(customer);
            System.out.println("Customer created successfully. ID: " + customer.getID());
            return true;
        } catch (Exception e) {
            System.out.println("Failed to create customer: " + e.getMessage());
        }
        return false;
    }

    public void getCustomerById(int customerId) {
        try {
            Customer customer = customerRepository.getCustomerById(customerId);
            if (customer != null) {
                System.out.println("Customer ID: " + customer.getID());
                System.out.println("Name: " + customer.getName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("Phone: " + customer.getPhoneNumber());
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve customer: " + e.getMessage());
        }
    }

    public void updateCustomer(int customerId, String newName, String newEmail, String newPhone) {
        try {
            Customer customer = customerRepository.getCustomerById(customerId);
            if (customer != null) {
                customer.setName(newName);
                customer.setEmail(newEmail);
                customer.setPhoneNumber(newPhone);
                customerRepository.updateCustomer(customer);
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to update customer: " + e.getMessage());
        }
    }

    public void deleteCustomer(int customerId) {
        try {
            Customer customer = customerRepository.getCustomerById(customerId);
            if (customer != null) {
                customerRepository.deleteCustomer(customer);
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to delete customer: " + e.getMessage());
        }
    }

    public void getAllCustomers() {
        try {
            List<Customer> customers = customerRepository.getAllCustomers();
            if (!customers.isEmpty()) {
                System.out.println("All Customers:");
                for (Customer customer : customers) {
                    System.out.println("ID: " + customer.getID());
                    System.out.println("Name: " + customer.getName());
                    System.out.println("Email: " + customer.getEmail());
                    System.out.println("Phone: " + customer.getPhoneNumber());
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("No customers found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to get customers: " + e.getMessage());
        }
    }
}
