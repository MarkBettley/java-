package com.ebac.modulo55;

public class ObserverContext {

    public void demonstrateObserver() {
        NotificationService service = new NotificationService();

        Observer user1 = new UserObserver("Marco");
        Observer user2 = new UserObserver("Ana");

        service.addObserver(user1);
        service.addObserver(user2);

        System.out.println("Enviando notificación a los observadores:");
        service.notifyObservers("Hay una nueva actualización disponible.");
    }
}
