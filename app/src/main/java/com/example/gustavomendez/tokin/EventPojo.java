package com.example.gustavomendez.tokin;

public class EventPojo {
    private String nameBand, nameRes, userIds, userIdr, userIdBand, userIdRes, dateB, dateEnd;
    private int eventStatus;

    public EventPojo() {
    }

    public EventPojo(String nameBand, String nameRes, String userIds, String userIdr, String userIdBand, String userIdRes,
                     Integer eventStatus, String dateB, String dateEnd) {
        this.nameBand = nameBand;
        this.nameRes = nameRes;
        this.userIds = userIds;
        this.eventStatus = eventStatus;
        this.userIdr = userIdr;
        this.userIdRes = userIdRes;
        this.userIdBand = userIdBand;
        this.dateB = dateB;
        this.dateEnd = dateEnd;

    }

    public String getNameBand() {
        return nameBand;
    }

    public void setNameBand(String nameBand) {
        this.nameBand = nameBand;
    }

    public String getNameRes() {
        return nameRes;
    }

    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getUserIdr() {
        return userIdr;
    }

    public void setUserIdr(String userIdr) {
        this.userIdr = userIdr;
    }

    public String getUserIdBand() {
        return userIdBand;
    }

    public void setUserIdBand(String userIdBand) {
        this.userIdBand = userIdBand;
    }

    public String getUserIdRes() {
        return userIdRes;
    }

    public void setUserIdRes(String userIdRes) {
        this.userIdRes = userIdRes;
    }

    public String getDateB() {
        return dateB;
    }

    public void setDateB(String dateB) {
        this.dateB = dateB;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(int eventStatus) {
        this.eventStatus = eventStatus;
    }

}

