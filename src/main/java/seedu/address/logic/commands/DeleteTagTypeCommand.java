package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.UniqueTagTypeMap;
import seedu.address.model.person.exceptions.TagTypeNotFoundException;
import seedu.address.model.tag.TagType;

/**
 * Deletes a specified tag type.
 */
public class DeleteTagTypeCommand extends Command {
    public static final String COMMAND_WORD = "deletetagtype";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tag type from the list of tag types that can be added to a person.\n"
            + "Parameters: TAG_TYPE \n"
            + "Example: " + COMMAND_WORD + " "
            + "Grade ";

    public static final String MESSAGE_DELETE_TAG_TYPE_SUCCESS = "Deleted tag type: %1$s";
    private final TagType toDelete;

    /**
     * Creates a DeleteTagTypeCommand to delete the specified {@code TagType}
     */
    public DeleteTagTypeCommand(TagType toDelete) {
        requireNonNull(toDelete);
        this.toDelete = toDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            UniqueTagTypeMap.removeExistingTagType(toDelete);
        } catch (TagTypeNotFoundException t) {
            throw new CommandException(t.getMessage());
        }

        model.deleteTagTypeForAllPerson(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_TYPE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagTypeCommand // instanceof handles nulls
                && toDelete.equals(((DeleteTagTypeCommand) other).toDelete));
    }
}
