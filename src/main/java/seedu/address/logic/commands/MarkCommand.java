package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.person.Class;
import seedu.address.model.tag.Tag;
import seedu.address.storage.ClassStorage;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the student present for the upcoming lesson "
            + "by the index number shown in the schedule list.\n"
            + "Parameters: INDEX (must be positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public MarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredScheduleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMark = lastShownList.get(targetIndex.getZeroBased());
        Person markedPerson = createMarkedPerson(personToMark);

        if (markedPerson.equals(personToMark)) {
            throw new CommandException("Person has been marked previously");
        }

        ClassStorage.saveClass(markedPerson, this.targetIndex.getZeroBased());


        model.setPerson(personToMark, markedPerson);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_USAGE);
    }

    private static Person createMarkedPerson(Person personToMark) {
        assert personToMark != null;

        if (personToMark.getMarkStatus().isPresent == true) {
            return personToMark;
        }

        // reference to EditCommand.java
        Name currentName = personToMark.getName();
        Phone currentPhone = personToMark.getPhone();
        NokPhone currentNokPhone = personToMark.getNokPhone();
        Email currentEmail = personToMark.getEmail();
        Address currentAddress = personToMark.getAddress();
        Class currentClassDateTime = personToMark.getAClass();
        Money currentMoneyOwed = personToMark.getMoneyOwed();
        Money currentMoneyPaid = personToMark.getMoneyPaid();
        Money currentRatesPerClass = personToMark.getRatesPerClass();
        AdditionalNotes currentNotes = personToMark.getAdditionalNotes();
        Set<Tag> currentTags = personToMark.getTags();
        Mark currentMarkStatus = personToMark.getMarkStatus();

        Money updatedMoneyOwed = currentMoneyOwed.addTo(currentRatesPerClass);
        Class displayedClass = currentClassDateTime;
        Class updatedClassDateTime = currentClassDateTime.addDays(7);

        Mark updatedMarkStatus = new Mark(Boolean.TRUE);

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