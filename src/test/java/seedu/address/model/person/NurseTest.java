package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ELLE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NurseTest {

    @Test
    public void toStringTest() {
        Person elle = new PersonBuilder(ELLE).build();
        String expectedToStringElle = "Category: N; Uid: 5; Name: Elle Meyer; Phone: 9482224; "
                + "Email: werner@example.com; Gender: F; Address: michegan ave; "
                + "Unavailable Dates: 11/11/2022; Home Visits: 15/10/2022 10:00 : [UID] 6;";
        assertEquals(expectedToStringElle, elle.toString());
    }
}
