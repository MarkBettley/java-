package com.ebac.modulo55;

public class DecoratorContext {

    public void demonstrateDecorator() {
        Coffee simpleCoffee = new SimpleCoffee();
        Coffee coffeeWithMilk = new MilkDecorator(simpleCoffee);

        System.out.println("Café original:");
        System.out.println(simpleCoffee.getDescription()
                + " - $" + simpleCoffee.getCost());

        System.out.println("Café decorado:");
        System.out.println(coffeeWithMilk.getDescription()
                + " - $" + coffeeWithMilk.getCost());
    }
}
