package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

/**
 * Test cases for {@code DisplayedPerson}
 */
public class DisplayedPersonTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private DisplayedPerson defaultPerson = new DisplayedPerson(GEORGE);

    @Test
    public void setDisplayedPerson_nullPerson_nullPointerExceptionThrown() {
        try {
            defaultPerson.setDisplayedPerson((Person) null, getTypicalAddressBook());
            fail(); // should not reach this step
        } catch (NullPointerException npe) {
            assert true;
        }
    }

    /**
     * pass if PersonNotFoundException is thrown when attempting to set a {@code Person} not
     * found in addressbook.
     */
    @Test
    public void setDisplayedPerson_invalidPerson_personNotFoundExceptionThrown() {
        try {
            Person invalidPerson = new PersonBuilder().withName("Person")
                    .withAddress("123, Jurong West Ave 6, #08-111").withEmail("Person@example.com")
                    .withPhone("12345678")
                    .build();
            defaultPerson.setDisplayedPerson(invalidPerson, getTypicalAddressBook());
            fail(); // should not reach this step
        } catch (PersonNotFoundException pnfe) {
            assert true;
        }
    }

}
