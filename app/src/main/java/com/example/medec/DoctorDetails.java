package com.example.medec;

public class DoctorsDetails {
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

    public String getDoctorsTitle() {
        return doctorsTitle;
    }

    public void setDoctorsTitle(String doctorsTitle) {
        this.doctorsTitle = doctorsTitle;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getImsgeUrl() {
        return imsgeUrl;
    }

    public void setImsgeUrl(String imsgeUrl) {
        this.imsgeUrl = imsgeUrl;
    }

    private String id;
    private String name;
    private String doctorsTitle;
    private String emailAdress;
    private String yearsOfExperience;
    private String imsgeUrl;

    public DoctorsDetails(String id, String name, String doctorsTitle, String emailAdress, String yearsOfExperience, String imsgeUrl) {
        this.id = id;
        this.name = name;
        this.doctorsTitle = doctorsTitle;
        this.emailAdress = emailAdress;
        this.yearsOfExperience = yearsOfExperience;
        this.imsgeUrl = imsgeUrl;
    }
}
