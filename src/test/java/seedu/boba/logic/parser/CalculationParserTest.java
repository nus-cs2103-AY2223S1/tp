package seedu.boba.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class CalculationParserTest {
    @Test
    public void calculate_addition_correctly() {
        assertEquals(CalculationParser.parseCalculation("1+1"), "2.00");
    }

    @Test
    public void calculate_subtraction_correctly() {
        assertEquals(CalculationParser.parseCalculation("1-2"), "-1.00");
    }

    @Test
    public void calculate_multiplication_correctly() {
        assertEquals(CalculationParser.parseCalculation("2*2"), "4.00");
    }

    @Test
    public void calculate_multiplication_correctly_decimal_overflow() {
        assertEquals(CalculationParser.parseCalculation("1.14*5.14"), "5.86");
    }

    @Test
    public void calculate_divisible_division_correctly() {
        assertEquals(CalculationParser.parseCalculation("2/2"), "1.00");
    }

    @Test
    public void calculate_nonDivisible_division_correctly_one_decimal() {
        assertEquals(CalculationParser.parseCalculation("5/2"), "2.50");
    }

    @Test
    public void calculate_nonDivisible_division_correctly_two_decimal() {
        assertEquals(CalculationParser.parseCalculation("10/8"), "1.25");
    }

    @Test
    public void calculate_nonDivisible_division_correctly_many_decimal() {
        assertEquals(CalculationParser.parseCalculation("1/8"), "0.13");
    }

    @Test
    public void calculate_nonDivisible_division_correctly_infinite_decimal() {
        assertEquals(CalculationParser.parseCalculation("10/3"), "3.33");
    }

    @Test
    public void calculate_division_by_zero() {
        Random rand = new Random();
        int numerator = rand.nextInt(Integer.MAX_VALUE);
        assertEquals(CalculationParser.parseCalculation(numerator + "/0"), "Infinity");
    }

    @Test
    public void calculate_complex_expressions_without_precedence() {
        assertEquals(CalculationParser.parseCalculation("1+2+3"), "6.00");
        assertEquals(CalculationParser.parseCalculation("1-2-3-4"), "-8.00");
        assertEquals(CalculationParser.parseCalculation("2*3*2"), "12.00");
        assertEquals(CalculationParser.parseCalculation("64/8/2/2"), "2.00");
    }

    @Test
    public void calculate_complex_expressions_with_precedence() {
        assertEquals(CalculationParser.parseCalculation("1+2*3"), "7.00");
        assertEquals(CalculationParser.parseCalculation("1*2-3"), "-1.00");
        assertEquals(CalculationParser.parseCalculation("1-2*3-4"), "-9.00");
        assertEquals(CalculationParser.parseCalculation("2+3*2"), "8.00");
        assertEquals(CalculationParser.parseCalculation("64-8/2/2"), "62.00");
    }

    @Test
    public void calculate_complex_expressions_with_whitespaces() {
        assertEquals(CalculationParser.parseCalculation("1 + 2 * 3"), "7.00");
        assertEquals(CalculationParser.parseCalculation("1 * 2 -  3"), "-1.00");
        assertEquals(CalculationParser.parseCalculation(" 1 - 2 * 3 - 4"), "-9.00");
        assertEquals(CalculationParser.parseCalculation("2 +3 *2"), "8.00");
        assertEquals(CalculationParser.parseCalculation("64 - 8/2/2"), "62.00");
    }

    @Test
    public void calculate_complex_expressions_parenthesis() {
        assertEquals(CalculationParser.parseCalculation("(1+2)*3"), "9.00");
        assertEquals(CalculationParser.parseCalculation("1*(2-3)"), "-1.00");
        assertEquals(CalculationParser.parseCalculation(" (1-2)*(3-4)"), "1.00");
        assertEquals(CalculationParser.parseCalculation("2+3*2/(3/4)"), "10.00");
        assertEquals(CalculationParser.parseCalculation(" 64 -8 /(2 / 2)*2"), "48.00");
        assertEquals(CalculationParser.parseCalculation(" 64.15 -8.01 /(2.2 / 2)*2"), "49.59");
        assertEquals(CalculationParser.parseCalculation("1.00+1.0+1"), "3.00");
    }

}
