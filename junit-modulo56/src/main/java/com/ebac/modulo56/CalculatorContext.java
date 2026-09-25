package com.ebac.modulo56;

public class CalculatorContext {

    private final Calculator calculator;

    public CalculatorContext() {
        this.calculator = new Calculator();
    }

    public void demonstrateCalculator() {
        System.out.println("Suma: " + calculator.sumar(10, 5));
        System.out.println("Resta: " + calculator.restar(10, 5));
        System.out.println("Multiplicación: " + calculator.multiplicar(10, 5));
        System.out.println("División: " + calculator.dividir(10, 5));
    }
}
