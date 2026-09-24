package com.ebac.modulo54;

public class FactoryContext {

    private final NotificationFactory factory;

    public FactoryContext() {
        this.factory = new NotificationFactory();
    }

    public void sendNotification(String type, String message) {
        Notification notification = factory.createNotification(type);
        notification.send(message);
    }
}
