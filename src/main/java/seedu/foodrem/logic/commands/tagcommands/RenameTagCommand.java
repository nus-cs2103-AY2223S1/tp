package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.foodrem.enums.CommandWord;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.tag.Tag;

/**
 * Renames an existing tag in FoodRem.
 */
public class RenameTagCommand extends Command {

    public static final String COMMAND_WORD = CommandWord.RENAME_TAG_COMMAND.getCommandWord();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Renames an existing tag in FoodRem. "
            + "Parameters: "
            + PREFIX_NAME + "ORIGINAL_TAG_NAME "
            + PREFIX_NAME + "NEW_TAG_NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Vegetables "
            + PREFIX_NAME + "fruits";

    public static final String MESSAGE_RENAME_TAG_SUCCESS = "Renamed tag: %1$s";
    public static final String MESSAGE_TAG_NOT_FOUND = "This tag does not exist in the FoodRem.";
    public static final String MESSAGE_DUPLICATE_TAG_NAME = "This tag name already exists in the FoodRem.";

    private final Tag originalTag;
    private final Tag renamedTag;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public RenameTagCommand(Tag originalTag, Tag renamedTag) {
        requireNonNull(originalTag);
        requireNonNull(renamedTag);
        this.originalTag = originalTag;
        this.renamedTag = renamedTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(originalTag)) {
            throw new CommandException(MESSAGE_TAG_NOT_FOUND);
        }

        if (!originalTag.equals(renamedTag) && model.hasTag(renamedTag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG_NAME);
        }

        model.setTag(originalTag, renamedTag);
        return new CommandResult(String.format(MESSAGE_RENAME_TAG_SUCCESS, renamedTag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof RenameTagCommand
                && originalTag.equals(((RenameTagCommand) other).originalTag)
                && renamedTag.equals(((RenameTagCommand) other).renamedTag));
    }
}
