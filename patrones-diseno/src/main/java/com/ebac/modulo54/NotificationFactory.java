package com.ebac.modulo54;

public class NotificationFactory {

    public Notification createNotification(String type) {
        if ("EMAIL".equalsIgnoreCase(type)) {
            return new EmailNotification();
        }

        if ("SMS".equalsIgnoreCase(type)) {
            return new SmsNotification();
        }

        throw new IllegalArgumentException(
                "Tipo de notificación no válido: " + type
        );
    }
}
