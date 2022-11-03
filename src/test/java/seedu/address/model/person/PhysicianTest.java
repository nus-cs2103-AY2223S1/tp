package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.HOUSE;

import org.junit.jupiter.api.Test;

public class PhysicianTest {

    @Test
    public void toStringTest() {
        Physician house = HOUSE;
        String expectedToStringHouse = "Attending Physician Name: Dr House; Phone: 91234567; Email: house@example.com;";
        assertEquals(expectedToStringHouse, house.toString());
    }
}
