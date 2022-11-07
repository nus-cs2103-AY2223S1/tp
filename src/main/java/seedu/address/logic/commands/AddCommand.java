package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.MainPanelName;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
        + "Either GitHub username or name must be provided."
        + "Parameters: "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_GITHUB + "GITHUB] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_ROLE + "ROLE] "
        + "[" + PREFIX_TIMEZONE + "TIMEZONE] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_SLACK + "SLACK] "
        + "[" + PREFIX_TELEGRAM + "TELEGRAM] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_GITHUB + "johndoe "
        + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
        + PREFIX_ROLE + "engineer "
        + PREFIX_TIMEZONE + "+8 "
        + PREFIX_TAG + "friends "
        + PREFIX_TAG + "owesMoney "
        + PREFIX_PHONE + "81828384 "
        + PREFIX_EMAIL + "test@example.com "
        + PREFIX_SLACK + "helloalex "
        + PREFIX_TELEGRAM + "@helloalex ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

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

    public static boolean canExecuteAt(MainPanelName name) {
        return name.equals(MainPanelName.List);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
