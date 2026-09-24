package com.ebac.modulo55;

import java.util.Arrays;
import java.util.List;

public class InternetProxy implements Internet {

    private final Internet realInternet;
    private final List<String> blockedWebsites;

    public InternetProxy() {
        this.realInternet = new RealInternet();
        this.blockedWebsites = Arrays.asList(
                "sitio-bloqueado.com",
                "acceso-denegado.com"
        );
    }

    @Override
    public void connectTo(String website) {
        if (blockedWebsites.contains(website.toLowerCase())) {
            System.out.println("Acceso bloqueado a: " + website);
            return;
        }

        realInternet.connectTo(website);
    }
}
