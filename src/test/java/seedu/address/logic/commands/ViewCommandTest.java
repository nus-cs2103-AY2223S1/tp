package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_NAME_VIEW;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_FULL_VIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameIsKeywordsPredicate;

public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameIsKeywordsPredicate firstPredicate =
                new NameIsKeywordsPredicate(Collections.singletonList(VALID_NAME_AMY));
        NameIsKeywordsPredicate secondPredicate =
                new NameIsKeywordsPredicate(Collections.singletonList(VALID_NAME_BOB));

        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns fqalse
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = MESSAGE_INVALID_PERSON_NAME_VIEW;
        NameIsKeywordsPredicate predicate = preparePredicate(" ");
        ViewCommand command = new ViewCommand(predicate);
        model.updateFilteredPersonList(predicate);
        assertCommandFailure(command, model, expectedMessage);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_noPersonsFound() {
        String expectedMessage = MESSAGE_INVALID_PERSON_NAME_VIEW;
        NameIsKeywordsPredicate predicate = preparePredicate("Alice Pauline Benson Meier");
        ViewCommand command = new ViewCommand(predicate);
        model.updateFilteredPersonList(predicate);
        assertCommandFailure(command, model, expectedMessage);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_ignoreCase_singlePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSON_FULL_VIEW, "alice Pauline");
        NameIsKeywordsPredicate ignoreCasePredicate = preparePredicate("alIcE PaUlIne");
        NameIsKeywordsPredicate predicate = preparePredicate("Alice Pauline");
        ViewCommand command = new ViewCommand(ignoreCasePredicate);
        model.updateFilteredPersonList(ignoreCasePredicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code NameIsKeywordsPredicate}.
     */
    private NameIsKeywordsPredicate preparePredicate(String userInput) {
        return new NameIsKeywordsPredicate(List.of(userInput));
    }
}
