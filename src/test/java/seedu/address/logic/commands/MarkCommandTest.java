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

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Class;
import seedu.address.model.student.Mark;
import seedu.address.model.student.Money;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkCommand.
 */
public class MarkCommandTest {

    private static final Class VALID_CLASS = new Class(LocalDate.of(2022, 10, 22),
             LocalTime.of(13, 0), LocalTime.of(15, 0));

    private Model model = new ModelManager(getTypicalTeachersPet(), new UserPrefs());

    @Test
    public void execute_invalidStudentIndexScheduledList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_STUDENT_SCHEDULE_INDEX);
    }

    @Test
    public void create_newMarkedStudent_success() throws CommandException {

        // initialize Amy to be not marked yet
        Student studentToMark = new StudentBuilder(AMY).withMark(Boolean.FALSE).build();
        Student expectedMarkedStudent = new Student(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), VALID_CLASS.addDays(7), AMY.getMoneyOwed().addTo(AMY.getRatesPerClass()),
                AMY.getMoneyPaid(), AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags(),
                new Mark(Boolean.TRUE), VALID_CLASS);

        studentToMark.setClass(VALID_CLASS);
        studentToMark.setDisplayClass(VALID_CLASS);

        Student markedStudent = MarkCommand.createMarkedStudent(studentToMark);
        assertEquals(expectedMarkedStudent, markedStudent);
    }

    @Test
    public void create_existingMarkedStudent_success() throws CommandException {
        // initialize Amy to be marked
        Student studentToMark = new StudentBuilder(AMY).withMark(Boolean.TRUE).build();

        assertEquals(studentToMark, MarkCommand.createMarkedStudent(studentToMark));
    }

    @Test
    public void create_markedStudentWithHighDebt_failure() {
        Student heavyDebtor = new Student(AMY.getName(), AMY.getPhone(), AMY.getNokPhone(), AMY.getEmail(),
                AMY.getAddress(), VALID_CLASS, new Money(Integer.MAX_VALUE),
                AMY.getMoneyPaid(), AMY.getRatesPerClass(), AMY.getAdditionalNotes(), AMY.getTags(),
                new Mark(Boolean.FALSE), VALID_CLASS);

        assertThrows(CommandException.class, () -> MarkCommand.createMarkedStudent(heavyDebtor));

    }

    @Test
    public void equals() {
        final MarkCommand standardCommand = new MarkCommand(INDEX_FIRST_STUDENT);

        // same values -> returns true
        MarkCommand commandWithSameValues = new MarkCommand(INDEX_FIRST_STUDENT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_SECOND_STUDENT)));
    }

}
