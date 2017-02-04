package com.matchscore.entity.linksentity;

import com.matchscore.entity.ProjectEnums.LinkKeys;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class ListeLink {
    private String linkText;
    private String linkUrl;
    private LinkKeys linkKey;

    public LinkKeys getLinkKey() {
        return linkKey;
    }

    public void setLinkKey(LinkKeys linkKey) {
        this.linkKey = linkKey;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
