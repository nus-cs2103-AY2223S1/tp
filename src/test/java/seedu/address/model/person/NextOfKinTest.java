package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.JADON;

import org.junit.jupiter.api.Test;

public class NextOfKinTest {

    @Test
    public void toStringTest() {
        NextOfKin jadon = JADON;
        String expectedToStringJadon = "Name: Jadon Sacho; Phone: 81234567; Email: jadon@example.com;";
        assertEquals(expectedToStringJadon, jadon.toString());
    }
}
