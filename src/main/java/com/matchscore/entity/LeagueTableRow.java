package com.matchscore.entity;

import com.matchscore.entity.linksentity.LinksLeagueTableRow;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class LeagueTableRow {
    private LinksLeagueTableRow _links;
    private String position;
    private String teamName;
    private int playedGames;
    private int points;
    private int goals;
    private int goalsAgainst;
    private int goalDifference;
    private int wins;
    private int draws;
    private int losses;
    private String crestURI;
    private TeamStatisticForSeason home;
    private TeamStatisticForSeason away;

    public LinksLeagueTableRow getLinks() {
        return _links;
    }

    public void setLinks(LinksLeagueTableRow _links) {
        this._links = _links;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public void setCrestURI(String crestURI) {
        this.crestURI = crestURI;
    }

    
    public LinksLeagueTableRow get_links() {
        return _links;
    }

    public void set_links(LinksLeagueTableRow _links) {
        this._links = _links;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public TeamStatisticForSeason getHome() {
        return home;
    }

    public void setHome(TeamStatisticForSeason home) {
        this.home = home;
    }

    public TeamStatisticForSeason getAway() {
        return away;
    }

    public void setAway(TeamStatisticForSeason away) {
        this.away = away;
    }
}
