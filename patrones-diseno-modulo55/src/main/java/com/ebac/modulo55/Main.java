package com.ebac.modulo55;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== DECORATOR ===");
        DecoratorContext decoratorContext = new DecoratorContext();
        decoratorContext.demonstrateDecorator();

        System.out.println("\n=== PROXY ===");
        ProxyContext proxyContext = new ProxyContext();
        proxyContext.demonstrateProxy();

        System.out.println("\n=== OBSERVER ===");
        ObserverContext observerContext = new ObserverContext();
        observerContext.demonstrateObserver();

        System.out.println("\n=== STATE ===");
        StateContext stateContext = new StateContext();
        stateContext.demonstrateState();
    }
}
