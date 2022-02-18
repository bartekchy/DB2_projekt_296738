package com.db.hotel.hotel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("guests")

public class Guest {

    @Id
    private String id;
    private String forename;
    private String surname;
    private String pesel;

    public Guest() {
    }

    public Guest(String forename, String surname, String pesel) {
        this.forename = forename;
        this.surname = surname;
        this.pesel = pesel;
    }

    public String getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}