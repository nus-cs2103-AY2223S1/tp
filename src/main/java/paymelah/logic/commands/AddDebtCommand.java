package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.util.CollectionUtil.requireAllNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;

import java.util.List;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.logic.commands.exceptions.CommandException;
import paymelah.model.Model;
import paymelah.model.debt.Debt;
import paymelah.model.person.Person;

/**
 * Adds a {@link Debt} to a {@link Person} in PayMeLah.
 */
public class AddDebtCommand extends Command {
    public static final String COMMAND_WORD = "adddebt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a debt to a person. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_DESCRIPTION + "<description> "
            + PREFIX_MONEY + "<money> "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Carl's Jr "
            + PREFIX_MONEY + "97.40";

    public static final String MESSAGE_ADD_DEBT_SUCCESS = "Added debt %1$s to %2$s";

    private final Index index;
    private final Debt debt;

    /**
     * Creates an AddDebtCommand to add a specified {@code Debt} to a specified {@code Person}.
     *
     * @param index of the person in the filtered person list to add a debt to
     * @param debt the debt to add to the person
     */
    public AddDebtCommand(Index index, Debt debt) {
        requireAllNonNull(index, debt);

        this.index = index;
        this.debt = debt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), personToEdit.getDebts().addDebt(debt));

        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_ADD_DEBT_SUCCESS, debt, editedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDebtCommand // instanceof handles nulls
                && index.equals(((AddDebtCommand) other).index)
                && debt.equals(((AddDebtCommand) other).debt));
    }
}
