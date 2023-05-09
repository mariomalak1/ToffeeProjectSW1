package Controllers;

import Models.Cart;
import Repositories.CartRepository;
import Repositories.CustomerRepository;
import java.util.List;
import Models.Customer;

public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController() {
        this.customerRepository = new CustomerRepository();
    }

    public void createCustomer(String name, String email, String password, String phoneNumber, boolean isAdmin) {
        try {
            Customer customer = new Customer(name, email, password, phoneNumber, isAdmin);
            customerRepository.addCustomer(customer);
        } catch (Exception e) {
            System.out.println("Failed to create customer: " + e.getMessage());
        }
    }

    public void updateCustomer(int id, String name, String email, String password, String phoneNumber, boolean isAdmin) {
        try {
            Customer customer = customerRepository.getCustomerById(id);

            if (customer != null) {
                customer.setName(name);
                customer.setEmail(email);
                customer.setPassword(password);
                customer.setPhoneNumber(phoneNumber);
                customer.setAdmin(isAdmin);

                customerRepository.updateCustomer(customer);
                System.out.println("Customer updated successfully. ID: " + customer.getID());
            } else {
                System.out.println("Customer not found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Failed to update customer: " + e.getMessage());
        }
    }

    public void deleteCustomer(int id) {
        try {
            Customer customer = customerRepository.getCustomerById(id);

            if (customer != null) {
                customerRepository.deleteCustomer(customer.getID());
                System.out.println("Customer deleted successfully. ID: " + id);
            } else {
                System.out.println("Customer not found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Failed to delete customer: " + e.getMessage());
        }
    }

    public Customer getCustomerByID(int id) {
        try {
            Customer customer = customerRepository.getCustomerById(id);
            List<Cart> allCartsForCustomer = new CartRepository().getCartsByCustomerID(customer.getID());
            customer.setCarts(allCartsForCustomer);
            return customer;
        } catch (Exception e) {
            System.out.println("Failed to get customer: " + e.getMessage());
            return null;
        }
    }

    public List<Customer> getAllCustomers() {
        try {
            return customerRepository.getAllCustomers();
        } catch (Exception e) {
            System.out.println("Failed to get customers: " + e.getMessage());
            return null;
        }
    }

    public Models.Cart getCurrentCartForCustomer(int customerID){
        Customer customer = getCustomerByID(customerID);
        return customer.getCurrentCart();
    }
}
