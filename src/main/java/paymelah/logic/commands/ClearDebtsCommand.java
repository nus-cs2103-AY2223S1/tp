package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.logic.commands.exceptions.CommandException;
import paymelah.model.Model;
import paymelah.model.debt.DebtList;
import paymelah.model.person.Address;
import paymelah.model.person.Email;
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
import paymelah.model.tag.Tag;

/**
 * Deletes the debts of an existing person in the address book.
 */
public class ClearDebtsCommand extends Command {

    public static final String COMMAND_WORD = "cleardebts";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the debts of the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLEAR_DEBTS_SUCCESS = "Cleared debts from Person: %1$s";

    private final Index index;

    /**
     * Constructor for the given ClearDebtsCommand.
     *
     * @param index of the person in the filtered person list to clear his debts
     */
    public ClearDebtsCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person debtorToClear = lastShownList.get(index.getZeroBased());
        Person clearedDebtor = createClearedDebtor(debtorToClear);

        model.setPerson(debtorToClear, clearedDebtor);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_CLEAR_DEBTS_SUCCESS, clearedDebtor.getName()));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code debtorToClear}
     * with no debts.
     *
     * @param debtorToClear Person within addressbook to be cleared of all his debts.
     * @return Person with an empty {@code DebtList}
     */
    private static Person createClearedDebtor(Person debtorToClear) {
        requireNonNull(debtorToClear);

        Name updatedName = debtorToClear.getName();
        Phone updatedPhone = debtorToClear.getPhone();
        Email updatedEmail = debtorToClear.getEmail();
        Address updatedAddress = debtorToClear.getAddress();
        Set<Tag> updatedTags = debtorToClear.getTags();
        DebtList updatedDebts = new DebtList();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedDebts);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearDebtsCommand)) {
            return false;
        }

        // state check
        ClearDebtsCommand c = (ClearDebtsCommand) other;
        return index.equals(c.index);
    }
}
