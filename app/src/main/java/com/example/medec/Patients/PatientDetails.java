package com.example.medec.Patients;

public class PatientDetails {
    public PatientDetails() {
    }

    public PatientDetails( String patientName, String patientEmailAddress, String patientContact, String imageUrl) {
        this.setId(id);
        this.setPatientName(patientName);
        this.setPatientEmailAddress(patientEmailAddress);
        this.setPatientContact(patientContact);
        this.setImageUrl(imageUrl);
    }

    private String id;
    private String patientName;
    private String patientEmailAddress;
    private String patientContact;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmailAddress() {
        return patientEmailAddress;
    }

    public void setPatientEmailAddress(String patientEmailAddress) {
        this.patientEmailAddress = patientEmailAddress;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(String patientContact) {
        this.patientContact = patientContact;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
