package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.AdditionalNotes;
import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Email;
import seedu.address.model.student.Mark;
import seedu.address.model.student.Money;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.storage.ClassStorage;

/**
 * Marks an existing student as present for his/her class.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the student present for the upcoming lesson "
            + "by the index number shown in the schedule list.\n"
            + "Parameters: INDEX (must be positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Student has been marked as present";

    public static final String MESSAGE_MARKED_PREVIOUSLY = "Student has been marked previously";

    public static final String MESSAGE_CLEAR_STUDENT_DEBT = "\nPlease clear the debt of the student first.";

    public static final int DAYS_IN_A_WEEK = 7;

    private final Index targetIndex;

    /**
     * Constructs a new MarkCommand for marking a student as present.
     *
     * @param targetIndex the index of the student in the schedule list.
     */
    public MarkCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredScheduleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_SCHEDULE_INDEX);
        }

        Student studentToMark = lastShownList.get(targetIndex.getZeroBased());
        Student markedStudent = createMarkedStudent(studentToMark);

        if (markedStudent.equals(studentToMark)) {
            throw new CommandException(MESSAGE_MARKED_PREVIOUSLY);
        }

        ClassStorage.saveClass(markedStudent, this.targetIndex.getZeroBased());
        ClassStorage.removeExistingClass(studentToMark);

        model.setStudent(studentToMark, markedStudent);

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates and returns a marked {@code Student} with the details of {@code studentToMark}.
     */
    static Student createMarkedStudent(Student studentToMark) throws CommandException {
        assert studentToMark != null;

        if (studentToMark.getMarkStatus().isMarked() == true) {
            return studentToMark;
        }

        Name currentName = studentToMark.getName();
        Phone currentPhone = studentToMark.getPhone();
        Phone currentNokPhone = studentToMark.getNokPhone();
        Email currentEmail = studentToMark.getEmail();
        Address currentAddress = studentToMark.getAddress();
        Class currentClassDateTime = studentToMark.getAClass();
        Money currentMoneyOwed = studentToMark.getMoneyOwed();
        Money currentMoneyPaid = studentToMark.getMoneyPaid();
        Money currentRatesPerClass = studentToMark.getRatesPerClass();
        AdditionalNotes currentNotes = studentToMark.getAdditionalNotes();
        Set<Tag> currentTags = studentToMark.getTags();
        Class displayedClass = currentClassDateTime;
        Class updatedClassDateTime = currentClassDateTime.addDays(DAYS_IN_A_WEEK);
        Mark updatedMarkStatus = new Mark(Boolean.TRUE);
        Money updatedMoneyOwed;

        try {
            updatedMoneyOwed = currentRatesPerClass.addTo(currentMoneyOwed);
        } catch (CommandException e) {
            throw new CommandException(e.getMessage() + MESSAGE_CLEAR_STUDENT_DEBT);
        }

        return new Student(currentName, currentPhone, currentNokPhone, currentEmail, currentAddress,
                updatedClassDateTime, updatedMoneyOwed, currentMoneyPaid, currentRatesPerClass, currentNotes,
                currentTags, updatedMarkStatus, displayedClass);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return targetIndex.equals(e.targetIndex);
    }
}
