package com.ebac.modulo55;

public class PendingState implements OrderState {

    @Override
    public void handle() {
        System.out.println("El pedido está pendiente.");
    }
}
