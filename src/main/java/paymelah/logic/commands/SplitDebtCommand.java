package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.util.CollectionUtil.requireAllNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.logic.commands.exceptions.CommandException;
import paymelah.model.Model;
import paymelah.model.debt.Debt;
import paymelah.model.person.Person;

/**
 * Splits a {@link Debt} and adds it to multiple {@link Person} in PayMeLah.
 */
public class SplitDebtCommand extends Command {
    public static final String COMMAND_WORD = "splitdebt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Splits a debt among several persons identified by their index numbers "
            + "used in the displayed person list. Index '0' specifies the user as a Person.\n"
            + "Parameters: PERSON_INDEX... (must be a non-negative integer."
            + " multiple indexes must be separated by a 'space'. '0' cannot be the sole index.) "
            + PREFIX_DESCRIPTION + "<description> "
            + PREFIX_MONEY + "<money> "
            + "Example: " + COMMAND_WORD + " 0 1 3 "
            + PREFIX_DESCRIPTION + "Large Pizza "
            + PREFIX_MONEY + "33.99";


    public static final String MESSAGE_SPLIT_DEBT_SUCCESS = "Added Debt: %1$s(Divided) to %2$s";

    private final Set<Index> debtorIndexes;
    private final Debt debt;

    /**
     * Creates an SplitDebtCommand to add a specified {@code Debt} to multiple specified {@code Person}s.
     *
     * @param debtorIndexes of the person in the filtered person list to add a debt to
     * @param debt the debt to add to the person
     */
    public SplitDebtCommand(Set<Index> debtorIndexes, Debt debt) {
        requireAllNonNull(debtorIndexes, debt);

        this.debtorIndexes = debtorIndexes;
        this.debt = debt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        ArrayList<Person> debtors = new ArrayList<>();

        for (Index debtorToEdit : debtorIndexes) {
            int zeroBasedIndex = debtorToEdit.getZeroBased();
            if (zeroBasedIndex >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            debtors.add(lastShownList.get(zeroBasedIndex));
        }

        StringBuilder nameList = new StringBuilder();

        for (int i = 0; i < debtors.size(); i++) {
            Person debtorToEdit = debtors.get(i);
            Person editedPerson = new Person(
                    debtorToEdit.getName(), debtorToEdit.getPhone(),
                    debtorToEdit.getEmail(), debtorToEdit.getAddress(),
                    debtorToEdit.getTags(), debtorToEdit.getDebts().addDebt(debt.copyDebt()));
            model.setPerson(debtorToEdit, editedPerson);

            if (i == 0) {
                nameList.append(debtorToEdit.getName());
            } else if (i != debtors.size() - 1) {
                nameList.append(", ").append(debtorToEdit.getName());
            } else {
                nameList.append(" and ").append(debtorToEdit.getName());
            }
        }

        return new CommandResult(String.format(MESSAGE_SPLIT_DEBT_SUCCESS, debt, nameList));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SplitDebtCommand)) {
            return false;
        }

        // state check
        SplitDebtCommand c = (SplitDebtCommand) other;
        return debtorIndexes.equals(c.debtorIndexes)
                && debt.equals(c.debt);
    }
}
