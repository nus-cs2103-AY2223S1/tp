package seedu.nutrigoals.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class BmiTest {

    private static final Height DEFAULT_HEIGHT = new HeightStub("170");
    private static final Weight DEFAULT_WEIGHT = new WeightStub("70");
    private static final double DEFAULT_BMI = 70 / (1.7 * 1.7);

    @Test
    public void constructor_defaultConstructorPass() {
        Bmi expectedBmi = new Bmi(DEFAULT_HEIGHT, DEFAULT_WEIGHT);
        Bmi actualBmi = new Bmi();
        assertEquals(actualBmi, expectedBmi);
    }

    @Test
    public void calculateBmi_success() {
        Bmi bmi = new Bmi(DEFAULT_HEIGHT, DEFAULT_WEIGHT);
        assertEquals(bmi.calculateBmi(), DEFAULT_BMI);
    }

    @Test
    public void equals() {
        Bmi bmi = new Bmi(new HeightStub("165"), new WeightStub("45"));
        Bmi bmi1 = new Bmi(new HeightStub("165"), new WeightStub("45"));
        Bmi bmi2 = new Bmi(new HeightStub("170"), new WeightStub("65"));
        assertNotEquals(null, bmi);
        assertNotEquals(bmi, bmi2);
        assertEquals(bmi, bmi1);
        assertEquals(bmi, bmi);
        assertFalse(bmi.equals(5));
    }

    private static class WeightStub extends Weight {

        private final String value;

        public WeightStub(String weight) {
            value = weight;
        }

        @Override
        public int getWeight() {
            return Integer.parseInt(value);
        }

    }

    private static class HeightStub extends Height {

        private final String value;

        public HeightStub(String height) {
            value = height;
        }

        @Override
        public double getHeight() {
            return Integer.parseInt(value) / 100.0;
        }

    }
}
