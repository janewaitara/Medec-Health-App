package com.example.medec.Doctors;

public class DoctorDetails {

    private String id;
    private String name;
    private String doctorsTitle;
    private String docCoursePursued;
    private String emailAddress;
    private String doctorContact;
    private String yearsOfExperience;
    private String imageUrl;
    public DoctorDetails() {
    }
    public DoctorDetails(String name, String emailAddress, String doctorContact, String docCoursePursued, String doctorsTitle, String yearsOfExperience, String imageUrl) {
        this.setId(id);
        this.setName(name);
        this.setEmailAddress(emailAddress);
        this.setDoctorContact(doctorContact);
        this.setDocCoursePursued(docCoursePursued);
        this.setDoctorsTitle(doctorsTitle);
        this.setYearsOfExperience(yearsOfExperience);
        this.setImageUrl(imageUrl);
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

    public String getDoctorsTitle() {
        return doctorsTitle;
    }

    public void setDoctorsTitle(String doctorsTitle) {
        this.doctorsTitle = doctorsTitle;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDoctorContact() {
        return doctorContact;
    }

    public void setDoctorContact(String doctorContact) {
        this.doctorContact = doctorContact;
    }


    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imsgeUrl) {
        this.imageUrl = imsgeUrl;
    }

    public String getDocCoursePursued() {
        return docCoursePursued;
    }

    public void setDocCoursePursued(String docCoursePursued) {
        this.docCoursePursued = docCoursePursued;
    }
}
