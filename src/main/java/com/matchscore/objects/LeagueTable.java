package com.matchscore.objects;

import com.matchscore.entity.LeagueTableRow;
import com.matchscore.entity.linksentity.LinksLeagueTables;

import java.util.ArrayList;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class LeagueTable {
    private LinksLeagueTables _links;
    private String leagueCaption;
    private int matchday;
    private ArrayList<LeagueTableRow> standing;

    public LinksLeagueTables get_links() {
        return _links;
    }

    public void set_links(LinksLeagueTables _links) {
        this._links = _links;
    }

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public ArrayList<LeagueTableRow> getStanding() {
        return standing;
    }

    public void setStanding(ArrayList<LeagueTableRow> standing) {
        this.standing = standing;
    }
}
