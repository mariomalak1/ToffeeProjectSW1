package Models;

import java.util.TreeMap;

public class User {
    protected int ID;
    protected String Name;
    protected String Email;
    protected String Password;
    protected String PhoneNumber;
    protected boolean Active = false;
    protected boolean IsAdmin;
    private static int LastID = 0;

    public User(String name, String email, String password, String phoneNumber, boolean admin){
        LastID++;
        ID = LastID;
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

    public int getID() {
        return ID;
    }

    public void makeActive(){
        Active = true;
    }
}

