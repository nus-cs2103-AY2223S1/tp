package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SpecialisationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Specialisation(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Specialisation(invalidEmail));
    }

    @Test
    public void isValidSpecialisation() {
        // null specialisation
        assertThrows(NullPointerException.class, () -> Specialisation.isValidSpecialisation(null));

        // blank specialisation
        assertFalse(Specialisation.isValidSpecialisation("")); // empty string
        assertFalse(Specialisation.isValidSpecialisation(" ")); // spaces only

        // valid specialisation
        assertTrue(Specialisation.isValidSpecialisation("1"));
        assertTrue(Specialisation.isValidSpecialisation("test"));
        assertTrue(Specialisation.isValidSpecialisation("testing"));
        assertTrue(Specialisation.isValidSpecialisation("testing5"));
        assertTrue(Specialisation.isValidSpecialisation("testing@test"));
        assertTrue(Specialisation.isValidSpecialisation("testing@5"));
        assertTrue(Specialisation.isValidSpecialisation("testingtestingtestingtestingtestingtesting"));
    }
}
