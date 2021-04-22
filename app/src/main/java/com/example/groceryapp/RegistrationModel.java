package com.example.groceryapp;

public class RegistrationModel {

    private int id;
    private String username;
    private String emailAddress;
    private String password;

    public RegistrationModel(int id, String username, String emailAddress, String password){
        this.id=id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public RegistrationModel(){

    }

    @Override
    public String toString() {
        return "RegistrationModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
