package com.example.TCSwim.domain;

public enum UserRoles {

    HEADCOACH("headcoach"),
    COACH("coach"),
    ATHLETE("athlete");

    private final String role;

    UserRoles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
