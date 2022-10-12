package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.role.Buyer;

/**
 * Adds a buyer to the address book.
 */
public class AddBuyerCommand extends Command {
    public static final String COMMAND_WORD = "buyer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/Updates a buyer role to the address book. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PRICE_RANGE + " PRICE RANGE] "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PRICE_RANGE + " 200000 - 250000 "
            + PREFIX_CHARACTERISTICS + " 5-ROOM; SOUTH-FACING; BISHAN ";

    public static final String MESSAGE_SUCCESS = "Buyer added: %1$s";

    private final Index index;
    private final Buyer buyer;

    /**
     * Creates an AddBuyer Command to add the specified {@code Buyer}
     * to the {@code Person} at specified {@code Index} in the last-shown list
     */
    public AddBuyerCommand(Buyer buyer, Index index) {
        requireAllNonNull(buyer, index);
        this.buyer = buyer;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        model.setBuyerRole(personToEdit, buyer);
        return new CommandResult(String.format(MESSAGE_SUCCESS, buyer));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBuyerCommand // instanceof handles nulls
                && buyer.equals(((AddBuyerCommand) other).buyer));
    }
}
