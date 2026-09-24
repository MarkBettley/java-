package com.ebac.modulo55;

public class MilkDecorator implements Coffee {

    private final Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + leche";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10.0;
    }
}
