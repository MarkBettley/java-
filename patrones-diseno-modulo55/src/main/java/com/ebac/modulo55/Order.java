package com.ebac.modulo55;

public class Order {

    private OrderState state;

    public Order(OrderState state) {
        this.state = state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void processOrder() {
        state.handle();
    }
}
