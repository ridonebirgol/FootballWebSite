/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.getdata;

import com.football.site.db.DbHelper;
import com.football.site.entity.FixturesMatchResult;
import com.football.site.entity.FixturesTeamMatchResult;
import com.football.site.entity.LeagueTableRows;
import com.football.site.entity.LeagueTableTeamStatistics;
import com.football.site.entity.Leaguetable;
import com.football.site.entity.Teams;
import com.football.site.helpers.HelperUtil;
import com.matchscore.entity.FixtureInfo;
import com.matchscore.entity.LeagueInfo;
import com.matchscore.entity.LeagueTableRow;
import com.matchscore.entity.PlayerInfo;
import com.matchscore.entity.TeamInfo;
import com.matchscore.objects.Fixtures;
import com.matchscore.objects.LeagueTable;
import com.matchscore.objects.LeagueTeams;
import com.matchscore.objects.Leagues;
import com.matchscore.objects.Players;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 *
 * @author RIDVAN
 */
public class FootballGetDataHelper {

    private static final Logger logger = Logger.getLogger(FootballGetDataHelper.class);

    public static void GetAndInsertUrlData() {
        Leagues ligler = null;
        try {
            ligler = ScoreWebService.GetLeagueList();
            if (ligler != null) {
                LeagueInfo ligInfo = null;
                com.football.site.entity.Leagues ligEntity = null;
                //ClearAllTable();
                for (Iterator<LeagueInfo> i = ligler.getLeagueList().iterator(); i.hasNext();) {
                    ligInfo = i.next();

                    if (IsLeagueUpdateNecessary(ligInfo)) {
                        Sleep(1500);

                        ligEntity = InsertOrUpdateLeagueInfo(ligInfo);

                        if (ligEntity != null) {
                            logger.error("lig tablo başlıyor");
                            GetAndInsertLeagueTable(ligEntity, ligInfo.getLinks().getLeagueTable().href);
                            Sleep(1500);
                            logger.error("lig fixture başlıyor");
                            GetAndInsertLeagueFixtures(ligEntity, ligInfo.getLinks().getFixtures().href);
                            Sleep(1500);
                            logger.error("lig takımlar başlıyor");
                            GetAndInsertTeams(ligEntity, ligInfo.getLinks().getTeams().href);
                        }
                    }
                }
            }
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        }
    }

    /**
     * Lig bilgilerinin güncellenmesi gerekiyor mu ?
     *
     * @param lig
     * @return
     */
    private static boolean IsLeagueUpdateNecessary(LeagueInfo lig) {
        boolean retVal = true;
        com.football.site.entity.Leagues dbLig = DbHelper.GetLeague(lig.getLeague(), lig.getYear());
        if (dbLig != null) {
            retVal = (lig.getLastUpdated().compareTo(dbLig.getLastUpdated()) != 0);
        }
        return retVal;
    }

    private static com.football.site.entity.Leagues InsertOrUpdateLeagueInfo(LeagueInfo ligInfo) {
        com.football.site.entity.Leagues ligEntity = DbHelper.GetLeague(ligInfo.getLeague(), ligInfo.getYear());
        if (ligEntity == null) {
            ligEntity = new com.football.site.entity.Leagues();
        }
        ligEntity.setCaption(ligInfo.getCaption());
        ligEntity.setLastUpdated(ligInfo.getLastUpdated());
        ligEntity.setLeague(ligInfo.getLeague());
        ligEntity.setNumberOfGames(ligInfo.getNumberOfGames());
        ligEntity.setNumberOfTeams(ligInfo.getNumberOfTeams());
        ligEntity.setSelfLink(ligInfo.getLinks().getSelf().href);
        ligEntity.setYear(Integer.valueOf(ligInfo.getYear()));
        if (!DbHelper.SaveLeagues(ligEntity)) {
            ligEntity = null;
        }
        return ligEntity;
    }

    private static void GetAndInsertLeagueTable(com.football.site.entity.Leagues ligEntity, String leagueTableUrl) {
        DbHelper.DeleteLeagueTables(ligEntity.getRecordId());
        LeagueTable ligTable = ScoreWebService.GetLeagueTable(leagueTableUrl);
        if (ligTable != null) {
            InsertLeagueTableInfo(ligEntity.getRecordId(), ligTable);
        }
    }

