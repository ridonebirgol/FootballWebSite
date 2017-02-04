/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author RIDVAN
 */
public class LeaguesRet implements Serializable {

    private Integer recordId;
    private String caption;
    private String league;
    private Integer year;
    private Integer numberOfTeams;
    private Integer numberOfGames;
    private String selfLink;
    private Date lastUpdated;
    private String teamsLink;
    private String fixturesLink;
    private String leagueTableLink;
    private Integer countryId;
    private Integer orderNo;
    private byte[] leagueIcon;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public byte[] getLeagueIcon() {
        return leagueIcon;
    }

    public void setLeagueIcon(byte[] leagueIcon) {
        this.leagueIcon = leagueIcon;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(Integer numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getTeamsLink() {
        return teamsLink;
    }

    public void setTeamsLink(String teamsLink) {
        this.teamsLink = teamsLink;
    }

    public String getFixturesLink() {
        return fixturesLink;
    }

    public void setFixturesLink(String fixturesLink) {
        this.fixturesLink = fixturesLink;
    }

    public String getLeagueTableLink() {
        return leagueTableLink;
    }

    public void setLeagueTableLink(String leagueTableLink) {
        this.leagueTableLink = leagueTableLink;
    }

}
