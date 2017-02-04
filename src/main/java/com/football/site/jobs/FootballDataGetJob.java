/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.jobs;

import com.football.site.helpers.HelperUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author RIDVAN
 */
public class FootballDataGetJob implements
        ServletContextListener {

    private static final Logger logger = Logger.getLogger(FootballDataGetJob.class);
    Scheduler scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        System.out.println("Context Initialized");

        try {
            // Setup the Job class and the Job group
            JobDetail job = newJob(QuartzJob.class).withIdentity("CronQuartzJob", "Group").build();

            // Create a Trigger that fires every 5 minutes.
            Trigger trigger = newTrigger()
                    .withIdentity("TriggerName", "Group")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/4 1/1 * ? *"))
                    .build();

            // Setup the Job and Trigger with Scheduler & schedule jobs
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            HelperUtil.AddErrorLog(logger, e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContext) {
        System.out.println("Context Destroyed");
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            HelperUtil.AddErrorLog(logger, e);
        }
    }
}
