package com.football.site.entity;
// Generated Jan 3, 2016 2:08:22 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TokenInfo generated by hbm2java
 */
@Entity
@Table(name="token_info"
    ,catalog="footballsite"
)
public class TokenInfo  implements java.io.Serializable {


     private Integer recordId;
     private String emailAddress;
     private String tokenInfo;
     private Date createDate;
     private Integer counterNumber;

    public TokenInfo() {
    }

    public TokenInfo(String emailAddress, String tokenInfo, Date createDate, Integer counterNumber) {
       this.emailAddress = emailAddress;
       this.tokenInfo = tokenInfo;
       this.createDate = createDate;
       this.counterNumber = counterNumber;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="recordId", nullable=false)
    public Integer getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    
    @Column(name="email_address", length=100)
    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    
    @Column(name="token_info", length=100)
    public String getTokenInfo() {
        return this.tokenInfo;
    }
    
    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=19)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    @Column(name="counter_number")
    public Integer getCounterNumber() {
        return this.counterNumber;
    }
    
    public void setCounterNumber(Integer counterNumber) {
        this.counterNumber = counterNumber;
    }




}


