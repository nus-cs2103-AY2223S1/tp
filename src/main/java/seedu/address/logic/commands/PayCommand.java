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

/**
 * Indicates that a particular student has paid an amount of money.
 */
public class PayCommand extends Command {

    public static final String COMMAND_WORD = "pay";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Indicates that the student has paid an amount of money "
            + "by the index number shown in the schedule list.\n"
            + "Parameters: INDEX AMOUNT_PAID (must be positive and non-negative integers respectively)\n"
            + "Example: " + COMMAND_WORD + " 1 300";

    public static final String MESSAGE_HAS_NO_DEBT = "Student has no debt to pay";

    public static final String MESSAGE_SUCCESS = "Student has successfully paid";

    private final Index targetIndex;

    private final Money amountPaid;

    /**
     * Constructs a new PayCommand for enabling a student to pay money.
     *
     * @param targetIndex the index of the student in the schedule list.
     */
    public PayCommand(Index targetIndex, Money amountPaid) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.amountPaid = amountPaid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredScheduleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_SCHEDULE_INDEX);
        }

        Student studentPaying = lastShownList.get(targetIndex.getZeroBased());
        Student paidStudent = createPaidStudent(studentPaying, amountPaid);

        model.setStudent(studentPaying, paidStudent);

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates and returns a paid {@code Student} with the details of {@code studentPaying}.
     */
    static Student createPaidStudent(Student studentPaying, Money amountPaid) throws CommandException {
        assert studentPaying != null;

        if (!studentPaying.isOwingMoney()) {
            throw new CommandException(MESSAGE_HAS_NO_DEBT);
        }

        Name currentName = studentPaying.getName();
        Phone currentPhone = studentPaying.getPhone();
        Phone currentNokPhone = studentPaying.getNokPhone();
        Email currentEmail = studentPaying.getEmail();
        Address currentAddress = studentPaying.getAddress();
        Class currentClassDateTime = studentPaying.getAClass();
        Money currentMoneyOwed = studentPaying.getMoneyOwed();
        Money currentMoneyPaid = studentPaying.getMoneyPaid();
        Money currentRatesPerClass = studentPaying.getRatesPerClass();
        AdditionalNotes currentNotes = studentPaying.getAdditionalNotes();
        Set<Tag> currentTags = studentPaying.getTags();
        Mark currentMarkStatus = studentPaying.getMarkStatus();
        Class displayedClassDate = studentPaying.getDisplayedClass();

        Money updatedMoneyPaid;
        Money updatedMoneyOwed;

        try {
            updatedMoneyPaid = amountPaid.addTo(currentMoneyPaid);
        } catch (CommandException e) {
            throw new CommandException(e.getMessage() + ", the student cannot pay any more amount");
        }

        try {
            updatedMoneyOwed = currentMoneyOwed.subtract(amountPaid);
        } catch (CommandException e) {
            throw new CommandException(e.getMessage() + ", the student cannot pay more than the debt");
        }

        return new Student(currentName, currentPhone, currentNokPhone, currentEmail, currentAddress,
                currentClassDateTime, updatedMoneyOwed, updatedMoneyPaid, currentRatesPerClass, currentNotes,
                currentTags, currentMarkStatus, displayedClassDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PayCommand)) {
            return false;
        }

        // state check
        PayCommand e = (PayCommand) other;
        return targetIndex.equals(e.targetIndex) && amountPaid.equals(e.amountPaid);
    }
}
