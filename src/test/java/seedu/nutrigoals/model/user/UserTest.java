package seedu.nutrigoals.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.testutil.UserBuilder;

public class UserTest {

    @Test
    public void constructor_nullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new User(null, null, null, null, null));
    }

    @Test
    public void equals() {
        User userA = new UserBuilder().build();
        User userB = new UserBuilder().withHeight("123").build();

        assertTrue(userA.equals(userA));
        assertFalse(userA.equals(userB));
        assertFalse(userA.equals(null));
    }

    @Test
    public void testGetWeight() {
        User userA = new UserBuilder().build();
        assertEquals(userA.getWeight(), new Weight(UserBuilder.DEFAULT_WEIGHT));
    }

    @Test
    public void testGetIdealWeight() {
        User userA = new UserBuilder().build();
        assertEquals(userA.getIdealWeight(), new Weight(UserBuilder.DEFAULT_IDEAL));
    }

    @Test
    public void testGetHeight() {
        User userA = new UserBuilder().build();
        assertEquals(userA.getHeight(), new Height(UserBuilder.DEFAULT_HEIGHT));
    }

    @Test
    public void testGetGender() {
        User userA = new UserBuilder().build();
        assertEquals(userA.getGender(), new Gender(UserBuilder.DEFAULT_GENDER));
    }

    @Test
    public void testGetAge() {
        User userA = new UserBuilder().withAge("20").build();
        assertEquals(userA.getAge(), new Age(UserBuilder.DEFAULT_AGE));
    }

    @Test
    public void validUser_calculateSuggestedCalories_success() {
        User user = new UserBuilder().withIdeal("55").withHeight("170")
                .withGender("F").withWeight("60").withAge("30").build();
        Calorie expectedSuggestedCalorie = new CalorieStub("1626");
        assertEquals(expectedSuggestedCalorie, user.calculateSuggestedCalorieIntake());

        // boundary values
        user = new UserBuilder().withIdeal("10").withHeight("100").withGender("M")
                .withWeight("10").withAge("99").build();
        expectedSuggestedCalorie = new CalorieStub("42");
        assertEquals(expectedSuggestedCalorie, user.calculateSuggestedCalorieIntake());

        user = new UserBuilder().withIdeal("10").withHeight("100").withGender("F")
                .withWeight("10").withAge("99").build();
        expectedSuggestedCalorie = new CalorieStub("567");
        assertEquals(expectedSuggestedCalorie, user.calculateSuggestedCalorieIntake());

        // boundary and non-boundary
        user = new UserBuilder().withIdeal("50").withHeight("100").withWeight("60")
                .withGender("f").withAge("1").build();
        expectedSuggestedCalorie = new CalorieStub("1576");
        assertEquals(expectedSuggestedCalorie, user.calculateSuggestedCalorieIntake());

        user = new UserBuilder().withIdeal("50").withHeight("100").withWeight("60")
                .withGender("m").withAge("1").build();
        expectedSuggestedCalorie = new CalorieStub("1497");
        assertEquals(expectedSuggestedCalorie, user.calculateSuggestedCalorieIntake());
    }

    private static class CalorieStub extends Calorie {
        private String value;

        public CalorieStub(String calorie) {
            value = calorie;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Calorie)) {
                return false;
            }

            Calorie c = (Calorie) obj;
            return getValue().equals(c.getValue());
        }

        @Override
        public String getValue() {
            return value;
        }
    }
}
