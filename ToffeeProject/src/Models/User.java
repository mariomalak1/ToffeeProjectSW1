package Models;

public class User {
    private String Name;
    private String Email;
    private String Password;
    private boolean Active;
    private boolean IsAdmin;

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
}
