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
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.order.Order;
import seedu.studmap.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudMap(), new UserPrefs());

    @Test
    public void equals() {
        SortCommand sortFirstCommand = new SortCommand(Comparator.comparing(Student::getNameString),
                "name",
                Order.ORDER_ASC);
        SortCommand sortFirstCommandCopy = new SortCommand(Comparator.comparing(Student::getNameString),
                "name",
                Order.ORDER_ASC);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));
    }

    @Test
    public void execute_nameAscending_success() {
        String expectedMessage = String.format(MESSAGE_SORT_SUCCESS, "name", "ascending");
        SortCommand sortCommand = new SortCommand(Comparator.comparing(Student::getNameString),
                "name",
                Order.ORDER_ASC);
        expectedModel.sortFilteredStudentList(Comparator.comparing(Student::getNameString), Order.ORDER_ASC);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredStudentList());
    }

    @Test
    public void execute_nameDescending_success() {
        String expectedMessage = String.format(MESSAGE_SORT_SUCCESS, "name", "descending");
        SortCommand sortCommand = new SortCommand(Comparator.comparing(Student::getNameString),
                "name",
                Order.ORDER_DSC);
        expectedModel.sortFilteredStudentList(Comparator.comparing(Student::getNameString), Order.ORDER_DSC);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE),
                model.getFilteredStudentList());
    }

    @Test
    public void execute_phoneAscending_success() {
        String expectedMessage = String.format(MESSAGE_SORT_SUCCESS, "phone", "ascending");
        SortCommand sortCommand = new SortCommand(Comparator.comparing(Student::getPhoneString),
                "phone",
                Order.ORDER_ASC);
        expectedModel.sortFilteredStudentList(Comparator.comparing(Student::getPhoneString),
                Order.ORDER_ASC);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ALICE, ELLE, FIONA, GEORGE, CARL, BENSON),
                model.getFilteredStudentList());
    }

    @Test
    public void execute_phoneDescending_success() {
        String expectedMessage = String.format(MESSAGE_SORT_SUCCESS, "phone", "descending");
        SortCommand sortCommand = new SortCommand(Comparator.comparing(Student::getPhoneString),
                "phone",
                Order.ORDER_DSC);
        expectedModel.sortFilteredStudentList(Comparator.comparing(Student::getPhoneString),
                Order.ORDER_DSC);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, GEORGE, FIONA, ELLE, ALICE, DANIEL),
                model.getFilteredStudentList());
    }
}
