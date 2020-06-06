package com.pma.running.controler;

import com.pma.running.dto.NotificationSettingsDto;
import com.pma.running.dto.PrivacySettingsDto;
import com.pma.running.dto.ThemeSettingsDto;
import com.pma.running.dto.UserSettingsResponseDto;
import com.pma.running.model.User;
import com.pma.running.model.UserSettings;
import com.pma.running.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "", maxAge = 3600)
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

    @Autowired
    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @PutMapping("/settings/privacy")
    public ResponseEntity<UserSettingsResponseDto> updatePrivacySettings(@RequestBody PrivacySettingsDto dto) {
        return new ResponseEntity<UserSettingsResponseDto>(userSettingsService.updatePrivacySettings(dto),
                HttpStatus.OK);
    }

    @PutMapping("/settings/notifications")
    public ResponseEntity<UserSettingsResponseDto> updateNotificationSettings(@RequestBody NotificationSettingsDto dto) {
        return new ResponseEntity<UserSettingsResponseDto>(userSettingsService.updateNotificationSettings(dto),
                HttpStatus.OK);
    }

    @PutMapping("/settings/theme")
    public ResponseEntity<UserSettingsResponseDto> updateThemeSettings(@RequestBody ThemeSettingsDto dto) {
        return new ResponseEntity<UserSettingsResponseDto>(userSettingsService.updateThemeSettings(dto),
                HttpStatus.OK);
    }

    @GetMapping("/settings/{username}")
    public ResponseEntity<UserSettingsResponseDto> getUserSettings(@PathVariable String username) {
        return new ResponseEntity<UserSettingsResponseDto>(userSettingsService.getUserSettings(username),
                HttpStatus.OK);
    }

}
