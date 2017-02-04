package com.matchscore.entity.linksentity;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class LinksTeamInfo {
    private HrefBase self;
    private HrefBase fixtures;
    private HrefBase players;

    public HrefBase getSelf() {
        return self;
    }

    public void setSelf(HrefBase self) {
        this.self = self;
    }

    public HrefBase getFixtures() {
        return fixtures;
    }

    public void setFixtures(HrefBase fixtures) {
        this.fixtures = fixtures;
    }

    public HrefBase getPlayers() {
        return players;
    }

    public void setPlayers(HrefBase players) {
        this.players = players;
    }
}
