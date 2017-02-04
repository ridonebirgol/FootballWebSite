package com.matchscore.entity.linksentity;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class LinksFixtureInfo {
    private HrefBase self;
    private HrefBase soccerseason;
    private HrefBase homeTeam;
    private HrefBase awayTeam;

    public HrefBase getSelf() {
        return self;
    }

    public void setSelf(HrefBase self) {
        this.self = self;
    }

    public HrefBase getSoccerseason() {
        return soccerseason;
    }

    public void setSoccerseason(HrefBase soccerseason) {
        this.soccerseason = soccerseason;
    }

    public HrefBase getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HrefBase homeTeam) {
        this.homeTeam = homeTeam;
    }

    public HrefBase getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(HrefBase awayTeam) {
        this.awayTeam = awayTeam;
    }
}
