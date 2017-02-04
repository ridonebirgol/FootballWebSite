package com.matchscore.entity.linksentity;

import java.io.Serializable;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class LinksLeagueInfo implements Serializable{
    private HrefBase self;
    private HrefBase teams;
    private HrefBase fixtures;
    private HrefBase leagueTable;

    public HrefBase getSelf() {
        return self;
    }

    public void setSelf(HrefBase self) {
        this.self = self;
    }

    public HrefBase getTeams() {
        return teams;
    }

    public void setTeams(HrefBase teams) {
        this.teams = teams;
    }

    public HrefBase getFixtures() {
        return fixtures;
    }

    public void setFixtures(HrefBase fixtures) {
        this.fixtures = fixtures;
    }

    public HrefBase getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(HrefBase leagueTable) {
        this.leagueTable = leagueTable;
    }
}
