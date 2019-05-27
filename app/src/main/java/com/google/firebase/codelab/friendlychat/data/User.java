package com.google.firebase.codelab.friendlychat.data;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String photoUrl;
    private String currentType;   //c for customer, d for delivery man
    private boolean deliveryModeActivation;
    private int rating;
    private String gender; //u:unkown, f:female, m:male
    private boolean basicInfo;


    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.phone = "";
        this.email = email;
        this.currentType = "";
        this.deliveryModeActivation = false;
        this.rating = 0;
        photoUrl = "";
        gender = "u";
        basicInfo = false;
    }

    public User() {

    }

    public boolean isBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(boolean basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public boolean isDeliveryModeActivation() {
        return deliveryModeActivation;
    }

    public void setDeliveryModeActivation(boolean deliveryModeActivation) {
        this.deliveryModeActivation = deliveryModeActivation;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", currentType='" + currentType + '\'' +
                ", deliveryModeActivation=" + deliveryModeActivation +
                ", rating=" + rating +
                ", gender='" + gender + '\'' +
                ", basicInfo=" + basicInfo +
                '}';
    }
}
