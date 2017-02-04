/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.entity;

import java.io.Serializable;

/**
 *
 * @author RIDVAN
 */
public class LeagueTableRowsRet implements Serializable {

    private LeagueTableRows row;
    private LeagueTableTeamStatistics homeInfo;
    private LeagueTableTeamStatistics awayInfo;

    public LeagueTableRows getRow() {
        return row;
    }

    public void setRow(LeagueTableRows row) {
        this.row = row;
    }

    public LeagueTableTeamStatistics getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(LeagueTableTeamStatistics homeInfo) {
        this.homeInfo = homeInfo;
    }

    public LeagueTableTeamStatistics getAwayInfo() {
        return awayInfo;
    }

    public void setAwayInfo(LeagueTableTeamStatistics awayInfo) {
        this.awayInfo = awayInfo;
    }

}
