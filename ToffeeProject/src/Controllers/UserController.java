package Controllers;

import Models.User;
import Repositories.UserRepository;
import java.sql.SQLException;

import java.util.List;

public class UserController {
    private final UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }

    public User createUser(String name, String email, String password, String phoneNumber, boolean admin) {
        try {
            User user = new User(name, email, password, phoneNumber, admin);
            userRepository.addUser(user);
            return user;
        } catch (SQLException e) {
            System.out.println("Failed to create user: " + e.getMessage());
        }
        return null;
    }

    public boolean deleteUser(int userID) {
        try {
            userRepository.deleteUser(userID);
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to delete user: " + e.getMessage());
        }
        return false;
    }

    public User updateUser(int userID, String name, String email, String password, String phoneNumber, boolean admin) {
        try {
            User user = userRepository.getUserById(userID);
            if (user != null) {
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhoneNumber(phoneNumber);
                if (admin) {
                    user.setAdmin(true);
                } else {
                    user.removeAdmin();
                }
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to update user: " + e.getMessage());
        }
        return null;
    }

    public void getAllUsers() {
        try {
            List<User> users = userRepository.getAllUsers();
            for (User user : users) {
                System.out.println("ID: " + user.getID() + ", Name: " + user.getName() + ", Email: " + user.getEmail() +
                        ", Phone: " + user.getPhoneNumber() + ", Admin: " + user.isAdmin());
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve users: " + e.getMessage());
        }
    }

    public User getUserById(int id){
        User user = null;
        try{
            user = userRepository.getUserById(id);
        }
        catch (NullPointerException | SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }
}
