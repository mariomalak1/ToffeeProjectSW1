package Controllers;
import Models.User;
public class UserController {
    public User addUser(String name, String email, String password, String phone, boolean admin){
        return new User(name, email, password, phone, admin);
    }
}
