package com.star.demo.music;

import org.json.JSONObject;

/**
 * Created by Jack on 15/11/16.
 */
public class Music {
    private String singer;
    private String sourceName;
    private String name;
    private String downloadUrl;

    public Music(JSONObject jsonObject) {
        this.singer = jsonObject.optString("singer");
        this.sourceName = jsonObject.optString("sourceName");
        this.name = jsonObject.optString("name");
        this.downloadUrl = jsonObject.optString("downloadUrl");
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
