package Models;
public class User {
    protected String Name;
    protected String Email;
    protected String Password;
    protected String PhoneNumber;
    protected boolean Active = false;
    protected boolean IsAdmin;

    public User(String name, String email, String password, String phoneNumber, boolean admin){
        Name = name;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
        IsAdmin = admin;
    }

    public void setPassword(String password){
        // encrypt it by any encryption algorithm first then save it
        this.Password = password;
    }

    public String getPassword() {
        // return it with the encryption
        return Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isActive() {
        return Active;
    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin() {
        IsAdmin = true;
    }

    public void removeAdmin(){
        IsAdmin = false;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return this.PhoneNumber;
    }

}

