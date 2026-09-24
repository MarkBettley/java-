package com.ebac.modulo55;

public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Café";
    }

    @Override
    public double getCost() {
        return 30.0;
    }
}
