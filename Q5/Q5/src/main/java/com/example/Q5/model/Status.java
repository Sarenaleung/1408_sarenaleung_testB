package com.example.Q5.model;
import jakarta.persistence.*;


@Entity(name = "status")
public class Status{
    public Status(String status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public String getStatus(){
        return this.status;
    }

    public String getStatusDesc(){
        return this.statusDesc;
    }

    public Status(){}

    @Id
    private String status;
    @Column(name = "status_desc")
    private String statusDesc;

}