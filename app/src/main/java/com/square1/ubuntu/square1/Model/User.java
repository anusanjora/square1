package com.square1.ubuntu.square1.Model;

/**
 * Created by ubuntu on 2/2/18.
 */

public class User {
    private String Name;
    private String Passward;

    public User() {
    }

    public User(String name, String passward) {
        Name = name;
        Passward = passward;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassward() {
        return Passward;
    }

    public void setPassward(String passward) {
        Passward = passward;
    }
}
