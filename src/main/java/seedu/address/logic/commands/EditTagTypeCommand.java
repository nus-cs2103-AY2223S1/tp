package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class EditTagTypeCommand extends Command{

    public static final String COMMAND_WORD = "editTagType";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits an existing tag type and its tag alias.\n"
            + "Parameters: OLD_TAG_TYPE-NEW-TAG-TYPE OLD_TAG_ALIAS-NEW_TAG_ALIAS\n"
            + "Example: " + COMMAND_WORD + " "
            + "Grade-GPA grdt-gpat ";

    public static final String MESSAGE_EDIT_TAG_TYPE_SUCCESS = "Edited tag type: %1$s";
    private final TagType toEdit;
    private final TagType editTo;

    public EditTagTypeCommand(TagType toEdit, TagType editTo) {
        this.toEdit = toEdit;
        this.editTo = editTo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditTagTypeCommand // instanceof handles nulls
                && toEdit.equals(((EditTagTypeCommand) other).toEdit)
                && editTo.equals(((EditTagTypeCommand) other).editTo));
    }
}
