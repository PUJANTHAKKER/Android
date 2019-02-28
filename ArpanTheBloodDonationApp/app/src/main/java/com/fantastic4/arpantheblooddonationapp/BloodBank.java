package com.fantastic4.arpantheblooddonationapp;

public class BloodBank {

        String name;
        String contactNumber;
        String city;
        String type;
        String age;
        String password;
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

        public BloodBank(){

        }



        public BloodBank(String name, String contactNumber, String type, String city, String address,String password) {
            this.name = name;
            this.contactNumber = contactNumber;
            this.type = this.type;
            this.city = city;
            this.age = address;
            this.password = password;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

}
