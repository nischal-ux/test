package com.example.attack;

public class UserInfo {
    private String id;
    private String name;
    private String email;
    private String password;
    private String conatact;
    private String image;
    private String profile;
    private String description;
    private String price;
    private String date;

    public UserInfo() {
    }
    public UserInfo(String id, String name, String email, String password, String conatact, String image, String profile, String description, String price, String date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.conatact = conatact;
        this.image = image;
        this.profile = profile;
        this.description = description;
        this.price = price;
        this.date = date;
    }


    public String getId() {
        return id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getConatact() {
        return conatact;
    }

    public void setConatact(String conatact) {
        this.conatact = conatact;
    }




    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String ToString(){
return name;
    }
}
