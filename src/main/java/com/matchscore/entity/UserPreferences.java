package com.matchscore.entity;

import java.io.Serializable;

/**
 * Created by RIDVAN on 13.11.2015.
 */
public class UserPreferences implements Serializable {
    private String favoriteCountry;
    private String favoriteTeam;
    private String nameSurname;

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getFavoriteCountry() {
        return favoriteCountry;
    }

    public void setFavoriteCountry(String favoriteCountry) {
        this.favoriteCountry = favoriteCountry;
    }

    public String getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(String favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }
}
