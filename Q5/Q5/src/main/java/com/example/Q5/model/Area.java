package com.example.Q5.model;
import jakarta.persistence.*;

@Entity(name = "area")
public class Area{
    @Id
    private Integer area;
    @Column(name = "area_name")
    private String areaName;

    public Area(){}

    public Area(Integer area, String areaName) {
        this.area = area;
        this.areaName = areaName;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}