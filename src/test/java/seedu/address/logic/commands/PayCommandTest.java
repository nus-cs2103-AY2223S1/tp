package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.getTypicalTeachersPet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Money;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for PayCommand.
 */
public class PayCommandTest {

    private static final Money VALID_MONEY = new Money(300);
    private Model model = new ModelManager(getTypicalTeachersPet(), new UserPrefs());

    @Test
    public void execute_invalidStudentIndexScheduledList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        PayCommand markCommand = new PayCommand(outOfBoundIndex, VALID_MONEY);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_STUDENT_SCHEDULE_INDEX);
    }

    @Test
    public void create_newPaidStudent_success() throws CommandException {
        Student studentPaying = new StudentBuilder(AMY).withMoneyOwed(VALID_MONEY.value).build();
        Student expectedPaidStudent = new Student(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), AMY.getAClass(), new Money(0),
                AMY.getMoneyPaid().addTo(VALID_MONEY), AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags(),
                AMY.getMarkStatus(), AMY.getDisplayedClass());

        Student paidStudent = PayCommand.createPaidStudent(studentPaying, VALID_MONEY);
        assertEquals(expectedPaidStudent, paidStudent);
    }

    @Test
    public void create_paidStudentWithMaximumPaidAmount_failure() {
        Student student = new Student(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), AMY.getAClass(), new Money(Integer.MAX_VALUE),
                VALID_MONEY, AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags(),
                AMY.getMarkStatus(), AMY.getDisplayedClass());

        assertThrows(CommandException.class, () -> PayCommand.createPaidStudent(student, new Money(Integer.MAX_VALUE)));
    }

    @Test
    public void create_paidStudentWithZeroDebt_failure() {
        Student student = new Student(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), AMY.getAClass(), new Money(0),
                VALID_MONEY, AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags(),
                AMY.getMarkStatus(), AMY.getDisplayedClass());

        assertThrows(CommandException.class, () -> PayCommand.createPaidStudent(student, VALID_MONEY));
    }

    @Test
    public void create_paidStudentWithLowDebt_failure() {
        Student student = new Student(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), AMY.getAClass(), new Money(299),
                VALID_MONEY, AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags(),
                AMY.getMarkStatus(), AMY.getDisplayedClass());

        assertThrows(CommandException.class, () -> PayCommand.createPaidStudent(student, VALID_MONEY));
    }

    @Test
    public void equals() {
        final PayCommand standardCommand = new PayCommand(INDEX_FIRST_STUDENT, VALID_MONEY);

        // same values -> returns true
        PayCommand commandWithSameValues = new PayCommand(INDEX_FIRST_STUDENT, VALID_MONEY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PayCommand(INDEX_SECOND_STUDENT, VALID_MONEY)));

        // different amount paid -> returns false
        assertFalse(standardCommand.equals(new PayCommand(INDEX_FIRST_STUDENT, new Money(100))));
    }
}
