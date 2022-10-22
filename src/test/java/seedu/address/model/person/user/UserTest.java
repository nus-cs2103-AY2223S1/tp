package seedu.address.model.person.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalPersons.XAVIER;
import static seedu.address.testutil.TypicalPersons.ZEPHYR;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.UserBuilder;

public class UserTest {

    private final EmptyUser emptyUser = new EmptyUser();

    @Test
    public void existingUserEquals() {
        // same values -> returns true
        User zephyrCopy = new UserBuilder(ZEPHYR).build();
        assertTrue(ZEPHYR.equals(zephyrCopy));

        // same object -> returns true
        assertTrue(ZEPHYR.equals(ZEPHYR));

        // null -> returns false
        assertFalse(ZEPHYR.equals(null));

        // different type -> returns false
        assertFalse(ZEPHYR.equals(5));

        // different user -> returns false
        assertFalse(ZEPHYR.equals(XAVIER));

        // empty user -> returns false
        assertFalse(ZEPHYR.equals(emptyUser));

        // different name -> returns false
        User editedZephyr = new UserBuilder(ZEPHYR).withName(VALID_NAME_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different phone -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different email -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different address -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different github -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withGithub(VALID_GITHUB_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

    }

    @Test
    public void emptyUserEquals() {
        // same object -> returns true
        assertTrue(emptyUser.equals(emptyUser));

        // both empty user -> returns true
        assertTrue(emptyUser.equals(new EmptyUser()));

        // null -> returns false
        assertFalse(emptyUser.equals(null));

        // different type -> returns false
        assertFalse(emptyUser.equals(5));
    }
}
