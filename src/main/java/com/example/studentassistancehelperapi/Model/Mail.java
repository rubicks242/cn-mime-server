package com.example.studentassistancehelperapi.Model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Mail {
    private String ip;
    private String city;
    private String url;
    private String version;
    private String country_name;
    private String org;
    private String domain;
    private String region;
    private ArrayList<String> to;

}





