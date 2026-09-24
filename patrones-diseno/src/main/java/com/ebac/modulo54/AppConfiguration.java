package com.ebac.modulo54;

public class AppConfiguration {

    private static AppConfiguration instance;

    private String applicationName;

    private AppConfiguration() {
        this.applicationName = "Aplicación EBAC - Patrones de Diseño";
    }

    public static AppConfiguration getInstance() {
        if (instance == null) {
            instance = new AppConfiguration();
        }

        return instance;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
