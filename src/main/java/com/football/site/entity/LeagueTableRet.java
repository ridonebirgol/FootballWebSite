/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author RIDVAN
 */
public class LeagueTableRet implements Serializable {

    private Leaguetable leagueTable;
    private ArrayList<LeagueTableRowsRet> rowsList;
    private ArrayList<LeagueTableRowsRet> rowsListHome;
    private ArrayList<LeagueTableRowsRet> rowsListAway;

    public Leaguetable getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(Leaguetable leagueTable) {
        this.leagueTable = leagueTable;
    }

    public ArrayList<LeagueTableRowsRet> getRowsList() {
        return rowsList;
    }

    public void setRowsList(ArrayList<LeagueTableRowsRet> rowsList) {
        this.rowsList = rowsList;
    }

    public ArrayList<LeagueTableRowsRet> getRowsListHome() {
        return rowsListHome;
    }

    public void setRowsListHome(ArrayList<LeagueTableRowsRet> rowsListHome) {
        this.rowsListHome = rowsListHome;
    }

    public ArrayList<LeagueTableRowsRet> getRowsListAway() {
        return rowsListAway;
    }

    public void setRowsListAway(ArrayList<LeagueTableRowsRet> rowsListAway) {
        this.rowsListAway = rowsListAway;
    }

}
