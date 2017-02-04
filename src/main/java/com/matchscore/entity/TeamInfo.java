package com.matchscore.entity;

import com.matchscore.entity.linksentity.LinksTeamInfo;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class TeamInfo {
    private LinksTeamInfo _links;
    private String name;
    private String code;
    private String shortName;
    private String crestUrl;

    public LinksTeamInfo get_links() {
        return _links;
    }

    public void set_links(LinksTeamInfo _links) {
        this._links = _links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }
}
