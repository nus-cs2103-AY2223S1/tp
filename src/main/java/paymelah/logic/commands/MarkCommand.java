package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_DEBT;

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
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
import paymelah.model.person.Telegram;
import paymelah.model.tag.Tag;

/**
 * Marks specific debts of a person identified using their respective displayed
 * indexes from the address book as paid.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a specific debt of the person identified by the index number "
            + "used in the displayed person list as paid. The debt is specified by the index number "
            + "of the debt displayed in the person's debt field. Multiple debts may be specified.\n"
            + "Parameters: PERSON_INDEX (must be a positive integer) "
            + PREFIX_DEBT + "DEBT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_DEBT + "2 3";

    public static final String MESSAGE_MARK_DEBT_SUCCESS = "Marked Debt(s) as Paid from: %1$s:\n";

    private final Index debtorIndex;
    private final Set<Index> debtIndexes;

    /**
     * Constructs the given MarkCommand.
     *
     * @param debtorIndex is the index of the {@code Person} in the filtered person list to have debts marked as paid.
     * @param indexSet is the set of indexes of the {@code Debt}s in the list of debts displayed in the given
     *      person's debt field.
     */
    public MarkCommand(Index debtorIndex, Set<Index> indexSet) {
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

        model.saveAddressBook();
        // Command message is saved to undo history after string is built below.
        Person debtorToUpdate = lastShownList.get(debtorIndex.getZeroBased());
        List<Debt> initialDebts = debtorToUpdate.getDebts().asList();
        Set<Debt> debtsToMark = new HashSet<>();

        for (Index debtIndex : debtIndexes) {
            requireNonNull(debtIndex);
            if (debtIndex.getOneBased() > initialDebts.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_DEBT_DISPLAYED_INDEX);
            }
            debtsToMark.add(initialDebts.get(debtIndex.getZeroBased()));
        }

        Person updatedDebtor = createUpdatedDebtor(debtorToUpdate, debtsToMark);

        model.setPerson(debtorToUpdate, updatedDebtor);

        String result = String.format(MESSAGE_MARK_DEBT_SUCCESS, updatedDebtor.getName());
        StringBuilder builder = new StringBuilder(result);

        int i = 1;
        for (Debt debt : debtsToMark) {
            builder.append(i + ". ")
                    .append(debt.setPaid(true))
                    .append("\n");
            i++;
        }

        model.saveCommandMessage(builder.toString());
        return new CommandResult(builder.toString());
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code debtorToUpdate}
     * and debts-to-mark marked as paid.
     *
     * @param debtorToUpdate {@code Person} within PayMeLah to have the specified {@code Debt}s marked as paid.
     * @param debtsToMark Set of {@code Debt}s to mark as paid from the {@code debtorToUpdate}.
     * @return Person with an updated {@code DebtList}.
     */
    private static Person createUpdatedDebtor(Person debtorToUpdate, Set<Debt> debtsToMark) {
        requireNonNull(debtorToUpdate);
        requireNonNull(debtsToMark);

        Name name = debtorToUpdate.getName();
        Phone phone = debtorToUpdate.getPhone();
        Telegram telegram = debtorToUpdate.getTelegram();
        Address address = debtorToUpdate.getAddress();
        Set<Tag> tags = debtorToUpdate.getTags();
        DebtList updatedDebts = debtorToUpdate.getDebts();

        for (Debt paidDebt : debtsToMark) {
            assert updatedDebts.contains(paidDebt) : String.format("Debt (%1$s) cannot be found.", paidDebt);
            updatedDebts = updatedDebts.markDebt(paidDebt);
        }


        return new Person(name, phone, telegram, address, tags, updatedDebts);
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
        MarkCommand c = (MarkCommand) other;
        return debtorIndex.equals(c.debtorIndex)
                && debtIndexes.equals(c.debtIndexes);
    }
}
