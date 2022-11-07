package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.util.CollectionUtil.requireAllNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_DATE;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;
import static paymelah.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.logic.commands.exceptions.CommandException;
import paymelah.model.Model;
import paymelah.model.debt.Debt;
import paymelah.model.debt.exceptions.DuplicateDebtException;
import paymelah.model.person.Person;

/**
 * Adds a {@link Debt} to a {@link Person} in PayMeLah.
 */
public class AddDebtCommand extends Command {
    public static final String COMMAND_WORD = "adddebt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a debt to a person.\n"
            + "Parameters: <person index…> (must be a positive integer) "
            + PREFIX_DESCRIPTION + "<description> "
            + PREFIX_MONEY + "<money> "
            + "[" + PREFIX_DATE + "<date>] "
            + "[" + PREFIX_TIME + "<time>]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Carl's Jr "
            + PREFIX_MONEY + "97.40 "
            + PREFIX_DATE + "2022-10-12 "
            + PREFIX_TIME + "09:00";

    public static final String MESSAGE_ADD_DEBT_SUCCESS = "Added debt %1$s to %2$s";
    public static final String MESSAGE_DUPLICATE_DEBT = "Debt %1$s already exists under %2$s";

    private final Set<Index> indices;
    private final Debt debt;

    /**
     * Creates an AddDebtCommand to add a specified {@code Debt} to a specified {@code Person}.
     *
     * @param indices A set of indices of the persons in the filtered person list to add a debt to
     * @param debt the debt to add to each person
     */
    public AddDebtCommand(Set<Index> indices, Debt debt) {
        requireAllNonNull(indices, debt);

        this.indices = indices;
        this.debt = debt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        ArrayList<Person> debtors = new ArrayList<>();

        for (Index index : indices) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            debtors.add(lastShownList.get(index.getZeroBased()));
        }

        model.saveAddressBook();
        // Command message is saved to undo history after string is built
        StringBuilder nameList = new StringBuilder();
        int size = debtors.size();

        for (int i = 0; i < size; i++) {
            Person personToEdit = debtors.get(i);
            try {
                Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(),
                        personToEdit.getTelegram(), personToEdit.getAddress(), personToEdit.getTags(),
                        personToEdit.getDebts().addDebt(debt.copyDebt()));
                model.setPerson(personToEdit, editedPerson);
            } catch (DuplicateDebtException e) {
                model.undoAddressBook();
                throw new CommandException(String.format(MESSAGE_DUPLICATE_DEBT, debt, personToEdit.getName()));
            }

            if (i == 0) {
                nameList.append(personToEdit.getName());
            } else if (i != size - 1) {
                nameList.append(", ").append(personToEdit.getName());
            } else {
                nameList.append(" and ").append(personToEdit.getName());
            }
        }

        model.saveCommandMessage(String.format(MESSAGE_ADD_DEBT_SUCCESS, debt, nameList));
        return new CommandResult(String.format(MESSAGE_ADD_DEBT_SUCCESS, debt, nameList));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDebtCommand // instanceof handles nulls
                && indices.equals(((AddDebtCommand) other).indices)
                && debt.equals(((AddDebtCommand) other).debt));
    }
}
