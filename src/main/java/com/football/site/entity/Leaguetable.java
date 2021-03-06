package com.football.site.entity;
// Generated Jan 3, 2016 2:08:22 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Leaguetable generated by hbm2java
 */
@Entity
@Table(name="leaguetable"
    ,catalog="footballsite"
)
public class Leaguetable  implements java.io.Serializable {


     private Integer recordId;
     private Integer leagueId;
     private String leaguecaption;
     private Integer matchday;

    public Leaguetable() {
    }

    public Leaguetable(Integer leagueId, String leaguecaption, Integer matchday) {
       this.leagueId = leagueId;
       this.leaguecaption = leaguecaption;
       this.matchday = matchday;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="record_id", nullable=false)
    public Integer getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    
    @Column(name="league_id")
    public Integer getLeagueId() {
        return this.leagueId;
    }
    
    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    
    @Column(name="leaguecaption", length=45)
    public String getLeaguecaption() {
        return this.leaguecaption;
    }
    
    public void setLeaguecaption(String leaguecaption) {
        this.leaguecaption = leaguecaption;
    }

    
    @Column(name="matchday")
    public Integer getMatchday() {
        return this.matchday;
    }
    
    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }




}


