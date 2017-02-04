package com.matchscore.objects;

import com.matchscore.entity.FixtureInfo;
import com.matchscore.entity.linksentity.LinksFixtures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by RIDVAN on 08.11.2015.
 */
public class Fixtures implements Serializable {
    private LinksFixtures links;
    private int count;
    private ArrayList<FixtureInfo> fixtures;

    public LinksFixtures getLinks() {
        return links;
    }

    public void setLinks(LinksFixtures _links) {
        this.links = _links;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<FixtureInfo> getFixtures() {
        return fixtures;
    }

    public void setFixtures(ArrayList<FixtureInfo> fixtures) {
        this.fixtures = fixtures;
    }
}
