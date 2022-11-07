package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagIsSecuredPredicate;

class FindTagCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        TagIsSecuredPredicate predicate = new TagIsSecuredPredicate(new Tag("SECURED"));
        FindTagCommand command = new FindTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void equals() {
        TagIsSecuredPredicate firstPredicate = new TagIsSecuredPredicate(new Tag("SECURED"));
        TagIsSecuredPredicate secondPredicate = new TagIsSecuredPredicate(new Tag("POTENTIAL"));

        FindTagCommand firstFindCommand = new FindTagCommand(firstPredicate);
        FindTagCommand secondFindCommand = new FindTagCommand(secondPredicate);

        // same object -> returns true
        assertEquals(firstFindCommand, firstFindCommand);

        // same values -> returns true
        FindTagCommand firstFindCommandCopy = new FindTagCommand(firstPredicate);
        assertEquals(firstFindCommand, firstFindCommandCopy);

        // different types -> returns false
        assertNotEquals(1, firstFindCommand);

        assertNotEquals(1, new ClearCommand());

        // null -> returns false
        assertNotEquals(null, firstFindCommand);

        // different predicate -> returns false
        assertNotEquals(firstFindCommand, secondFindCommand);
    }
}
