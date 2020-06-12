package com.example.attack;

public class info {
    public String id,  name,username,  email, password,contact,price,description,profileurl,pictures,date;


    public info(String id, String username, String contact, String price, String description, String pictures, String date) {
        this.id = id;
        this.username = username;

        this.contact = contact;
        this.price = price;
        this.description = description;
        this.pictures = pictures;
        this.date = date;
    }


public info(String id){
        this.id=id;

}
    public info() {
    }

    public info(String id, String username, String contact, String price, String description, String url1, String date, String email) {
        this.username = username;
this.email=email;
        this.contact = contact;
        this.price = price;
        this.description = description;
        this.pictures = pictures;
        this.date = date;
        this.id=id;
    }

    public info(String id, String name, String username, String email, String profileurl) {
        this.username = username;
        this.email=email;
        this.id=id;
        this.name=name;
        this.profileurl=profileurl;
    }

    public info(String[] username) {
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
