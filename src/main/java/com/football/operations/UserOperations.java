/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.operations;

import com.football.site.db.DbHelper;
import com.football.site.entity.UserInfo;
import com.google.gson.Gson;
import com.matchscore.entity.Constants;

/**
 *
 * @author RIDVAN
 */
public class UserOperations {

    /**
     * Kullanıcı bilgilerini kaydeder
     *
     * @param userData
     * @return
     */
    public static String SaveUserInfo(UserInfo userInfo) {
        String resultMessage = "";
        Gson gson = new Gson();
        //UserInfo userInfo = gson.fromJson(userData, UserInfo.class);
        if (DbHelper.SaveUser(userInfo)) {
            resultMessage = gson.toJson(userInfo, UserInfo.class);
        } else {
            resultMessage = Constants.UNSUCCESS;
        }
        return resultMessage;
    }

    public static String CheckOrSaveUser(String content) {
        String result = "";
        UserInfo userInfo = new Gson().fromJson(content, UserInfo.class);
        UserInfo dbUser = DbHelper.GetUserInfo(userInfo);
        if (dbUser == null) {
            result = UserOperations.SaveUserInfo(userInfo);
        } else if (dbUser.getPassword().equals(userInfo.getPassword())) {
            result = new Gson().toJson(dbUser, UserInfo.class);
        } else {
            result = Constants.UNSUCCESS + ":UserExistsButPasswordNotvalid";
        }
        return result;
    }

    public static String GetUserInfo(String content) {
        String result = "";
        UserInfo userInfo = new Gson().fromJson(content, UserInfo.class);
        UserInfo dbUser = DbHelper.GetUserInfo(userInfo);
        if (dbUser == null) {
            result = Constants.UNSUCCESS + ":UserNotExists";
        } else if (dbUser.getPassword().equals(userInfo.getPassword())) {
            result = new Gson().toJson(dbUser, UserInfo.class);
        } else {
            result = Constants.UNSUCCESS + ":UserExistsButPasswordNotvalid";
        }
        return result;
    }
}
