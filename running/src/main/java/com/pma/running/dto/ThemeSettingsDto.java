package com.pma.running.dto;

import lombok.Data;

@Data
public class ThemeSettingsDto {

    private String username;
    private boolean nightTheme;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isNightTheme() {
        return nightTheme;
    }

    public void setNightTheme(boolean nightTheme) {
        this.nightTheme = nightTheme;
    }
}