    private static void InsertLeagueTableInfo(int leagueId, LeagueTable ligTable) {
        Leaguetable tableEntity = new Leaguetable();
        tableEntity.setLeagueId(leagueId);
        tableEntity.setLeaguecaption(ligTable.getLeagueCaption());
        tableEntity.setMatchday(ligTable.getMatchday());
        if (DbHelper.SaveLeagueTable(tableEntity)) {
            LeagueTableRows row = null;
            LeagueTableTeamStatistics stHome = null;
            LeagueTableTeamStatistics stAway = null;
            LeagueTableRow item = null;
            for (Iterator<LeagueTableRow> i = ligTable.getStanding().iterator(); i.hasNext();) {
                item = i.next();
                row = new LeagueTableRows();
                row.setLeagueTableId(tableEntity.getRecordId());
                row.setDraws(item.getDraws());
                row.setGoals(item.getGoals());
                row.setGoalDifference(item.getGoalDifference());
                row.setGoalsAgainst(item.getGoalsAgainst());
                row.setLosses(item.getLosses());
                row.setPlayedGames(item.getPlayedGames());
                row.setPoints(item.getPoints());
                row.setPosition(item.getPosition());
                row.setTeamName(item.getTeamName());
                row.setWins(item.getWins());
                row.setCrestUrl(item.getCrestURI());
                row.setCrestPicture(HelperUtil.GetTeamPicture(logger, row.getCrestUrl(), 32, 32));
                stHome = new LeagueTableTeamStatistics();
                stHome.setStatisticOwn("HOME");
                stHome.setDraws(item.getHome().getDraws());
                stHome.setGoals(item.getHome().getGoals());
                stHome.setGoalsAgainst(item.getHome().getGoalsAgainst());
                stHome.setLosses(item.getHome().getLosses());
                stHome.setWins(item.getHome().getWins());
                stAway = new LeagueTableTeamStatistics();
                stAway.setStatisticOwn("AWAY");
                stAway.setDraws(item.getAway().getDraws());
                stAway.setGoals(item.getAway().getGoals());
                stAway.setGoalsAgainst(item.getAway().getGoalsAgainst());
                stAway.setLosses(item.getAway().getLosses());
                stAway.setWins(item.getAway().getWins());
                DbHelper.SaveLeagueTableRow(row, stHome, stAway);
            }
        }
    }

    private static void GetAndInsertLeagueFixtures(com.football.site.entity.Leagues ligEntity, String href) {
        DbHelper.DeleteLeagueFixtures(ligEntity.getRecordId());
        Fixtures fixtures = ScoreWebService.GetFixture(href);
        if (fixtures != null) {
            FixtureInfo item = null;
            for (Iterator<FixtureInfo> i = fixtures.getFixtures().iterator(); i.hasNext();) {
                item = i.next();
                InsertLeagueFixtures(item, ligEntity.getRecordId());
            }
        }
    }

    private static void InsertLeagueFixtures(FixtureInfo item, Integer recordId) {
        com.football.site.entity.Fixtures entity = new com.football.site.entity.Fixtures();
        entity.setAwayTeamName(item.getAwayTeamName());
        entity.setDate(item.getDate());
        entity.setHomeTeamName(item.getHomeTeamName());
        entity.setLegueId(recordId);
        entity.setMatchday(item.getMatchday());
        entity.setStatus(item.getStatus());
        FixturesMatchResult rEntity = new FixturesMatchResult();
        rEntity.setGoalsAwayTeam(item.getResult().getGoalsAwayTeam());
        rEntity.setGoalsHomeTeam(item.getResult().getGoalsHomeTeam());
        DbHelper.SaveFixtures(entity, rEntity);
    }

    private static void ClearAllTable() {
        DbHelper.TruncateAllTables();
    }

