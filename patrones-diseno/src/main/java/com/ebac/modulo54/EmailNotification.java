package com.ebac.modulo54;

public class EmailNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Enviando notificación por EMAIL: " + message);
    }
}
