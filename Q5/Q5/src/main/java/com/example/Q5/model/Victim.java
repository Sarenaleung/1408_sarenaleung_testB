package com.example.Q5.model;
import jakarta.persistence.*;

@Entity(name = "victim")
public class Victim{
    public Victim(Integer victimAge, String victimSex, String victimDescent) {
        this.age = victimAge;
        this.sex = victimSex;
        this.descent = victimDescent;
    }

    public Victim(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "descent")
    private String descent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescent() {
        return descent;
    }

    public void setDescent(String descent) {
        this.descent = descent;
    }

}