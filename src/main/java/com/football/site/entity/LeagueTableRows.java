package com.football.site.entity;
// Generated Jan 3, 2016 2:08:22 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LeagueTableRows generated by hbm2java
 */
@Entity
@Table(name="league_table_rows"
    ,catalog="footballsite"
)
public class LeagueTableRows  implements java.io.Serializable {


     private Integer recordId;
     private Integer leagueTableId;
     private String position;
     private String teamName;
     private Integer playedGames;
     private Integer points;
     private Integer goals;
     private Integer goalsAgainst;
     private Integer goalDifference;
     private Integer wins;
     private Integer draws;
     private Integer losses;
     private String crestUrl;
     private byte[] crestPicture;

    public LeagueTableRows() {
    }

    public LeagueTableRows(Integer leagueTableId, String position, String teamName, Integer playedGames, Integer points, Integer goals, Integer goalsAgainst, Integer goalDifference, Integer wins, Integer draws, Integer losses, String crestUrl, byte[] crestPicture) {
       this.leagueTableId = leagueTableId;
       this.position = position;
       this.teamName = teamName;
       this.playedGames = playedGames;
       this.points = points;
       this.goals = goals;
       this.goalsAgainst = goalsAgainst;
       this.goalDifference = goalDifference;
       this.wins = wins;
       this.draws = draws;
       this.losses = losses;
       this.crestUrl = crestUrl;
       this.crestPicture = crestPicture;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="record_id", nullable=false)
    public Integer getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    
    @Column(name="league_table_id")
    public Integer getLeagueTableId() {
        return this.leagueTableId;
    }
    
    public void setLeagueTableId(Integer leagueTableId) {
        this.leagueTableId = leagueTableId;
    }

    
    @Column(name="position", length=45)
    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }

    
    @Column(name="teamName", length=100)
    public String getTeamName() {
        return this.teamName;
    }
    
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    
    @Column(name="playedGames")
    public Integer getPlayedGames() {
        return this.playedGames;
    }
    
    public void setPlayedGames(Integer playedGames) {
        this.playedGames = playedGames;
    }

    
    @Column(name="points")
    public Integer getPoints() {
        return this.points;
    }
    
    public void setPoints(Integer points) {
        this.points = points;
    }

    
    @Column(name="goals")
    public Integer getGoals() {
        return this.goals;
    }
    
    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    
    @Column(name="goalsAgainst")
    public Integer getGoalsAgainst() {
        return this.goalsAgainst;
    }
    
    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    
    @Column(name="goalDifference")
    public Integer getGoalDifference() {
        return this.goalDifference;
    }
    
    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }

    
    @Column(name="wins")
    public Integer getWins() {
        return this.wins;
    }
    
    public void setWins(Integer wins) {
        this.wins = wins;
    }

    
    @Column(name="draws")
    public Integer getDraws() {
        return this.draws;
    }
    
    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    
    @Column(name="losses")
    public Integer getLosses() {
        return this.losses;
    }
    
    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    
    @Column(name="crestUrl", length=150)
    public String getCrestUrl() {
        return this.crestUrl;
    }
    
    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    
    @Column(name="crestPicture")
    public byte[] getCrestPicture() {
        return this.crestPicture;
    }
    
    public void setCrestPicture(byte[] crestPicture) {
        this.crestPicture = crestPicture;
    }




}

