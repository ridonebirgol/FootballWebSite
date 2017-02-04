/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.entity;

/**
 *
 * @author RIDVAN
 */
public class TeamsRet {

    private Teams teamInfo;
    private String playersUrl;
    private String fixtureUrl;

    public Teams getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(Teams teamInfo) {
        this.teamInfo = teamInfo;
    }

    public String getFixtureUrl() {
        return fixtureUrl;
    }

    public void setFixtureUrl(String fixtureUrl) {
        this.fixtureUrl = fixtureUrl;
    }

    public String getPlayersUrl() {
        return playersUrl;
    }

    public void setPlayersUrl(String playersUrl) {
        this.playersUrl = playersUrl;
    }

}
