package com.example.Q5.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "crime_record")
public class CrimeRecord {
    @Id
    private Integer drNo;

    @Column(name = "date_rptd")
    private LocalDateTime dateRptd;

    @Column(name = "date_occ")
    private LocalDateTime dateOcc;

    @Column(name = "time_occ")
    private Integer timeOcc;

    @ManyToOne
    @JoinColumn(name = "area_code", referencedColumnName = "area")
    private Area areaCd;

    @Column(name = "rpt_dist_no")
    private Integer rptDistNo;

    @Column(name = "part1or2")
    private Integer part1Or2;

    @ManyToOne
    @JoinColumn(name = "crm_code", referencedColumnName = "crmCd")
    private CrimeCode crimeCode;

    @Column(name = "Mocodes")
    private String mocodes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "victim_id", referencedColumnName = "id")
    private Victim victim;

    @ManyToOne
    @JoinColumn(name = "premis_cd", referencedColumnName = "premisCd")
    private PremisCode premisCode;

    @ManyToOne
    @JoinColumn(name = "weapon_used_cd", referencedColumnName = "weaponCd")
    private Weapon weapon;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "status")
    private Status status;

    @Column(name = "crm_cd1")
    private Integer crmCd1;

    @Column(name = "crm_cd2")
    private Integer crmCd2;

    @Column(name = "crm_cd3")
    private Integer crmCd3;

    @Column(name = "crm_cd4")
    private Integer crmCd4;

    @Column(name = "location")
    private String location;

    @Column(name = "cross_street")
    private String crossStreet;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lon")
    private Float lon;

    @Column(name = "area")
    private Float area;

    public Integer getDrNo() {
        return drNo;
    }

    public void setDrNo(Integer drNo) {
        this.drNo = drNo;
    }

    public LocalDateTime getDateRptd() {
        return dateRptd;
    }

    public void setDateRptd(LocalDateTime dateRptd) {
        this.dateRptd = dateRptd;
    }

    public LocalDateTime getDateOcc() {
        return dateOcc;
    }

    public void setDateOcc(LocalDateTime dateOcc) {
        this.dateOcc = dateOcc;
    }

    public Integer getTimeOcc() {
        return timeOcc;
    }

    public void setTimeOcc(Integer timeOcc) {
        this.timeOcc = timeOcc;
    }

    public Area getAreaCd() {
        return areaCd;
    }

    public void setAreaCd(Area areaCd) {
        this.areaCd = areaCd;
    }

    public Integer getRptDistNo() {
        return rptDistNo;
    }

    public void setRptDistNo(Integer rptDistNo) {
        this.rptDistNo = rptDistNo;
    }

    public Integer getPart1Or2() {
        return part1Or2;
    }

    public void setPart1Or2(Integer part1Or2) {
        this.part1Or2 = part1Or2;
    }

    public CrimeCode getCrimeCode() {
        return crimeCode;
    }

    public void setCrimeCode(CrimeCode crimeCode) {
        this.crimeCode = crimeCode;
    }

    public String getMocodes() {
        return mocodes;
    }

    public void setMocodes(String mocodes) {
        this.mocodes = mocodes;
    }

    public Victim getVictim() {
        return victim;
    }

    public void setVictim(Victim victim) {
        this.victim = victim;
    }

    public PremisCode getPremisCode() {
        return premisCode;
    }

    public void setPremisCode(PremisCode premisCode) {
        this.premisCode = premisCode;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCrmCd1() {
        return crmCd1;
    }

    public void setCrmCd1(Integer crmCd1) {
        this.crmCd1 = crmCd1;
    }

    public Integer getCrmCd2() {
        return crmCd2;
    }

    public void setCrmCd2(Integer crmCd2) {
        this.crmCd2 = crmCd2;
    }

    public Integer getCrmCd3() {
        return crmCd3;
    }

    public void setCrmCd3(Integer crmCd3) {
        this.crmCd3 = crmCd3;
    }

    public Integer getCrmCd4() {
        return crmCd4;
    }

    public void setCrmCd4(Integer crmCd4) {
        this.crmCd4 = crmCd4;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float float1) {
        this.lat = float1;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }
}