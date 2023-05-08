package com.example.backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VersionService {

    @Value("${app.version}")
    private String appVersion;

    @Value("${db.version}")
    private String dbVersion;

    public String getVersionInformation() {
        return "App Version: " + appVersion + ", DB Version: " + dbVersion;
    }
}
