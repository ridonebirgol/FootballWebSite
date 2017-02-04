package com.matchscore.entity;

import com.matchscore.entity.linksentity.LinksFixtureInfo;

import java.util.Date;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class FixtureInfo {
    private LinksFixtureInfo _links;
    private Date date;
    private String status;
    private int matchday;
    private String homeTeamName;
    private String awayTeamName;
    private MatchResult result;

    public LinksFixtureInfo get_links() {
        return _links;
    }

    public void set_links(LinksFixtureInfo _links) {
        this._links = _links;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }
}
