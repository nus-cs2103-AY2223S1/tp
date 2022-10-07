package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEGREETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTYPETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILLTAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Adds a tag of a specific tag type to a person.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "addTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag of a specific tag type to a person.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "[" + PREFIX_SKILLTAG + " TAG] "
            + "[" + PREFIX_DEGREETAG + " TAG] "
            + "[" + PREFIX_JOBTYPETAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_SKILLTAG + "Java "
            + PREFIX_DEGREETAG + "Undergraduate "
            + PREFIX_JOBTYPETAG + "Internship";

    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists";

    private final Tag toAdd;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public AddTagCommand(Tag toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagCommand // instanceof handles nulls
                && toAdd.equals(((AddTagCommand) other).toAdd));
    }
}