    private static void GetAndInsertTeams(com.football.site.entity.Leagues ligEntity, String href) {
        if (!DbHelper.IsLeagueTemasInsertBefore(ligEntity.getRecordId(),ligEntity.getYear())) {
            LeagueTeams teams = ScoreWebService.GetLeagueTeams(href);
            DbHelper.DeleteLeagueTeams(ligEntity.getRecordId());
            TeamInfo item = null;
            Teams team = null;
            for (Iterator<TeamInfo> i = teams.getTeams().iterator(); i.hasNext();) {
                try {
                    item = i.next();
                    team = new Teams();
                    team.setLeagueId(ligEntity.getRecordId());
                    team.setCode(item.getCode());
                    team.setCrestUrl(item.getCrestUrl());
                    team.setName(item.getName());
                    team.setShortName(item.getShortName());
                    team.setCrestPicture(HelperUtil.GetTeamPicture(logger, item.getCrestUrl(), 48, 48));
                    if (DbHelper.SaveTeams(team)) {
                        GetAndInsertPlayers(team.getRecordId(), item.get_links().getPlayers().href);
                        Sleep(1000);
                        GetAndInsertTeamFixtures(team.getRecordId(), item.get_links().getFixtures().href);
                    }
                } catch (Exception e) {
                    String err = HelperUtil.getStackTrace(e);
                    HelperUtil.AddErrorLogWithString(logger, String.format("%s:%s", "Save Team Error League Id:" + ligEntity.getRecordId().toString(), err));
                }
            }
            DbHelper.InsertLeagueTeamsInsertInfo(ligEntity.getRecordId(),ligEntity.getYear());
        }
    }

    private static void GetAndInsertTeamFixtures(Integer teamId, String href) {
        Fixtures fixtures = ScoreWebService.GetFixture(href);
        if (fixtures != null) {
            FixtureInfo item = null;
            for (Iterator<FixtureInfo> i = fixtures.getFixtures().iterator(); i.hasNext();) {
                item = i.next();
                InsertTeamFixtures(item, teamId);
            }
        }
    }

    private static void InsertTeamFixtures(FixtureInfo item, Integer teamId) {
        com.football.site.entity.FixturesTeam entity = new com.football.site.entity.FixturesTeam();
        entity.setAwayTeamName(item.getAwayTeamName());
        entity.setDate(item.getDate());
        entity.setHomeTeamName(item.getHomeTeamName());
        entity.setTeamId(teamId);
        entity.setMatchday(item.getMatchday());
        entity.setStatus(item.getStatus());
        FixturesTeamMatchResult rEntity = new FixturesTeamMatchResult();
        rEntity.setGoalsAwayTeam(item.getResult().getGoalsAwayTeam());
        rEntity.setGoalsHomeTeam(item.getResult().getGoalsHomeTeam());
        DbHelper.SaveTeamFixtures(entity, rEntity);
    }

    private static void GetAndInsertPlayers(Integer recordId, String href) {
        Players players = ScoreWebService.GetPlayers(href);
        PlayerInfo item = null;
        com.football.site.entity.Players p = null;
        for (Iterator<PlayerInfo> i = players.getPlayers().iterator(); i.hasNext();) {
            item = i.next();
            p = new com.football.site.entity.Players();
            try {
                p.setTeamsId(recordId);
                p.setContractUntil(item.getContractUntil());
                p.setDateOfBirth(item.getDateOfBirth());
                p.setJerseyNumber(item.getJerseyNumber());
                p.setMarketValue(item.getMarketValue());
                p.setMarketValueMoneyType(item.getMarketValueMoneyType());
                p.setMarketValueNumber(item.getMarketValueNumber());
                p.setName(item.getName());
                p.setNationality(item.getNationality());
                p.setPosition(item.getPosition());
                DbHelper.SavePlayer(p);
            } catch (Exception e) {
                String err = HelperUtil.getStackTrace(e);
                HelperUtil.AddErrorLogWithString(logger, String.format("%s:%s", "Save Player Error. Team Id:" + recordId.toString(), err));
            }
        }
    }

    private static void Sleep(int milisecond) {
        try {
            Thread.sleep(milisecond);
        } catch (InterruptedException ex) {
            HelperUtil.AddErrorLog(logger, ex);
        }
    }
}
