/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.webservice;

import com.football.site.db.DbHelper;
import com.football.site.entity.FixturesRet;
import com.football.site.entity.LeagueTableRet;
import com.football.site.entity.LeaguesCountryRet;
import com.football.site.entity.LeaguesRet;
import com.football.site.entity.Players;
import com.football.site.entity.Teams;
import com.football.site.entity.TeamsRet;
import com.football.site.getdata.FootballGetDataHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.matchscore.entity.Constants;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author RIDVAN
 */
@Path("footballservice")
public class footballservice {

    /**
     * Creates a new instance of footballservice
     */
    public footballservice() {
    }

    /**
     * Retrieves representation of an instance of
     * com.football.site.webservice.footballservice
     *
     * @param userToken
     * @param year
     * @return an instance of javax.ws.rs.core.Response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("GetLeagueList/{year}")
    public Response GetLeagueList(@HeaderParam("UserToken") String userToken, @PathParam("year") String year) {
        //if (DbHelper.IsTokenExists(userToken)) {
            if (year.isEmpty()) {
                year = Constants.CurrentYear;
            }
            ArrayList<LeaguesRet> ligListe = DbHelper.GetLeagueList(year);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<LeaguesRet>>() {
            }.getType();
            String retValue = gson.toJson(ligListe, listType);
            return Response.status(Response.Status.OK).entity(retValue).build();
//        } else {
//            return Response.status(Response.Status.UNAUTHORIZED).entity("TokenNotAuthenticated").build();
//        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("GetFixtureInfo/{leagueId}")
    public Response GetFixtureInfo(@HeaderParam("UserToken") String userToken, @PathParam("leagueId") int leagueId) {
        if (DbHelper.IsTokenExists(userToken)) {
            ArrayList<FixturesRet> fixtureList = DbHelper.GetLeagueFixture(leagueId);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<FixturesRet>>() {
            }.getType();
            String retValue = gson.toJson(fixtureList, listType);
            return Response.status(Response.Status.OK).entity(retValue).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("TokenNotAuthenticated").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("GetTeamFixtureInfo/{teamId}")
    public Response GetTeamFixtureInfo(@HeaderParam("UserToken") String userToken, @PathParam("teamId") int teamId) {
        if (DbHelper.IsTokenExists(userToken)) {
            ArrayList<FixturesRet> fixtureList = DbHelper.GetTeamFixture(teamId);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<FixturesRet>>() {
            }.getType();
            String retValue = gson.toJson(fixtureList, listType);
            return Response.status(Response.Status.OK).entity(retValue).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("TokenNotAuthenticated").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("GetLeagueTable/{leagueId}")
    public Response GetLeagueTable(@HeaderParam("UserToken") String userToken, @PathParam("leagueId") int leagueId) {
        if (DbHelper.IsTokenExists(userToken)) {
            LeagueTableRet leagueTable = DbHelper.GetLeagueTable(leagueId);
            Gson gson = new Gson();
            String retValue = gson.toJson(leagueTable, LeagueTableRet.class);
            return Response.status(Response.Status.OK).entity(retValue).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("TokenNotAuthenticated").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("GetLeagueTeams/{leagueId}")
    public Response GetLeagueTeams(@HeaderParam("UserToken") String userToken, @PathParam("leagueId") int leagueId) {
        if (DbHelper.IsTokenExists(userToken)) {
            ArrayList<TeamsRet> fixtureList = DbHelper.GetLeagueTeams(leagueId);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Teams>>() {
            }.getType();
            String retValue = gson.toJson(fixtureList, listType);
            return Response.status(Response.Status.OK).entity(retValue).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("TokenNotAuthenticated").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("GetCountryList")
    public Response GetCountryList(@HeaderParam("UserToken") String userToken) {
        //if (DbHelper.IsTokenExists(userToken)) {
        ArrayList<LeaguesCountryRet> countryList = DbHelper.GetCountryList();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<LeaguesCountryRet>>() {
        }.getType();
        String retValue = gson.toJson(countryList, listType);
        return Response.status(Response.Status.OK).entity(retValue).build();
//        } else {
//            return Response.status(Response.Status.UNAUTHORIZED).entity("TokenNotAuthenticated").build();
//        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("GetTeamPlayers/{teamId}")
    public Response GetTeamPlayers(@HeaderParam("UserToken") String userToken, @PathParam("teamId") int leagueId) {
        if (DbHelper.IsTokenExists(userToken)) {
            ArrayList<Players> fixtureList = DbHelper.GetTeamPlayers(leagueId);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Type listType = new TypeToken<ArrayList<Players>>() {
            }.getType();
            String retValue = gson.toJson(fixtureList, listType);
            return Response.status(Response.Status.OK).entity(retValue).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("TokenNotAuthenticated").build();
        }
    }

    @GET
    @Path("GetDataAndInsert")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response GetDataAndInsert() {
        FootballGetDataHelper.GetAndInsertUrlData();
        return Response.status(Response.Status.OK).entity("Successfull").build();
    }

}
