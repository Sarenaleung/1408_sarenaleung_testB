package com.example.Q5.model;
import jakarta.persistence.*;


@Entity(name = "weapon")
public class Weapon{
    public Weapon(Integer weaponUsedCd, String weaponDesc) {
        this.weaponCd = weaponUsedCd;
        this.weaponDesc = weaponDesc;
    }

    public Integer getWeaponCd(){
        return this.weaponCd;
    }

    public String getWeaponDesc(){
        return this.weaponDesc;
    }

    public Weapon(){}

    @Id
    private Integer weaponCd;
    @Column(name = "weapon_desc")
    private String weaponDesc;

}