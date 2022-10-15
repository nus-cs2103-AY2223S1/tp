package seedu.studmap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.SortCommand.MESSAGE_SORT_SUCCESS;
import static seedu.studmap.testutil.TypicalStudents.ALICE;
import static seedu.studmap.testutil.TypicalStudents.BENSON;
import static seedu.studmap.testutil.TypicalStudents.CARL;
import static seedu.studmap.testutil.TypicalStudents.DANIEL;
import static seedu.studmap.testutil.TypicalStudents.ELLE;
import static seedu.studmap.testutil.TypicalStudents.FIONA;
import static seedu.studmap.testutil.TypicalStudents.GEORGE;
import static seedu.studmap.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.attribute.Attribute;
import seedu.studmap.model.order.Order;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SortCommand sortFirstCommand = new SortCommand(new Attribute("name"), new Order("asc"));
        SortCommand sortSecondCommand = new SortCommand(new Attribute("name"), new Order("dsc"));

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(new Attribute("name"), new Order("asc"));
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

    @Test
    public void execute_nameAscending_success() {
        String expectedMessage = String.format(MESSAGE_SORT_SUCCESS, "[name]", "ascending");
        SortCommand sortCommand = new SortCommand(new Attribute("name"), new Order("asc"));
        expectedModel.sortFilteredStudentList(new Attribute("name"), new Order("asc"));
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredStudentList());
    }

    @Test
    public void execute_nameDescending_success() {
        String expectedMessage = String.format(MESSAGE_SORT_SUCCESS, "[name]", "descending");
        SortCommand sortCommand = new SortCommand(new Attribute("name"), new Order("dsc"));
        expectedModel.sortFilteredStudentList(new Attribute("name"), new Order("dsc"));
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE),
                model.getFilteredStudentList());
    }

    @Test
    public void execute_phoneAscending_success() {
        String expectedMessage = String.format(MESSAGE_SORT_SUCCESS, "[phone]", "ascending");
        SortCommand sortCommand = new SortCommand(new Attribute("phone"), new Order("asc"));
        expectedModel.sortFilteredStudentList(new Attribute("phone"), new Order("asc"));
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ALICE, ELLE, FIONA, GEORGE, CARL, BENSON),
                model.getFilteredStudentList());
    }

    @Test
    public void execute_phoneDescending_success() {
        String expectedMessage = String.format(MESSAGE_SORT_SUCCESS, "[phone]", "descending");
        SortCommand sortCommand = new SortCommand(new Attribute("phone"), new Order("dsc"));
        expectedModel.sortFilteredStudentList(new Attribute("phone"), new Order("dsc"));
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, GEORGE, FIONA, ELLE, ALICE, DANIEL),
                model.getFilteredStudentList());
    }
}
