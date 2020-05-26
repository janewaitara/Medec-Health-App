package com.example.medec;

public class CountyDetails {



    private final String name;
    private final int code;
    private final String capital;

    public CountyDetails(String name, int code, String capital) {
        this.name = name;
        this.code = code;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getCapital() {
        return capital;
    }
}
