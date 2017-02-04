package com.matchscore.entity;

import java.util.Date;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class PlayerInfo {
    
    private String name;
    private String position;
    private int jerseyNumber;
    private Date dateOfBirth;
    private String nationality;
    private Date contractUntil;
    private String marketValue;
    private double marketValueNumber;
    private String marketValueMoneyType;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public int getJerseyNumber() {
        return jerseyNumber;
    }
    
    public void setJerseyNumber(int jerseryNumber) {
        this.jerseyNumber = jerseryNumber;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public Date getContractUntil() {
        return contractUntil;
    }
    
    public void setContractUntil(Date contractUntil) {
        this.contractUntil = contractUntil;
    }
    
    public String getMarketValue() {
        return marketValue;
    }
    
    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }
    
    public double getMarketValueNumber() {
        marketValueNumber = 0;
        try {
            marketValueNumber = Double.valueOf(this.marketValue.replace(",", "").split(" ")[0]);
        } catch (Exception e) {
        }
        return marketValueNumber;
    }
    
    public String getMarketValueMoneyType() {
        marketValueMoneyType = "";
        try {
            marketValueMoneyType = this.marketValue.split(" ")[1];
        } catch (Exception e) {
        }
        return marketValueMoneyType;
    }
}
