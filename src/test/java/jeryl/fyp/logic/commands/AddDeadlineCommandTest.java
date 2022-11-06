package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.core.Messages.MESSAGE_COMPLETED_PROJECT;
import static jeryl.fyp.commons.core.Messages.MESSAGE_DUPLICATE_DEADLINE;
import static jeryl.fyp.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.DeadlineList;
import jeryl.fyp.model.student.DeadlineListTemplate;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.exceptions.StudentNotFoundException;
import jeryl.fyp.model.util.SampleDataUtil;
import jeryl.fyp.testutil.StudentBuilder;

public class AddDeadlineCommandTest {

    @Test
    public void constructor_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDeadlineCommand(null, null));
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        ModelManager model = new ModelManager();
        AddDeadlineCommandTest.DeadlineListStubAcceptingDeadlineAdded deadlineListStub = new AddDeadlineCommandTest
                .DeadlineListStubAcceptingDeadlineAdded();
        Deadline validDeadline = SampleDataUtil.getDeadline("Valid name", "01-01-1970 00:00");
        Student validStudent = new StudentBuilder().build();
        model.addStudent(validStudent);
        deadlineListStub.add(validDeadline);
        CommandResult commandResult = new AddDeadlineCommand(validDeadline, validStudent.getStudentId())
                .execute(model);

        assertEquals(String.format(AddDeadlineCommand.MESSAGE_SUCCESS, validDeadline),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDeadline), deadlineListStub.deadlinesAdded);
    }

    @Test
    public void execute_duplicateDeadline_throwsCommandException() throws CommandException {
        ModelManager model = new ModelManager();
        Deadline validDeadline = SampleDataUtil.getDeadline("Valid name", "01-01-1970 00:00");
        Student validStudent = new StudentBuilder().build();
        model.addStudent(validStudent);
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(validDeadline, validStudent.getStudentId());
        addDeadlineCommand.execute(model);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_DEADLINE, () ->
                addDeadlineCommand.execute(model));
    }

    @Test
    public void execute_completedStudent_throwsCommandException() throws CommandException {
        ModelManager model = new ModelManager();
        Deadline validDeadline = SampleDataUtil.getDeadline("Valid name", "01-01-1970 00:00");
        Student validStudent = new StudentBuilder().withProjectStatus("DONE").build();
        model.addStudent(validStudent);
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(validDeadline, validStudent.getStudentId());

        assertThrows(CommandException.class, MESSAGE_COMPLETED_PROJECT, () ->
                addDeadlineCommand.execute(model));
    }

    @Test
    public void execute_studentNotFound_throwsCommandException() throws CommandException {
        ModelManager model = new ModelManager();
        Deadline validDeadline = SampleDataUtil.getDeadline("Valid name", "01-01-1970 00:00");
        Student validStudent = new StudentBuilder().withStudentId("A0123456X").build();
        Student anotherValidStudent = new StudentBuilder().withStudentId("A0123456Y").build();
        model.addStudent(validStudent);
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(validDeadline,
                anotherValidStudent.getStudentId());

        assertThrows(StudentNotFoundException.class, MESSAGE_STUDENT_NOT_FOUND, () ->
                addDeadlineCommand.execute(model));
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withStudentId("A1111111U").build();
        Student bob = new StudentBuilder().withStudentId("A1111112U").build();
        Deadline aliceDeadline = SampleDataUtil.getDeadline("Task 1", "01-01-1970 00:00");
        Deadline bobDeadline = SampleDataUtil.getDeadline("Task 2", "01-01-1970 00:00");
        AddDeadlineCommand addAliceTaskCommand = new AddDeadlineCommand(aliceDeadline, alice.getStudentId());
        AddDeadlineCommand addBobTaskCommand = new AddDeadlineCommand(bobDeadline, bob.getStudentId());

        // same object -> returns true
        assertTrue(addAliceTaskCommand.equals(addAliceTaskCommand));

        // same values -> returns true
        AddDeadlineCommand addAliceTaskCommandCopy = new AddDeadlineCommand(aliceDeadline, alice.getStudentId());
        assertTrue(addAliceTaskCommand.equals(addAliceTaskCommandCopy));

        // different types -> returns false
        assertFalse(addAliceTaskCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceTaskCommand.equals(null));

        // different student -> returns false
        assertFalse(addAliceTaskCommand.equals(addBobTaskCommand));
    }
    /**
     * A default DeadlineList stub that have all of the methods failing.
     */
    private class DeadlineListStub implements DeadlineListTemplate {
        @Override
        public boolean contains(Deadline toCheck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void add(Deadline toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeadline(Deadline target, Deadline editedDeadline) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void remove(Deadline toRemove) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeadlines(DeadlineList replacement) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeadlines(List<Deadline> deadlines) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Deadline> asUnmodifiableObservableList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Deadline getDeadlineByName(String taskName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Index getIndexByName(String taskName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Deadline getDeadlineByRank(Integer rank) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Iterator<Deadline> iterator() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int size() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A DeadlineList stub that always accept the Deadline being added.
     */
    private class DeadlineListStubAcceptingDeadlineAdded extends DeadlineListStub {
        final ArrayList<Deadline> deadlinesAdded = new ArrayList<>();

        @Override
        public boolean contains(Deadline deadline) {
            requireNonNull(deadline);
            return deadlinesAdded.stream().anyMatch(deadline::isSameDeadlineName);
        }

        @Override
        public void add(Deadline deadline) {
            requireNonNull(deadline);
            deadlinesAdded.add(deadline);
        }
    }

}
