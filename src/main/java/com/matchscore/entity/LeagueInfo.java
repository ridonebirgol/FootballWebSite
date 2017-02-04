package com.matchscore.entity;

import com.matchscore.entity.linksentity.LinksLeagueInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RIDVAN on 07.11.2015.
 */
public class LeagueInfo implements Serializable {

    private LinksLeagueInfo _links;
    private String caption;
    private String league;
    private String year;
    private int numberOfTeams;
    private int numberOfGames;
    private Date lastUpdated;
    
    public LinksLeagueInfo getLinks() {
        return _links;
    }

    public void setLinks(LinksLeagueInfo _links) {
        this._links = _links;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
