package com.ebac.modulo54;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== FACTORY METHOD ===");

        FactoryContext factoryContext = new FactoryContext();
        factoryContext.sendNotification(
                "EMAIL",
                "Mensaje creado mediante Factory Method"
        );
        factoryContext.sendNotification(
                "SMS",
                "Segundo mensaje creado mediante Factory Method"
        );

        System.out.println("\n=== BUILDER ===");

        BuilderContext builderContext = new BuilderContext();
        builderContext.showUserProfile();

        System.out.println("\n=== SINGLETON ===");

        SingletonContext singletonContext = new SingletonContext();
        singletonContext.demonstrateSingleton();
    }
}
