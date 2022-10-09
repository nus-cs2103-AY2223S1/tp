package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_DEBT;
import static paymelah.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.logic.commands.exceptions.CommandException;
import paymelah.model.Model;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtList;
import paymelah.model.person.Address;
import paymelah.model.person.Email;
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
import paymelah.model.tag.Tag;

/**
 * Deletes a specific debt of a person identified using their respective displayed
 * indexes from the address book.
 */
public class DeleteDebtCommand extends Command {

    public static final String COMMAND_WORD = "deletedebt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a specific debt of the person identified by the index number "
            + "used in the displayed person list. The debt is specified by the index number "
            + "of the debt displayed in the person's debt field. Multiple debts may be specified.\n"
            + "Parameters: PERSON_INDEX (must be a positive integer) "
            + PREFIX_DEBT + "DEBT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_DEBT + "2 " + PREFIX_DEBT + "3";

    public static final String MESSAGE_DELETE_DEBT_SUCCESS = "Deleted Debt(s) from: %1$s:\n";

    private final Index debtorIndex;
    private final Set<Index> debtIndexes;

    /**
     * Constructs the given DeleteDebtCommand.
     *
     * @param debtorIndex is the index of the {@code Person} in the filtered person list to have a debt deleted
     * @param indexSet is the set of indexes of the {@code Debt}s in the list of debts displayed in the given
     *      person's debt field.
     */
    public DeleteDebtCommand(Index debtorIndex, Set<Index> indexSet) {
        requireNonNull(debtorIndex);
        requireNonNull(indexSet);
        this.debtorIndex = debtorIndex;
        this.debtIndexes = indexSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (debtorIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person debtorToUpdate = lastShownList.get(debtorIndex.getZeroBased());
        List<Debt> initialDebts = debtorToUpdate.getDebts().asList();
        Set<Debt> debtsToDelete = new HashSet<>();

        for (Index debtIndex : debtIndexes) {
            requireNonNull(debtIndex);
            if (debtIndex.getOneBased() > initialDebts.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_DEBT_DISPLAYED_INDEX);
            }
            debtsToDelete.add(initialDebts.get(debtIndex.getZeroBased()));
        }

        Person updatedDebtor = createReducedDebtor(debtorToUpdate, debtsToDelete);

        model.setPerson(debtorToUpdate, updatedDebtor);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        String result = String.format(MESSAGE_DELETE_DEBT_SUCCESS, updatedDebtor.getName());
        StringBuilder builder = new StringBuilder(result);

        int i = 1;
        for (Debt debt : debtsToDelete) {
            builder.append(i + ". ")
                    .append(debt.toString())
                    .append("\n");
            i++;
        }

        return new CommandResult(builder.toString());
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code debtorToClear}
     * with no debts.
     *
     * @param debtorToReduce {@code Person} within addressbook to have the specified {@code Debt}s deleted.
     * @param debtsToDelete Set of {@code Debt}s to delete from the {@code debtorToReduce}.
     * @return Person with a reduced {@code DebtList}.
     */
    private static Person createReducedDebtor(Person debtorToReduce, Set<Debt> debtsToDelete) {
        requireNonNull(debtorToReduce);
        requireNonNull(debtsToDelete);

        Name name = debtorToReduce.getName();
        Phone phone = debtorToReduce.getPhone();
        Email email = debtorToReduce.getEmail();
        Address address = debtorToReduce.getAddress();
        Set<Tag> tags = debtorToReduce.getTags();
        DebtList reducedDebts = debtorToReduce.getDebts();

        for (Debt paidDebt : debtsToDelete) {
            assert reducedDebts.contains(paidDebt) : String.format("Debt (%1$s) cannot be found.", paidDebt);
            reducedDebts = reducedDebts.removeDebt(paidDebt);
        }


        return new Person(name, phone, email, address, tags, reducedDebts);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteDebtCommand)) {
            return false;
        }

        // state check
        DeleteDebtCommand c = (DeleteDebtCommand) other;
        return debtorIndex.equals(c.debtorIndex)
                && debtIndexes.equals(c.debtIndexes);
    }
}
