package com.ebac.modulo55;

public class RealInternet implements Internet {

    @Override
    public void connectTo(String website) {
        System.out.println("Conectando a: " + website);
    }
}
