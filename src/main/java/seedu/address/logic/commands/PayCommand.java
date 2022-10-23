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
import seedu.address.model.person.Money;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
        List<Person> lastShownList = model.getFilteredScheduleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_SCHEDULE_INDEX);
        }

        Person personPaying = lastShownList.get(targetIndex.getZeroBased());
        Person paidPerson = createPaidPerson(personPaying, amountPaid);

        model.setPerson(personPaying, paidPerson);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates and returns a paid {@code Person} with the details of {@code personPaying}.
     */
    static Person createPaidPerson(Person personPaying, Money amountPaid) throws CommandException {
        assert personPaying != null;

        if (!personPaying.isOwingMoney()) {
            throw new CommandException(MESSAGE_HAS_NO_DEBT);
        }

        Name currentName = personPaying.getName();
        Phone currentPhone = personPaying.getPhone();
        Phone currentNokPhone = personPaying.getNokPhone();
        Email currentEmail = personPaying.getEmail();
        Address currentAddress = personPaying.getAddress();
        Class currentClassDateTime = personPaying.getAClass();
        Money currentMoneyOwed = personPaying.getMoneyOwed();
        Money currentMoneyPaid = personPaying.getMoneyPaid();
        Money currentRatesPerClass = personPaying.getRatesPerClass();
        AdditionalNotes currentNotes = personPaying.getAdditionalNotes();
        Set<Tag> currentTags = personPaying.getTags();

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

        return new Person(currentName, currentPhone, currentNokPhone, currentEmail, currentAddress,
                currentClassDateTime, updatedMoneyOwed, updatedMoneyPaid, currentRatesPerClass, currentNotes,
                currentTags);
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
