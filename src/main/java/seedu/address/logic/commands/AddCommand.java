package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a Supplier to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a supplier to the address book. "
            + "\nParameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_PRICE + "PRICE "
            + PREFIX_ITEM + "ITEM "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "Supplier]"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Yoyo Pte Ltd "
            + PREFIX_PHONE + "67090808 "
            + PREFIX_PRICE + "$1.20 "
            + PREFIX_ITEM + "Ginger "
            + PREFIX_ADDRESS + "Blk 42 Hougang Street 29,#01-40 "
            + PREFIX_TAG + "Supplier ";

    public static final String MESSAGE_SUCCESS = "New supplier added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This supplier already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
