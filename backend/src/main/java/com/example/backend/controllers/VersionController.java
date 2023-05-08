package com.example.backend.controllers;

import com.example.backend.services.VersionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that returns the version information for the Application
 */
@AllArgsConstructor
@RestController
public class VersionController {

    private final VersionService versionService;

    // Could be separated into 2 endpoints if the information is required separately
    @GetMapping("/versions")
    public String getVersionInformation() {
        return versionService.getVersionInformation();
    }
}
