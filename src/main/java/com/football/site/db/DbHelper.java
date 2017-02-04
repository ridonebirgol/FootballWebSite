/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.db;

import com.football.site.entity.Fixtures;
import com.football.site.entity.FixturesMatchResult;
import com.football.site.entity.FixturesRet;
import com.football.site.entity.FixturesTeam;
import com.football.site.entity.FixturesTeamMatchResult;
import com.football.site.entity.LeagueTableRet;
import com.football.site.entity.LeagueTableRows;
import com.football.site.entity.LeagueTableRowsRet;
import com.football.site.entity.LeagueTableTeamStatistics;
import com.football.site.entity.Leagues;
import com.football.site.entity.LeaguesCountry;
import com.football.site.entity.LeaguesCountryRet;
import com.football.site.entity.LeaguesRet;
import com.football.site.entity.Leaguetable;
import com.football.site.entity.Players;
import com.football.site.entity.Teams;
import com.football.site.entity.TeamsRet;
import com.football.site.entity.TokenInfo;
import com.football.site.entity.UserInfo;
import com.football.site.helpers.HelperUtil;
import com.matchscore.entity.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.ParameterMode;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;

/**
 *
 * @author RIDVAN
 */
public class DbHelper {

    private static final Logger logger = Logger.getLogger(DbHelper.class);

    public static boolean ExecuteScript(String sql) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            s.createSQLQuery(sql).executeUpdate();
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean TruncateAllTables() {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            s.createSQLQuery("truncate table fixtures_team").executeUpdate();
            s.createSQLQuery("truncate table fixtures_team_match_result").executeUpdate();
            s.createSQLQuery("truncate table fixtures").executeUpdate();
            s.createSQLQuery("truncate table fixtures_match_result").executeUpdate();
            s.createSQLQuery("truncate table league_table_rows").executeUpdate();
            s.createSQLQuery("truncate table league_table_team_statistics").executeUpdate();
            s.createSQLQuery("truncate table leagues").executeUpdate();
            s.createSQLQuery("truncate table leaguetable").executeUpdate();
            s.createSQLQuery("truncate table teams").executeUpdate();
            s.createSQLQuery("truncate table players").executeUpdate();
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;

    }

    public static Leagues GetLeague(String leagueKey, String year) {
        Leagues retLig = null;
        try {
            Session s = HibernateUtil.currentSession();
            Transaction t = s.beginTransaction();
            Criteria cr = s.createCriteria(Leagues.class);
            cr.add(Restrictions.eq("league", leagueKey));
            cr.add(Restrictions.eq("year", Integer.valueOf(year)));
            Object obj = cr.uniqueResult();
            retLig = (Leagues) obj;
            t.commit();
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }

        return retLig;
    }

