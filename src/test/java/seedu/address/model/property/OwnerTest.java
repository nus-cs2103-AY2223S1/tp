package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;

public class OwnerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Owner(null, null));
    }

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Owner(null, new Phone("91234567")));
    }

    @Test
    public void constructor_nullPhone_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Owner(new Name("John Smith"), null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Owner(new Name(invalidName), new Phone("91234567")));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {

        // Invalid phone: Contains non-numerical characters
        assertThrows(IllegalArgumentException.class, () -> new Owner(new Name("John Smith"), new Phone("9123456A")));

        // Invalid phone: Two digits long
        assertThrows(IllegalArgumentException.class, () -> new Owner(new Name("John Smith"), new Phone("12")));

        // Invalid phone: Single character
        assertThrows(IllegalArgumentException.class, () -> new Owner(new Name("John Smith"), new Phone("A")));
    }

    @Test
    public void testEquals() {

        Owner owner1 = new Owner(new Name("John Smith"), new Phone("91234567"));
        Owner owner2 = new Owner(new Name("John Smith"), new Phone("91234567"));
        Owner owner3 = new Owner(new Name("Jane Smith"), new Phone("91234567"));
        Owner owner4 = new Owner(new Name("John Smith"), new Phone("81234567"));


        assertEquals(owner1, owner1); // same object
        assertEquals(owner1, owner2); // different object, same parameters
        assertNotEquals(owner1, owner3); // different name, same phone
        assertNotEquals(owner1, owner4); // same name, different phone
        assertNotEquals(owner1, null); // cannot compare to null owner

    }

}
