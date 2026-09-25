package com.ebac.modulo56;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testSumar() {
        assertEquals(15.0, calculator.sumar(10, 5));
    }

    @Test
    void testRestar() {
        assertEquals(5.0, calculator.restar(10, 5));
    }

    @Test
    void testMultiplicar() {
        assertEquals(50.0, calculator.multiplicar(10, 5));
    }

    @Test
    void testDividir() {
        assertEquals(2.0, calculator.dividir(10, 5));
    }

    @Test
    void testDivisionEntreCero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.dividir(10, 0)
        );
    }
}
