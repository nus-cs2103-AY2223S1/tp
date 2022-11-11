package seedu.modquik.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modquik.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.modquik.commons.core.GuiSettings;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.model.ModQuik;
import seedu.modquik.model.Model;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.ReadOnlyUserPrefs;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.tutorial.Tutorial;
import seedu.modquik.testutil.ReminderBuilder;

public class AddReminderCommandTest {

    @Test
    public void constructor_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddReminderCommand(null));
    }

    @Test
    public void execute_reminderAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingReminderAdded modelStub = new ModelStubAcceptingReminderAdded();
        Reminder validReminder = new ReminderBuilder().build();

        CommandResult commandResult = new AddReminderCommand(validReminder).execute(modelStub);

        assertEquals(String.format(AddReminderCommand.MESSAGE_SUCCESS, validReminder),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validReminder), modelStub.remindersAdded);
    }

    @Test
    public void execute_duplicateReminder_throwsCommandException() {
        Reminder validReminder = new ReminderBuilder().build();
        AddReminderCommand addReminderCommand = new AddReminderCommand(validReminder);
        ModelStub modelStub = new ModelStubWithReminder(validReminder);

        assertThrows(CommandException.class, AddReminderCommand.MESSAGE_DUPLICATE_REMINDER, () ->
                addReminderCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Reminder markMidterms = new ReminderBuilder().withName("Mark Midterms").build();
        Reminder markFinals = new ReminderBuilder().withName("Mark Finals").build();
        AddReminderCommand markMidtermsCommand = new AddReminderCommand(markMidterms);
        AddReminderCommand markFinalsCommand = new AddReminderCommand(markFinals);

        // same object -> returns true
        assertTrue(markMidtermsCommand.equals(markMidtermsCommand));

        // same values -> returns true
        AddReminderCommand markMidtermsCommandCopy = new AddReminderCommand(markMidterms);
        assertTrue(markMidtermsCommand.equals(markMidtermsCommandCopy));

        // different types -> returns false
        assertFalse(markMidtermsCommand.equals(1));

        // null -> returns false
        assertFalse(markMidtermsCommand.equals(null));

        // different person -> returns false
        assertFalse(markMidtermsCommand.equals(markFinalsCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getModQuikFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModQuikFilePath(Path modQuikFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModQuik(ReadOnlyModQuik modQuik) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModQuik getModQuik() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<PieChart.Data> getStudentGradeChartData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasReminder(Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addReminder(Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReminder(Reminder target, Reminder editedReminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteReminder(Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortReminderByPriority() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortReminderByDeadline() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Reminder> getFilteredReminderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredReminderList(Predicate<Reminder> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingTutorial(Tutorial toCheck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingTutorialExcept(Tutorial toCheck, Tutorial exception) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutorial(Tutorial target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutorial(Tutorial tutorialToEdit, Tutorial editedTutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tutorial> getFilteredTutorialList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasConsultation(Consultation consultation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingConsultation(Consultation toCheck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingConsultationExcept(Consultation toCheck, Consultation exception) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteConsultation(Consultation target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addConsultation(Consultation consultation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetConsultations() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetTutorials() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetReminders() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Consultation> getFilteredConsultationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredConsultationList(Predicate<Consultation> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setConsultation(Consultation target, Consultation editedConsultation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetStudents() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markReminder(Reminder reminderToMark) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean reminderIsMarked(Reminder reminderToMark) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unmarkReminder(Reminder reminderToUnmark) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single reminder.
     */
    private class ModelStubWithReminder extends ModelStub {
        private final Reminder reminder;

        ModelStubWithReminder(Reminder reminder) {
            requireNonNull(reminder);
            this.reminder = reminder;
        }

        @Override
        public boolean hasReminder(Reminder reminder) {
            requireNonNull(reminder);
            return this.reminder.isSameReminder(reminder);
        }
    }

    /**
     * A Model stub that always accept the reminder being added.
     */
    private class ModelStubAcceptingReminderAdded extends ModelStub {
        final ArrayList<Reminder> remindersAdded = new ArrayList<>();

        @Override
        public boolean hasReminder(Reminder reminder) {
            requireNonNull(reminder);
            return remindersAdded.stream().anyMatch(reminder::isSameReminder);
        }

        @Override
        public void addReminder(Reminder reminder) {
            requireNonNull(reminder);
            remindersAdded.add(reminder);
        }

        @Override
        public ReadOnlyModQuik getModQuik() {
            return new ModQuik();
        }
    }

}
