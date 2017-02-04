/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.db;

import com.football.site.entity.Fixtures;
import com.football.site.entity.FixturesMatchResult;
import com.football.site.entity.FixturesTeam;
import com.football.site.entity.FixturesTeamMatchResult;
import com.football.site.entity.LeagueTableRows;
import com.football.site.entity.LeagueTableTeamStatistics;
import com.football.site.entity.Leagues;
import com.football.site.entity.LeaguesCountry;
import com.football.site.entity.Leaguetable;
import com.football.site.entity.Players;
import com.football.site.entity.Teams;
import com.football.site.entity.TokenInfo;
import com.football.site.entity.UserInfo;
import com.football.site.helpers.HelperUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author RIDVAN
 */
public class HibernateUtil {

    public static final ThreadLocal MAP = new ThreadLocal();

    private static final Logger LOG = Logger.getLogger(HibernateUtil.class);

    /**
     * Make default construct private
     */
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // loads configuration and mappings
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                //TODO : yeni bir entity geldiğinde ekleme yapılacak
                configuration.addAnnotatedClass(Fixtures.class);
                configuration.addAnnotatedClass(FixturesMatchResult.class);
                configuration.addAnnotatedClass(LeagueTableRows.class);
                configuration.addAnnotatedClass(LeagueTableTeamStatistics.class);
                configuration.addAnnotatedClass(Leagues.class);
                configuration.addAnnotatedClass(Leaguetable.class);
                configuration.addAnnotatedClass(Teams.class);
                configuration.addAnnotatedClass(Players.class);
                configuration.addAnnotatedClass(TokenInfo.class);
                configuration.addAnnotatedClass(UserInfo.class);
                configuration.addAnnotatedClass(FixturesTeam.class);
                configuration.addAnnotatedClass(FixturesTeamMatchResult.class);
                configuration.addAnnotatedClass(LeaguesCountry.class);

                ServiceRegistry serviceRegistry
                        = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                // builds a session factory from the service registry
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException ex) {
                HelperUtil.AddErrorLog(LOG, ex);
            } catch (Exception e) {
                HelperUtil.AddErrorLog(LOG, e);
            }
        }

        return sessionFactory;
    }

    /**
     * Gets Hibernate Session for current thread. When finished, users must
     * return session using {@link #closeSession() closeSession()} method.
     *
     * @return Hibernate Session for current thread.
     * @throws HibernateException if there is an error opening a new session.
     */
    public static Session currentSession() throws HibernateException {
        Session s = (Session) MAP.get();
        // Open a new Session, if this Thread has none yet
        if (s == null) {
            s = getSessionFactory().openSession();
            MAP.set(s);
        }
        return s;
    }

    /**
     * Closes the Hibernate Session. Users must call this method after calling
     * {@link #currentSession() currentSession()}.
     *
     * @throws HibernateException if session has problem closing.
     */
    public static void closeSession() throws HibernateException {
        Session s = (Session) MAP.get();
        MAP.set(null);
        if (s != null) {
            s.close();
        }
    }
}
