package com.example.Q5.model;
import jakarta.persistence.*;

@Entity(name = "crime_code")
public class CrimeCode{
    @Id
    private Integer crmCd;
    @Column(name = "crm_cd_desc")
    private String crmCdDesc;

    public CrimeCode(){}

    public CrimeCode(Integer crmCd, String crmCdDesc) {
        this.crmCd = crmCd;
        this.crmCdDesc = crmCdDesc;
    }

    public Integer getCrmCd() {
        return crmCd;
    }

    public void setCrmCd(Integer crmCd) {
        this.crmCd = crmCd;
    }

    public String getCrmCdDesc() {
        return crmCdDesc;
    }

    public void setCrmCdDesc(String crmCdDesc) {
        this.crmCdDesc = crmCdDesc;
    }

}