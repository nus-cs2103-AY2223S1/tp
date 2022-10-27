package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Model;
import seedu.watson.model.person.Person;

/**
 * Adds a person to the watson book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the watson book. "
                                               + "Parameters: "
                                               + PREFIX_NAME + "NAME "
                                               + PREFIX_PHONE + "PHONE "
                                               + PREFIX_EMAIL + "EMAIL "
                                               + PREFIX_ADDRESS + "ADDRESS "
                                               + PREFIX_STUDENTCLASS + "STUDENTCLASS " // New fields
                                               + "[" + PREFIX_TAG + "TAG]..."
                                               + "[" + PREFIX_REMARK + "REMARK]...\n"
                                               + "Example: " + COMMAND_WORD + " "
                                               + PREFIX_NAME + "John Doe "
                                               + PREFIX_PHONE + "98765432 "
                                               + PREFIX_EMAIL + "johnd@example.com "
                                               + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                                               + PREFIX_STUDENTCLASS + "1A " // New fields
                                               + PREFIX_TAG + "friends "
                                               + PREFIX_REMARK + "likes to eat ice cream ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the watson book";

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
    public String toString() {
        return this.toAdd.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
