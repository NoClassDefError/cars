package org.cie.entities;

public class User {
    private String userName, passwrd;

    public User(String userName, char[] password) {
        this.userName = userName;
        this.passwrd = String.valueOf(password);
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.passwrd = password;
    }

    public User() {
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
