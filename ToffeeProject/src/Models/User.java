package Models;
public class User {
    protected String Name;
    protected String Email;
    protected String Password;
    protected String phoneNumber;
    protected boolean Active;
    protected boolean IsAdmin;

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
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

}

