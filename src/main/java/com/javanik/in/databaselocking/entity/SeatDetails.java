package com.javanik.in.databaselocking.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
public class SeatDetails {
    public SeatDetails() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     private String movieName;

     private boolean booked;

    public SeatDetails(String movieName, Long id, Long customVersion, boolean booked) {
        this.movieName = movieName;
        this.id = id;
        this.version = customVersion;
        this.booked = booked;
    }

    private Long version = 0l;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long customVersion) {
        this.version = customVersion;
    }
}
