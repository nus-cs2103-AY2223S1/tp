package seedu.nutrigoals.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.tag.Tag;

class CalorieTest {

    @Test
    void isValidCalorie() {
        assertTrue(Calorie.isValidCalorie("0"));
        assertTrue(Calorie.isValidCalorie("1"));
        assertTrue(Calorie.isValidCalorie("100"));
        assertFalse(Calorie.isValidCalorie(Long.MAX_VALUE + ""));
        assertFalse(Calorie.isValidCalorie("-1"));
        assertFalse(Calorie.isValidCalorie("1.0"));
    }

    @Test
    void getCalorieValue() {
        assertEquals(new Calorie("2000").getCalorieValue(), 2000);
        assertEquals(new Calorie("0").getCalorieValue(), 0);
    }

    @Test
    void testEquals() {
        assertEquals(new Calorie("1000"), new Calorie("1000"));
        assertEquals(new Calorie("100"), new Calorie("100"));
        assertNotEquals(new Calorie("100"), new Calorie("10"));
        assertNotEquals(new Tag("breakfast"), new Calorie());
    }
}
