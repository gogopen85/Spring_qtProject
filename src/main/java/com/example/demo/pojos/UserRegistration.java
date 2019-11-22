package com.example.demo.pojos;

public class UserRegistration {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String name;

    public UserRegistration() {
    }

    public UserRegistration(String username, String password, String passwordConfirmation, String name) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
