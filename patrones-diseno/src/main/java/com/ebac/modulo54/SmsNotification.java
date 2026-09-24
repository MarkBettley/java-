package com.ebac.modulo54;

public class SmsNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Enviando notificación por SMS: " + message);
    }
}