    public static boolean SaveLeagues(Leagues league) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            if (league.getRecordId() != null && league.getRecordId() > 0) {
                s.update(league);
            } else {
                s.save(league);
            }
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean SaveTeams(Teams team) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            s.save(team);
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean SaveFixtures(Fixtures fixtures, FixturesMatchResult result) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            s.save(fixtures);
            result.setFixturesId(fixtures.getRecordId());
            s.save(result);
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean SaveTeamFixtures(FixturesTeam fixtures, FixturesTeamMatchResult result) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            s.save(fixtures);
            result.setFixturesId(fixtures.getRecordId());
            s.save(result);
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean DeleteLeagueFixtures(int leagueId) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            ProcedureCall pc = s.createStoredProcedureCall("delete_league_fixtures");
            pc.registerParameter("p_league_id", Integer.class, ParameterMode.IN).bindValue(leagueId);
            ProcedureOutputs po = pc.getOutputs();
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean DeleteLeagueTables(int leagueId) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            ProcedureCall pc = s.createStoredProcedureCall("delete_league_table");
            pc.registerParameter("p_league_id", Integer.class, ParameterMode.IN).bindValue(leagueId);
            ProcedureOutputs po = pc.getOutputs();
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean SaveLeagueTable(Leaguetable tableEntity) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            s.save(tableEntity);
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean SaveLeagueTableRow(LeagueTableRows row, LeagueTableTeamStatistics stHome, LeagueTableTeamStatistics stAway) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            s.save(row);
            stHome.setLeagueTableRowId(row.getRecordId());
            stAway.setLeagueTableRowId(row.getRecordId());
            s.save(stHome);
            s.save(stAway);
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean DeleteLeagueTeams(Integer leagueId) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            ProcedureCall pc = s.createStoredProcedureCall("delete_league_teams");
            pc.registerParameter("p_league_id", Integer.class, ParameterMode.IN).bindValue(leagueId);
            ProcedureOutputs po = pc.getOutputs();
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static boolean SavePlayer(Players p) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            s.save(p);
            tran.commit();
            retVal = true;
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static ArrayList<LeaguesRet> GetLeagueList(String year) {
        ArrayList<LeaguesRet> retList = new ArrayList<>();
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            int yy = Integer.valueOf(year);
            ArrayList<Leagues> rList = (ArrayList<Leagues>) s.createCriteria(Leagues.class).add(Restrictions.eq("year", yy)).list();
            LeaguesRet addItem = null;
            Leagues item = null;
            for (Iterator<Leagues> i = rList.iterator(); i.hasNext();) {
                item = i.next();
                addItem = new LeaguesRet();
                addItem.setRecordId(item.getRecordId());
                addItem.setCaption(item.getCaption());
                addItem.setLastUpdated(item.getLastUpdated());
                addItem.setLeague(item.getLeague());
                addItem.setNumberOfGames(item.getNumberOfGames());
                addItem.setNumberOfTeams(item.getNumberOfTeams());
                addItem.setSelfLink(item.getSelfLink());
                addItem.setYear(item.getYear());
                addItem.setTeamsLink(String.format("%s%s", Constants.UrlInfo, "GetLeagueTeams/" + item.getRecordId()));
                addItem.setFixturesLink(String.format("%s%s", Constants.UrlInfo, "GetFixtureInfo/" + item.getRecordId()));
                addItem.setLeagueTableLink(String.format("%s%s", Constants.UrlInfo, "GetLeagueTable/" + item.getRecordId()));
                addItem.setCountryId(item.getCountryId());
                addItem.setOrderNo(item.getOrderNo());
                addItem.setLeagueIcon(item.getLeagueIcon());
                retList.add(addItem);
            }
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
            tran.commit();
        }
        return retList;
    }

    public static ArrayList<LeaguesCountryRet> GetCountryList() {
        ArrayList<LeaguesCountryRet> retList = new ArrayList<>();
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            ArrayList<LeaguesCountry> rList = (ArrayList<LeaguesCountry>) s.createCriteria(LeaguesCountry.class).list();
            LeaguesCountryRet item = null;
            LeaguesCountry inItem = null;
            for (Iterator<LeaguesCountry> i = rList.iterator(); i.hasNext();) {
                inItem = i.next();
                item = new LeaguesCountryRet();
                item.setCountryCode(inItem.getCountryCode());
                item.setCountryFlag(inItem.getCountryFlag());
                item.setCountryName(inItem.getCountryName());
                item.setLeaguesUrl(String.format("%sGetLeagueList/%s/%s", Constants.UrlInfo, Constants.CurrentYear, inItem.getRecordId().toString()));
                item.setOrderNo(inItem.getOrderNo());
                item.setRecordId(inItem.getRecordId());
                retList.add(item);
            }
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
            tran.commit();
        }
        return retList;
    }

    public static ArrayList<FixturesRet> GetLeagueFixture(int leagueId) {
        ArrayList<FixturesRet> retList = new ArrayList<>();
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            SQLQuery q = s.createSQLQuery("SELECT * FROM fixtures_view WHERE legue_id=:leagueId");
            q.setParameter("leagueId", leagueId);
            List lst = q.list();
            FixturesRet item = null;
            Object[] objArr = null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            for (int i = 0; i < lst.size(); i++) {
                objArr = (Object[]) lst.get(i);
                item = new FixturesRet();
                item.setFixtureId(Integer.parseInt(objArr[0].toString()));
                item.setLeagueId(Integer.parseInt(objArr[1].toString()));
                item.setMatchDate(df.parse(objArr[2].toString()));
                item.setStatus(objArr[3].toString());
                item.setMatchday(Integer.parseInt(objArr[4].toString()));
                item.setHomeTeamName(objArr[5].toString());
                item.setAwayTeamName(objArr[6].toString());
                item.setHomeTeamGoal(Integer.parseInt(objArr[7].toString()));
                item.setAwayTeamGoal(Integer.parseInt(objArr[8].toString()));
                retList.add(item);
            }
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            tran.commit();
            HibernateUtil.closeSession();
        }
        return retList;
    }

    public static LeagueTableRet GetLeagueTable(int leagueId) {
        LeagueTableRet retList = new LeagueTableRet();
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            Criteria cr = s.createCriteria(Leaguetable.class);
            cr.add(Restrictions.eq("leagueId", leagueId));
            Leaguetable ligTable = (Leaguetable) cr.uniqueResult();

            cr = s.createCriteria(LeagueTableRows.class);
            cr.add(Restrictions.eq("leagueTableId", ligTable.getRecordId()));
            ArrayList<LeagueTableRows> dbRowList = (ArrayList<LeagueTableRows>) cr.list();

            ArrayList<LeagueTableRowsRet> rowList = new ArrayList<>();
            LeagueTableRowsRet item = null;
            LeagueTableRows dbItem = null;
            ArrayList<LeagueTableTeamStatistics> statisticList = null;
            for (Iterator<LeagueTableRows> i = dbRowList.iterator(); i.hasNext();) {
                dbItem = i.next();
                cr = s.createCriteria(LeagueTableTeamStatistics.class);
                cr.add(Restrictions.eq("leagueTableRowId", dbItem.getRecordId()));
                statisticList = (ArrayList<LeagueTableTeamStatistics>) cr.list();
                item = new LeagueTableRowsRet();
                item.setRow(dbItem);
                if (statisticList.size() > 0) {
                    if (statisticList.get(0).getStatisticOwn().equals("HOME")) {
                        item.setHomeInfo(statisticList.get(0));
                        item.setAwayInfo(statisticList.get(1));
                    } else {
                        item.setHomeInfo(statisticList.get(1));
                        item.setAwayInfo(statisticList.get(0));
                    }
                }
                rowList.add(item);
            }
            retList.setLeagueTable(ligTable);
            retList.setRowsList(rowList);

            SQLQuery q = s.createSQLQuery("SELECT * FROM league_table_home WHERE league_id=:leagueId");
            q.setParameter("leagueId", leagueId);
            List lst = q.list();
            Object[] objArr = null;
            ArrayList<LeagueTableRowsRet> rowListHome = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++) {
                objArr = (Object[]) lst.get(i);
                item = new LeagueTableRowsRet();
                dbItem = new LeagueTableRows();
                dbItem.setPosition(String.valueOf(i + 1));
                dbItem.setLeagueTableId(Integer.parseInt(objArr[0].toString()));
                dbItem.setTeamName(objArr[2].toString());
                dbItem.setPlayedGames(Integer.parseInt(objArr[3].toString()));
                dbItem.setGoals(Integer.parseInt(objArr[4].toString()));
                dbItem.setGoalsAgainst(Integer.parseInt(objArr[5].toString()));
                dbItem.setGoalDifference(Integer.parseInt(objArr[6].toString()));
                dbItem.setWins(Integer.parseInt(objArr[7].toString()));
                dbItem.setDraws(Integer.parseInt(objArr[8].toString()));
                dbItem.setLosses(Integer.parseInt(objArr[9].toString()));
                dbItem.setPoints(Integer.parseInt(objArr[10].toString()));
                dbItem.setCrestUrl(objArr[11].toString());
                dbItem.setCrestPicture((byte[]) objArr[12]);
                dbItem.setRecordId(0);
                item.setRow(dbItem);
                rowListHome.add(item);
            }
            retList.setRowsListHome(rowListHome);

            q = s.createSQLQuery("SELECT * FROM league_table_away WHERE league_id=:leagueId");
            q.setParameter("leagueId", leagueId);
            lst = q.list();
            ArrayList<LeagueTableRowsRet> rowListAway = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++) {
                objArr = (Object[]) lst.get(i);
                item = new LeagueTableRowsRet();
                dbItem = new LeagueTableRows();
                dbItem.setPosition(String.valueOf(i + 1));
                dbItem.setLeagueTableId(Integer.parseInt(objArr[0].toString()));
                dbItem.setTeamName(objArr[2].toString());
                dbItem.setPlayedGames(Integer.parseInt(objArr[3].toString()));
                dbItem.setGoals(Integer.parseInt(objArr[4].toString()));
                dbItem.setGoalsAgainst(Integer.parseInt(objArr[5].toString()));
                dbItem.setGoalDifference(Integer.parseInt(objArr[6].toString()));
                dbItem.setWins(Integer.parseInt(objArr[7].toString()));
                dbItem.setDraws(Integer.parseInt(objArr[8].toString()));
                dbItem.setLosses(Integer.parseInt(objArr[9].toString()));
                dbItem.setPoints(Integer.parseInt(objArr[10].toString()));
                dbItem.setCrestUrl(objArr[11].toString());
                dbItem.setCrestPicture((byte[]) objArr[12]);
                dbItem.setRecordId(0);
                item.setRow(dbItem);
                rowListAway.add(item);
            }
            retList.setRowsListAway(rowListAway);
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            tran.commit();
            HibernateUtil.closeSession();
        }
        return retList;
    }

    public static ArrayList<TeamsRet> GetLeagueTeams(int leagueId) {
        ArrayList<TeamsRet> retList = new ArrayList<>();
        Transaction tran = null;
        try {
            ArrayList<Teams> dbList = new ArrayList<>();
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            Criteria cr = s.createCriteria(Teams.class);
            cr.add(Restrictions.eq("leagueId", leagueId));
            dbList = (ArrayList<Teams>) cr.list();
            TeamsRet item = null;
            Teams tItem = null;
            for (Iterator<Teams> i = dbList.iterator(); i.hasNext();) {
                item = new TeamsRet();
                tItem = i.next();
                item.setTeamInfo(tItem);
                item.setPlayersUrl(String.format("%s%s", Constants.UrlInfo, "GetTeamPlayers/" + tItem.getRecordId()));
                item.setFixtureUrl(String.format("%s%s", Constants.UrlInfo, "GetTeamFixtureInfo/" + tItem.getRecordId()));
                retList.add(item);
            }
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            tran.commit();
            HibernateUtil.closeSession();
        }
        return retList;
    }

    public static ArrayList<Players> GetTeamPlayers(int teamId) {
        ArrayList<Players> retList = new ArrayList<>();
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            SQLQuery q = s.createSQLQuery("select * from players_view where teams_id = :teams_id");
            q.setParameter("teams_id", teamId);
            List lst = q.list();
            Object[] objArr = null;
            Players item = null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            for (int i = 0; i < lst.size(); i++) {
                objArr = (Object[]) lst.get(i);
                item = new Players();
                item.setRecordId(Integer.parseInt(objArr[0].toString()));
                item.setTeamsId(Integer.parseInt(objArr[1].toString()));
                item.setName(objArr[2].toString());
                item.setPosition(objArr[3].toString());
                item.setJerseyNumber(Integer.parseInt(objArr[4].toString()));
                item.setDateOfBirth(df.parse(objArr[5].toString()));
                item.setNationality(objArr[6].toString());
                item.setContractUntil(df.parse(objArr[7].toString()));
                item.setMarketValue(objArr[8].toString());
                item.setMarketValueNumber(Double.parseDouble(objArr[9].toString()));
                item.setMarketValueMoneyType(objArr[10].toString());
                retList.add(item);
            }
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            tran.commit();
            HibernateUtil.closeSession();
        }
        return retList;
    }

    public static boolean IsTokenExists(String token) {
        boolean retValue = false;
        Transaction tran = null;
        System.out.println("Servise gerldik");
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            Criteria cr = s.createCriteria(TokenInfo.class);
            cr.add(Restrictions.eq("tokenInfo", token));
            Object obj = cr.uniqueResult();

            if (obj != null) {
                TokenInfo t = (TokenInfo) obj;
                retValue = true;
                t.setCounterNumber(t.getCounterNumber() + 1);
                s.update(t);
            }
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            tran.commit();
            HibernateUtil.closeSession();
        }
        return retValue;
    }

    public static boolean SaveUser(UserInfo userInfo) {
        boolean retValue = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            if (userInfo.getRecordId() > 0) {
                s.update(userInfo);
            } else {
                s.save(userInfo);
            }
            tran.commit();
            retValue = true;
        } catch (Exception e) {
            tran.rollback();
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
        return retValue;
    }

    public static ArrayList<FixturesRet> GetTeamFixture(int teamId) {
        ArrayList<FixturesRet> retList = new ArrayList<>();
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            SQLQuery q = s.createSQLQuery("SELECT * FROM fixtures_team_view WHERE team_id=:teamId");
            q.setParameter("teamId", teamId);
            List lst = q.list();
            FixturesRet item = null;
            Object[] objArr = null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            for (int i = 0; i < lst.size(); i++) {
                objArr = (Object[]) lst.get(i);
                item = new FixturesRet();
                item.setFixtureId(Integer.parseInt(objArr[0].toString()));
                item.setLeagueId(Integer.parseInt(objArr[1].toString()));
                item.setMatchDate(df.parse(objArr[2].toString()));
                item.setStatus(objArr[3].toString());
                item.setMatchday(Integer.parseInt(objArr[4].toString()));
                item.setHomeTeamName(objArr[5].toString());
                item.setAwayTeamName(objArr[6].toString());
                item.setHomeTeamGoal(Integer.parseInt(objArr[7].toString()));
                item.setAwayTeamGoal(Integer.parseInt(objArr[8].toString()));
                retList.add(item);
            }
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            tran.commit();
            HibernateUtil.closeSession();
        }
        return retList;
    }

    /**
     * Kullanıcı var mı ?
     *
     * @param userInfo
     * @return
     */
    public static UserInfo GetUserInfo(UserInfo userInfo) {
        UserInfo retUser = null;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            Criteria c = s.createCriteria(UserInfo.class).add(Restrictions.eq("emailAddress", userInfo.getEmailAddress()));
            c.add(Restrictions.eq("recordStatus", "A"));
            retUser = (UserInfo) c.uniqueResult();
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            tran.commit();
            HibernateUtil.closeSession();
        }
        return retUser;
    }

    public static boolean IsLeagueTemasInsertBefore(int leagueId, int year) {
        boolean retVal = false;
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            SQLQuery q = s.createSQLQuery("SELECT * FROM league_teams_info WHERE league_id=:leagueId AND yearyer=:year");
            q.setParameter("teamId", leagueId);
            q.setParameter("year", year);
            List lst = q.list();
            retVal = lst.size() > 0;
        } catch (Exception e) {
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            if (null != tran) {
                tran.commit();
            }
            HibernateUtil.closeSession();
        }
        return retVal;
    }

    public static void InsertLeagueTeamsInsertInfo(Integer recordId, Integer year) {
        Transaction tran = null;
        try {
            Session s = HibernateUtil.currentSession();
            tran = s.beginTransaction();
            ProcedureCall pc = s.createStoredProcedureCall("delete_league_teams");
            pc.registerParameter("p_league_id", Integer.class, ParameterMode.IN).bindValue(recordId);
            pc.registerParameter("p_year", Integer.class, ParameterMode.IN).bindValue(year);
            ProcedureOutputs po = pc.getOutputs();
            tran.commit();
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            HelperUtil.AddErrorLog(logger, e);
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
