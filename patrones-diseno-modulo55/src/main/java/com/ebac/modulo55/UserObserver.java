package com.ebac.modulo55;

public class UserObserver implements Observer {

    private final String name;

    public UserObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " recibió la notificación: " + message);
    }
}
