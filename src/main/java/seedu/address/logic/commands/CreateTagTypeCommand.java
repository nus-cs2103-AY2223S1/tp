package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.TagType;

/**
 * Creates a new tag type.
 */
public class CreateTagTypeCommand extends Command {
    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new tag type so that you can add a tag "
            + "of a specific tag type to persons.\n"
            + "Parameters: "
            + "TAG_TYPE TAG_ALIAS\n"
            + "Example: " + COMMAND_WORD + " "
            + "Grage grdt";

    public static final String MESSAGE_SUCCESS = "New tag type created: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG_TYPE = "This tag type already exists";

    private final TagType toAdd;

    /**
     * Creates a CreateTagTypeCommand to add the specified {@code TagType}
     */
    public CreateTagTypeCommand(TagType toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateTagTypeCommand // instanceof handles nulls
                && toAdd.equals(((CreateTagTypeCommand) other).toAdd));
    }
}
