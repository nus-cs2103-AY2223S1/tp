package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.CollectionUtil.requireAllNonNull;

import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.logic.parser.exceptions.DuplicatePrefixException;
import seedu.clinkedin.logic.parser.exceptions.PrefixNotFoundException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.TagType;
import seedu.clinkedin.model.tag.exceptions.DuplicateTagTypeException;
import seedu.clinkedin.model.tag.exceptions.TagTypeNotFoundException;
import seedu.clinkedin.model.tag.exceptions.TagTypePrefixPairNotFoundException;

/**
 * Edits a tag type.
 */
public class EditTagTypeCommand extends Command {

    public static final String COMMAND_WORD = "edittagtype";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits an existing tag type and its tag alias.\n"
            + "Parameters: OLD_TAG_TYPE-NEW-TAG-TYPE OLD_TAG_ALIAS-NEW_TAG_ALIAS\n"
            + "Example: " + COMMAND_WORD + " "
            + "Grade-GPA grdt-gpat ";

    public static final String MESSAGE_EDIT_TAG_TYPE_SUCCESS = "Edited tag type: %1$s";
    private final TagType toEditTagType;
    private final TagType editToTagType;
    private final Prefix toEditPrefix;
    private final Prefix editToPrefix;

    /**
     * Creates an EditTagTypeCommand to edit the specified {@code TagType}
     */
    public EditTagTypeCommand(Prefix toEditPrefix, TagType toEditTagType, Prefix editToPrefix, TagType editToTagType) {
        requireAllNonNull(toEditPrefix, toEditTagType, editToPrefix, editToTagType);
        this.toEditPrefix = toEditPrefix;
        this.toEditTagType = toEditTagType;
        this.editToPrefix = editToPrefix;
        this.editToTagType = editToTagType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            UniqueTagTypeMap.setExistingTagType(toEditPrefix, toEditTagType, editToPrefix, editToTagType);
            model.editTagTypeForAllPerson(toEditTagType, editToTagType);
        } catch (PrefixNotFoundException | TagTypeNotFoundException
                | DuplicateTagTypeException | DuplicatePrefixException | TagTypePrefixPairNotFoundException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_EDIT_TAG_TYPE_SUCCESS, editToTagType));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditTagTypeCommand // instanceof handles nulls
                && toEditPrefix.equals(((EditTagTypeCommand) other).toEditPrefix)
                && toEditTagType.equals(((EditTagTypeCommand) other).toEditTagType)
                && editToPrefix.equals(((EditTagTypeCommand) other).editToPrefix)
                && editToTagType.equals(((EditTagTypeCommand) other).editToTagType));
    }
}
