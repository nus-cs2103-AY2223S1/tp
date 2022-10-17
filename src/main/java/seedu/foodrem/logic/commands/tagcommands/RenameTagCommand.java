package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandType.RENAME_TAG_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.tag.Tag;

/**
 * Renames an existing tag in FoodRem.
 */
public class RenameTagCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Renamed tag: %1$s";
    private static final String ERROR_NOT_FOUND = "This tag does not exist in the FoodRem.";
    private static final String ERROR_DUPLICATE = "This tag name already exists in the FoodRem.";

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
            throw new CommandException(ERROR_NOT_FOUND);
        }

        if (!originalTag.equals(renamedTag) && model.hasTag(renamedTag)) {
            throw new CommandException(ERROR_DUPLICATE);
        }

        model.setTag(originalTag, renamedTag);
        return new CommandResult(String.format(MESSAGE_SUCCESS, renamedTag));
    }

    public static String getUsage() {
        return RENAME_TAG_COMMAND.getUsage();
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
