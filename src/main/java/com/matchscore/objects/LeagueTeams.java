package com.matchscore.objects;

import com.matchscore.entity.linksentity.LinksLeagueTeams;
import com.matchscore.entity.TeamInfo;

import java.util.ArrayList;

/**
 * Created by RIDVAN on 07.11.2015.
 */


public class LeagueTeams {
    private LinksLeagueTeams links;
    private int count;
    private ArrayList<TeamInfo> teams;

    public LinksLeagueTeams get_links() {
        return links;
    }

    public void set_links(LinksLeagueTeams _links) {
        this.links = _links;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<TeamInfo> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<TeamInfo> teams) {
        this.teams = teams;
    }
}
