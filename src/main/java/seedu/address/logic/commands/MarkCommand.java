package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AdditionalNotes;
import seedu.address.model.person.Address;
import seedu.address.model.person.Class;
import seedu.address.model.person.Email;
import seedu.address.model.person.Mark;
import seedu.address.model.person.Money;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.storage.ClassStorage;

/**
 * Marks an existing person as present for his/her class.
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

    public MarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredScheduleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_SCHEDULE_INDEX);
        }

        Person personToMark = lastShownList.get(targetIndex.getZeroBased());
        Person markedPerson = createMarkedPerson(personToMark);

        if (markedPerson.equals(personToMark)) {
            throw new CommandException(MESSAGE_MARKED_PREVIOUSLY);
        }

        ClassStorage.saveClass(markedPerson, this.targetIndex.getZeroBased());
        ClassStorage.removeExistingClass(personToMark);

        model.setPerson(personToMark, markedPerson);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates and returns a marked {@code Person} with the details of {@code personToMark}.
     */
    private static Person createMarkedPerson(Person personToMark) throws CommandException {
        assert personToMark != null;

        if (personToMark.getMarkStatus().isMarked() == true) {
            return personToMark;
        }

        Name currentName = personToMark.getName();
        Phone currentPhone = personToMark.getPhone();
        Phone currentNokPhone = personToMark.getNokPhone();
        Email currentEmail = personToMark.getEmail();
        Address currentAddress = personToMark.getAddress();
        Class currentClassDateTime = personToMark.getAClass();
        Money currentMoneyOwed = personToMark.getMoneyOwed();
        Money currentMoneyPaid = personToMark.getMoneyPaid();
        Money currentRatesPerClass = personToMark.getRatesPerClass();
        AdditionalNotes currentNotes = personToMark.getAdditionalNotes();
        Set<Tag> currentTags = personToMark.getTags();
        Class displayedClass = currentClassDateTime;
        Class updatedClassDateTime = currentClassDateTime.addDays(DAYS_IN_A_WEEK);
        Mark updatedMarkStatus = new Mark(Boolean.TRUE);
        Money updatedMoneyOwed;

        try {
            updatedMoneyOwed = currentRatesPerClass.addTo(currentMoneyOwed);
        } catch (CommandException e) {
            throw new CommandException(e.getMessage() + MESSAGE_CLEAR_STUDENT_DEBT);
        }

        return new Person(currentName, currentPhone, currentNokPhone, currentEmail, currentAddress,
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
