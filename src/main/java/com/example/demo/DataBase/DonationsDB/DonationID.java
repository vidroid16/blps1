package com.example.demo.DataBase.DonationsDB;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
@Embeddable
public class DonationID implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userID;
    private Long projectID;
    private Timestamp donation_time;

    public DonationID(){
        this.donation_time = new Timestamp(System.currentTimeMillis());
    }
    DonationID(Long userId, Long projectID, Timestamp time){
        super();
        this.projectID = projectID;
        this.donation_time = time;
        this.userID = userId;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public Timestamp getTime() {
        return donation_time;
    }

    public void setTime(Timestamp time) {
        this.donation_time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonationID that = (DonationID) o;
        return userID.equals(that.userID) && projectID.equals(that.projectID) && donation_time.equals(that.donation_time);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((userID == null) ? 0 : userID.hashCode());
        result = prime * result
                + ((projectID == null) ? 0 : projectID.hashCode());
        result = prime * result
                + ((donation_time == null) ? 0 : donation_time.hashCode());
        return result;
    }
}
