package com.fantastic4.arpantheblooddonationapp;

/**
 * Created by root on 11/6/16.
 */

public class Donor {
    String name;
    String contactNumber;
    String city;
    String bloodGroup;
    String age;
    String password;
    String email;
    String deviceToken;
    //String lat;
    //String lan;

    /*public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }*/

    public Donor(){

    }



    public Donor(String name, String contactNumber, String bloodGroup, String city, String age,String password, String email, String deviceToken) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.bloodGroup = bloodGroup;
        this.city = city;
        this.age = age;
        this.password = password;
        this.email = email;
        this.deviceToken = deviceToken;
       // this.lat = lat;
        //this.lan = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
