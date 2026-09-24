package com.ebac.modulo55;

public class ShippedState implements OrderState {

    @Override
    public void handle() {
        System.out.println("El pedido ha sido enviado.");
    }
}
