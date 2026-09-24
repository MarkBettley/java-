package com.ebac.modulo55;

public class ProxyContext {

    public void demonstrateProxy() {
        Internet internet = new InternetProxy();

        System.out.println("Intentando acceder a un sitio permitido:");
        internet.connectTo("ebac.mx");

        System.out.println("Intentando acceder a un sitio bloqueado:");
        internet.connectTo("sitio-bloqueado.com");
    }
}
