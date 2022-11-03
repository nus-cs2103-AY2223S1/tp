package seedu.nutrigoals.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.tag.Tag;

class CalorieTest {

    private static final Calorie DEFAULT_CALORIE = new Calorie();
    private static final Calorie ZERO_CALORIE = new Calorie("0");
    private static final Calorie SMALL_CALORIE = new Calorie("1");
    private static final Calorie LARGE_CALORIE = new Calorie(Integer.toString(Integer.MAX_VALUE));

    @Test
    public void isValidCalorie() {
        assertTrue(Calorie.isValidCalorie("0"));
        assertTrue(Calorie.isValidCalorie("1"));
        assertTrue(Calorie.isValidCalorie("100"));
        assertFalse(Calorie.isValidCalorie(Long.MAX_VALUE + ""));

        // negative integers not allowed
        assertFalse(Calorie.isValidCalorie("-1"));

        // floating point numbers not allowed
        assertFalse(Calorie.isValidCalorie("1.0"));

        // leading 0s not allowed
        assertFalse(Calorie.isValidCalorie("002000"));
        assertFalse(Calorie.isValidCalorie("00"));
    }

    @Test
    public void getCalorieValue() {
        assertEquals(DEFAULT_CALORIE.getCalorieValue(), 2000);
        assertEquals(ZERO_CALORIE.getCalorieValue(), 0);
    }

    @Test
    public void testEquals() {
        assertEquals(new Calorie("1000"), new Calorie("1000"));
        assertEquals(new Calorie("100"), new Calorie("100"));
        assertNotEquals(new Calorie("100"), new Calorie("10"));
        assertFalse(new Calorie().equals(new Tag("breakfast")));
        assertFalse(new Calorie().equals(null));
    }

    @Test
    public void calculateCalorieDifference_success() {
        int expectedCalorie = 100;
        Calorie calorie1 = new Calorie("250");
        Calorie calorie2 = new Calorie("150");
        assertEquals(expectedCalorie, calorie1.calculateDifference(calorie2));

        expectedCalorie = -100;
        assertEquals(expectedCalorie, calorie2.calculateDifference(calorie1));
    }

    @Test
    public void addCalorie_success() {
        Calorie expectedCalorie = new Calorie("300");
        Calorie calorie1 = new Calorie("160");
        Calorie calorie2 = new Calorie("140");
        assertEquals(expectedCalorie, calorie1.addCalorie(calorie2));
    }

    @Test
    public void isCalorieSumTooLarge_largeSum_returnTrue() {
        assertTrue(SMALL_CALORIE.isCalorieSumTooLarge(LARGE_CALORIE));
    }

    @Test
    public void isCalorieSumTooLarge_smallSum_returnFalse() {
        assertFalse(SMALL_CALORIE.isCalorieSumTooLarge(SMALL_CALORIE));
    }

    @Test
    public void calculateProportion_nonZeroDenominator_success() {
        assertEquals(2000, DEFAULT_CALORIE.calculateProportion(SMALL_CALORIE));
    }

    @Test
    public void calculateProportion_zeroDenominator_success() {
        assertEquals(1, DEFAULT_CALORIE.calculateProportion(ZERO_CALORIE));
    }
}
