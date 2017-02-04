package com.matchscore.objects;

import com.matchscore.entity.linksentity.LinksPlayers;
import com.matchscore.entity.PlayerInfo;

import java.util.ArrayList;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class Players {
    private LinksPlayers _links;
    private int count;
    private ArrayList<PlayerInfo> players;

    public LinksPlayers get_links() {
        return _links;
    }

    public void set_links(LinksPlayers _links) {
        this._links = _links;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<PlayerInfo> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerInfo> players) {
        this.players = players;
    }
}
