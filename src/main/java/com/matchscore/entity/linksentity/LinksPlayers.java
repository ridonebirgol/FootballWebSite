package com.matchscore.entity.linksentity;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class LinksPlayers {
    private HrefBase _self;
    private HrefBase team;

    public HrefBase get_self() {
        return _self;
    }

    public void set_self(HrefBase _self) {
        this._self = _self;
    }

    public HrefBase getTeam() {
        return team;
    }

    public void setTeam(HrefBase team) {
        this.team = team;
    }
}
