package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class WeightTest {

    @Test
    public void getValue() {
        Weight weight = new Weight(5.5);
        assertEquals(weight.getValue(), 5.5);
    }

    @Test
    public void equals_sameObject() {
        Weight weight = new Weight(5.5);
        assertEquals(weight, weight);
    }

    @Test
    public void equals_differentObjectSameValue() {
        Weight weight1 = new Weight(5.5);
        Weight weight2 = new Weight(5.5);
        assertEquals(weight1, weight2);
    }

    @Test
    public void equals_differentValues() {
        Weight weight1 = new Weight(5.5);
        Weight weight2 = new Weight(6.5);
        assertNotEquals(weight1, weight2);
    }

    @Test
    public void hashCodeTest() {
        Weight weight1 = new Weight(5.5);
        Weight weight2 = new Weight(6.5);
        assertEquals(weight1.hashCode(), Double.hashCode(5.5));
        assertNotEquals(weight1.hashCode(), weight2.hashCode());
    }

    @Test
    public void toStringTest() {
        Weight weight = new Weight(5.5);
        String expected = Double.valueOf(5.5) + " kg";
        assertEquals(weight.toString(), expected);
    }
}
