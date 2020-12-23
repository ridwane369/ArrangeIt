package com.andsofts.ridwane.arrangeit;


public class Events {
    String Event_Type,Location,Date,Contact_Details,Created_By;
    public Events(){}

    public Events(String type, String loc, String date, String cd, String crtdby) {
        Event_Type = type;
        Location = loc;
        Date = date;
        Contact_Details = cd;
        Created_By = crtdby;
    }

    public String getEvent_Type() {
        return Event_Type;
    }

    public void setEvent_Type(String event_Type) {
        Event_Type = event_Type;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getContact_Details() {
        return Contact_Details;
    }

    public void setContact_Details(String contact_Details) {
        Contact_Details = contact_Details;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }
}
