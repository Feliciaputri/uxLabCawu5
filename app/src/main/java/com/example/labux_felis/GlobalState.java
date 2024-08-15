package com.example.labux_felis;
public class GlobalState {
    private static GlobalState instance;
    private String username;

    private GlobalState() {

    }

    public static synchronized GlobalState getInstance() {
        if (instance == null) {
            instance = new GlobalState();
        }
        return instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
