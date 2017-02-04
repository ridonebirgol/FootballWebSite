/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.jobs;

import com.football.site.getdata.FootballGetDataHelper;
import com.football.site.helpers.HelperUtil;
import java.util.Calendar;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author RIDVAN
 */
public class QuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        HelperUtil.SendMail(Calendar.getInstance().getTime() + " - Getting data started");
        System.out.println(Calendar.getInstance().getTime() + " - Getting data started");
        FootballGetDataHelper.GetAndInsertUrlData();
        HelperUtil.SendMail(Calendar.getInstance().getTime() + " - Getting data ended");
        System.out.println(Calendar.getInstance().getTime() + " - Getting data ended");
    }
}
