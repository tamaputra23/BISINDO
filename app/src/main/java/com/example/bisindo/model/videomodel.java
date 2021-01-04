package com.example.bisindo.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;
//sebagai model yang menerima data dari firebase database
public class videomodel {
    private String judul;
    private String video;
    public videomodel(){ }
    public videomodel(String judul, String video) {

        this.judul = judul;
        this.video = video;

    }
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    public  String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("judul", judul);
        result.put("video", video);
        return result;
    }
}
