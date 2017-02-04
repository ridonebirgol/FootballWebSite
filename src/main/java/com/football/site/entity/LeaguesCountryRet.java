/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.entity;

import java.io.Serializable;

/**
 *
 * @author RIDVAN
 */
public class LeaguesCountryRet implements Serializable {

    private Integer recordId;
    private String countryName;
    private String countryCode;
    private Integer orderNo;
    private byte[] countryFlag;
    private String leaguesUrl;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public byte[] getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(byte[] countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getLeaguesUrl() {
        return leaguesUrl;
    }

    public void setLeaguesUrl(String leaguesUrl) {
        this.leaguesUrl = leaguesUrl;
    }

}
