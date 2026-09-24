package com.ebac.modulo55;

public class StateContext {

    public void demonstrateState() {
        Order order = new Order(new PendingState());

        System.out.println("Estado inicial:");
        order.processOrder();

        order.setState(new ShippedState());

        System.out.println("Después de cambiar el estado:");
        order.processOrder();
    }
}
