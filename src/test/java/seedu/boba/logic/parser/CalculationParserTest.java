package seedu.boba.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;


public class CalculationParserTest {
    // I know the test function names are weired, just to make checkstyle happy
    @Test
    public void calculate_addition_correctly() {
        assertEquals(CalculationParser.parseCalculation("1+1"), "2");
    }

    @Test
    public void calculate_subtraction_correctly() {
        assertEquals(CalculationParser.parseCalculation("1-2"), "-1");
    }

    @Test
    public void calculate_multiplication_correctly() {
        assertEquals(CalculationParser.parseCalculation("2*2"), "4");
    }

    @Test
    public void calculateMultiplicationCorrectly_decimalOverflow() {
        assertEquals(CalculationParser.parseCalculation("1.14*5.14"), "5.86");
    }

    @Test
    public void calculateDivisible_divisionCorrectly() {
        assertEquals(CalculationParser.parseCalculation("2/2"), "1");
    }

    @Test
    public void calculateNonDivisibleDivisionCorrectly_oneDecimal() {
        assertEquals(CalculationParser.parseCalculation("5/2"), "2.5");
    }

    @Test
    public void calculateNonDivisibleDivisionCorrectly_twoDecimal() {
        assertEquals(CalculationParser.parseCalculation("10/8"), "1.25");
    }

    @Test
    public void calculateNonDivisibleDivisionCorrectly_manyDecimal() {
        assertEquals(CalculationParser.parseCalculation("1/8"), "0.13");
    }

    @Test
    public void calculateVonDivisibleDivisionCorrectly_infiniteDecimal() {
        assertEquals(CalculationParser.parseCalculation("10/3"), "3.33");
    }

    @Test
    public void calculate_divisionByZero() {
        Random rand = new Random();
        int numerator = rand.nextInt(Integer.MAX_VALUE);
        assertEquals(CalculationParser.parseCalculation(numerator + "/0"), "∞");
    }

    @Test
    public void calculateComplexExpressions_withoutPrecedence() {
        assertEquals(CalculationParser.parseCalculation("1+2+3"), "6");
        assertEquals(CalculationParser.parseCalculation("1-2-3-4"), "-8");
        assertEquals(CalculationParser.parseCalculation("2*3*2"), "12");
        assertEquals(CalculationParser.parseCalculation("64/8/2/2"), "2");
    }

    @Test
    public void calculateComplexExpressions_withPrecedence() {
        assertEquals(CalculationParser.parseCalculation("1+2*3"), "7");
        assertEquals(CalculationParser.parseCalculation("1*2-3"), "-1");
        assertEquals(CalculationParser.parseCalculation("1-2*3-4"), "-9");
        assertEquals(CalculationParser.parseCalculation("2+3*2"), "8");
        assertEquals(CalculationParser.parseCalculation("64-8/2/2"), "62");
    }

    @Test
    public void calculateComplexExpressions_withWhitespaces() {
        assertEquals(CalculationParser.parseCalculation("1 + 2 * 3"), "7");
        assertEquals(CalculationParser.parseCalculation("1 * 2 -  3"), "-1");
        assertEquals(CalculationParser.parseCalculation(" 1 - 2 * 3 - 4"), "-9");
        assertEquals(CalculationParser.parseCalculation("2 +3 *2"), "8");
        assertEquals(CalculationParser.parseCalculation("64 - 8/2/2"), "62");
    }

    @Test
    public void calculateComplexExpressions_parenthesis() {
        assertEquals(CalculationParser.parseCalculation("(1+2)*3"), "9");
        assertEquals(CalculationParser.parseCalculation("1*(2-3)"), "-1");
        assertEquals(CalculationParser.parseCalculation(" (1-2)*(3-4)"), "1");
        assertEquals(CalculationParser.parseCalculation("2+3*2/(3/4)"), "10");
        assertEquals(CalculationParser.parseCalculation(" 64 -8 /(2 / 2)*2"), "48");
        assertEquals(CalculationParser.parseCalculation(" 64.15 -8.01 /(2.2 / 2)*2"), "49.59");
        assertEquals(CalculationParser.parseCalculation("1.00+1.0+1"), "3");
    }

}
