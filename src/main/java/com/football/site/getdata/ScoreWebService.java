/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.getdata;

import com.football.site.helpers.HelperUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.matchscore.entity.Constants;
import com.matchscore.entity.LeagueInfo;
import com.matchscore.entity.LeagueKey;
import com.matchscore.entity.linksentity.LinksFixtures;
import com.matchscore.entity.linksentity.LinksLeagueTeams;
import com.matchscore.objects.Fixtures;
import com.matchscore.objects.LeagueTable;
import com.matchscore.objects.LeagueTeams;
import com.matchscore.objects.Leagues;
import com.matchscore.objects.Players;
import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Created by RIDVAN on 02.11.2015.
 */
public class ScoreWebService {

    private static final Logger logger = Logger.getLogger(ScoreWebService.class);

    private static String GetHttpClientResponse(String url) {
        String responseText = "";
        try {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(url);
                httpGet.addHeader("X-Auth-Token", Constants.XAuthToken);
                httpGet.addHeader("Content-type", "application/json");
                CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
                responseText = EntityUtils.toString(httpResponse.getEntity(), "ISO-8859-1");
                //logger.info(String.format("%s - %s", url, responseText));
            }
        } catch (IOException | ParseException e) {
            HelperUtil.AddErrorLog(logger, e);
            HelperUtil.AddErrorLogWithString(logger, url);
        }
        return responseText;
    }

    public static Leagues GetLeagueList() {
        ArrayList<LeagueInfo> izlenecekLigler = new ArrayList<LeagueInfo>();
        Leagues ligler = ScoreWebService.GetLeagueListFromWebService();
        LeagueInfo in = null;
        if (ligler != null) {
            for (Iterator<LeagueInfo> i = ligler.getLeagueList().iterator(); i.hasNext();) {
                in = i.next();
                if (!GetValidLeaguesList().contains(in.getLeague())) {
                    izlenecekLigler.add(in);
                }
            }
            ligler.setLeagueList(izlenecekLigler);
        }
        return ligler;
    }

    private static List<String> GetValidLeaguesList() {
        List<String> valigLeagues = new ArrayList<String>();
        /*
        valigLeagues.add(LeagueKey.BL1);
        valigLeagues.add(LeagueKey.DED);
        valigLeagues.add(LeagueKey.FL1);
        valigLeagues.add(LeagueKey.PL);
        valigLeagues.add(LeagueKey.PPL);
        valigLeagues.add(LeagueKey.SA);
        */
        valigLeagues.add(LeagueKey.EC);
        valigLeagues.add(LeagueKey.EL);
        valigLeagues.add(LeagueKey.WC);
        valigLeagues.add(LeagueKey.CL);
        return valigLeagues;
    }

    private static Leagues GetLeagueListFromWebService() {
        Leagues result = null;
        try {
            JsonParser parser = new JsonParser();
            ArrayList<LeagueInfo> ligListe = new ArrayList<LeagueInfo>();
            String response = GetHttpClientResponse("http://api.football-data.org/v1/soccerseasons"/*"http://www.football-data.org/alpha/soccerseasons"*/);
            JsonArray array = parser.parse(response).getAsJsonArray();
            LeagueInfo item = null;
            Gson gSon = new Gson();
            for (int i = 0; i < array.size(); i++) {
                item = gSon.fromJson(array.get(i).getAsJsonObject().toString(), LeagueInfo.class);
                ligListe.add(item);
            }
            result = new Leagues();
            result.setLeagueList(ligListe);

        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        }
        return result;
    }

    public static LeagueTeams GetLeagueTeams(String url) {
        LeagueTeams result = null;
        try {
            String response = GetHttpClientResponse(url);
            result = GetLeagueTeamsFromJson(response);
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    private static LeagueTeams GetLeagueTeamsFromJson(String data) {
        LeagueTeams ligTeams = null;
        try {
            JsonParser parser = new JsonParser();
            Gson gson = new Gson();
            ligTeams = gson.fromJson(data, LeagueTeams.class);
            ligTeams.set_links(new LinksLeagueTeams());
            JsonObject obj = parser.parse(data).getAsJsonObject();
            JsonObject linkler = obj.getAsJsonObject("_links");
            ligTeams.get_links().setSelf(linkler.getAsJsonObject("self").get("href").getAsString());
            ligTeams.get_links().setSoccerseason(linkler.getAsJsonObject("soccerseason").get("href").getAsString());
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        }
        return ligTeams;
    }

    public static Fixtures GetFixture(String url) {
        Fixtures result = null;
        try {
            JsonParser parser = new JsonParser();
            String response = GetHttpClientResponse(url);
            Gson gson = new Gson();
            result = gson.fromJson(response, Fixtures.class);
            LinksFixtures lf = new LinksFixtures();
            JsonObject obj = parser.parse(response).getAsJsonObject();
            JsonObject linkler = obj.getAsJsonObject("_links");
            lf.setSelf(linkler.getAsJsonObject("self").get("href").getAsString());
            if (linkler.has("soccerseason")) {
                lf.setSoccerseason(linkler.getAsJsonObject("soccerseason").get("href").getAsString());
            }
            if (linkler.has("team")) {
                lf.setTeam(linkler.getAsJsonObject("team").get("href").getAsString());
            }
            result.setLinks(lf);
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        }
        return result;
    }

    public static Players GetPlayers(String url) {
        Players result = null;
        try {
            String response = GetHttpClientResponse(url);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            result = gson.fromJson(response, Players.class);
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        }
        return result;
    }

    public static LeagueTable GetLeagueTable(String url) {
        LeagueTable result = null;
        try {
            String response = GetHttpClientResponse(url);
            Gson gson = new Gson();
            result = gson.fromJson(response, LeagueTable.class);
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        }
        return result;
    }

    public static String GetUrlResponse(String url) {
        String result = "";
        try {
            result = GetHttpClientResponse(url);
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        }
        return result;
    }
}
