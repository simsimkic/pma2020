package com.example.myapplication.dto.request;

import com.google.gson.annotations.SerializedName;

public class ThemeSettingsDto {

    @SerializedName("username")
    private String username;
    @SerializedName("nightTheme")
    private boolean nightTheme;

    public ThemeSettingsDto() {
    }

    public ThemeSettingsDto(String username, boolean nightTheme) {
        this.username = username;
        this.nightTheme = nightTheme;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getNightTheme() {
        return nightTheme;
    }

    public void setNightTheme(boolean nightTheme) {
        this.nightTheme = nightTheme;
    }
}
