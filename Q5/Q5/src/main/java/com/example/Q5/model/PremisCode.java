package com.example.Q5.model;
import jakarta.persistence.*;

@Entity(name = "premis_code")
public class PremisCode{
    public PremisCode(Integer premisCd, String premisDesc) {
        this.premisCd = premisCd;
        this.premisDesc = premisDesc;
    }

    public Integer getPremisCd(){
        return this.premisCd;
    }

    public String getPremisDesc(){
        return this.premisDesc;
    }

    public PremisCode(){

    }

    @Id
    private Integer premisCd;
    @Column(name = "premis_desc")
    private String premisDesc;

}