package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_URL_STR;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Link;

/**
 * Adds a new link to TruthTable.
 */
public class AddLinkCommand extends Command {
    public static final String COMMAND_WORD = "add_link";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new link \n"
            + "Parameters: "
            + "-" + FLAG_NAME_STR + " NAME "
            + "-" + FLAG_URL_STR + " PHONE \n"
            + "Example: " + COMMAND_WORD + " "
            + "-" + FLAG_NAME_STR + " \"Google\" "
            + "-" + FLAG_URL_STR + " https://google.com ";

    public static final String MESSAGE_SUCCESS = "New link added: %1$s";

    public static final String MESSAGE_DUPLICATE_LINK = "This link already exists in team";

    private final Link toAdd;

    /**
     * Creates an AddLinkCommand to add the specified {@code Link}.
     */
    public AddLinkCommand(Link link) {
        requireNonNull(link);
        this.toAdd = link;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasLink(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LINK);
        }
        model.addLink(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLinkCommand // instanceof handles nulls
                && toAdd.equals(((AddLinkCommand) other).toAdd));
    }
}
