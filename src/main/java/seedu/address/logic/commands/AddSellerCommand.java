package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTIES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.role.Seller;

/**
 * Adds a buyer to the address book.
 */
public class AddSellerCommand extends Command {
    public static final String COMMAND_WORD = "seller";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/Updates a seller role to the address book. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PROPERTIES + " PROPERTIES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PROPERTIES + " 2; 3 ";

    public static final String MESSAGE_SUCCESS = "Seller added: %1$s";

    private final Index index;
    private final Seller seller;

    /**
     * Creates an AddSellerCommand to add the specified {@code Seller}
     * to the {@code Person} at specified {@code Index} in the last-shown list
     */
    public AddSellerCommand(Seller seller, Index index) {
        requireAllNonNull(seller, index);
        this.seller = seller;
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
        model.setSellerRole(personToEdit, seller);
        return new CommandResult(String.format(MESSAGE_SUCCESS, seller));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSellerCommand // instanceof handles nulls
                && seller.equals(((AddSellerCommand) other).seller));
    }
}
