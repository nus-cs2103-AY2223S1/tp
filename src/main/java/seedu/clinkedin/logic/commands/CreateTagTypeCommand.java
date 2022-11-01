package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.logic.parser.exceptions.DuplicatePrefixException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.exceptions.DuplicateTagTypeException;
import seedu.clinkedin.model.tag.TagType;

/**
 * Creates a new tag type.
 */
public class CreateTagTypeCommand extends Command {
    public static final String COMMAND_WORD = "createtagtype";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new tag type so that you can add a tag "
            + "of a specific tag type to persons.\n"
            + "Parameters: "
            + "TAG_TYPE TAG_ALIAS\n"
            + "Example: " + COMMAND_WORD + " "
            + "Grade grdt";

    public static final String MESSAGE_SUCCESS = "New tag type created: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG_TYPE = "This tag type already exists";

    private final TagType toAdd;
    private final Prefix prefix;

    /**
     * Creates a CreateTagTypeCommand to add the specified {@code TagType}
     */
    public CreateTagTypeCommand(TagType toAdd, Prefix prefix) {
        requireNonNull(toAdd);
        requireNonNull(prefix);
        this.toAdd = toAdd;
        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            UniqueTagTypeMap.createTagType(prefix, toAdd);
        } catch (DuplicatePrefixException | DuplicateTagTypeException d) {
            throw new CommandException(d.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateTagTypeCommand // instanceof handles nulls
                && toAdd.equals(((CreateTagTypeCommand) other).toAdd));
    }
}
