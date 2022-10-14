package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        assertTrue(Tag.isValidTagName("abcd"));
        assertTrue(Tag.isValidTagName("1234"));
        assertTrue(Tag.isValidTagName("ab34"));
        assertFalse(Tag.isValidTagName("Vice-President"));
        assertFalse(Tag.isValidTagName("Vice President"));
        assertTrue(Tag.isValidTagName("VicePresident"));
    }

    @Test
    public void shallowCopy_copyNotSameButEqual() {
        Tag tag = new Tag("Operations");

        Person personA = new PersonBuilder().withName("PersonA").build();
        Person personB = new PersonBuilder().withName("PersonB").build();
        tag.addPerson(personA);
        tag.addPerson(personB);

        Tag copy = tag.shallowCopy();

        assertNotSame(tag, copy);
        assertEquals(tag, copy);

        assertEquals(tag.getDeepCopiedPersonList(), copy.getDeepCopiedPersonList());
    }

    @Test
    public void getUnmodifiableCopiedPersonList_modifyPerson_doesNotMutateOriginalPerson() {
        Person personA = new PersonBuilder().withName("PersonA").build();

        Tag tag = new Tag("Operations");
        tag.addPerson(personA);
        Person reference = tag.getDeepCopiedPersonList().get(0);

        assertEquals(reference, personA);
        assertNotSame(reference, personA);

        reference.getLoan().increaseLoan(100);

        assertNotEquals(personA.getLoan().getAmount(), reference.getLoan().getAmount());
    }

}
