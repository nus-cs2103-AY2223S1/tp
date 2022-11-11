package seedu.address.logic.commands.fields;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.item.DisplayItem;

// @@author jasonchristopher21
/** Command to rename a group/task/person */
public class DeleteFieldCommand extends FieldCommand {

    public static final String SUBCOMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = getFullCommand(SUBCOMMAND_WORD)
        + " :Deletes an custom attribute to a given item.\n"
        + getFullCommand(SUBCOMMAND_WORD) + " [t|u|g]/id [type]"
        + "e.g. field delete t/1 bug"
        + " OR task select 1 field delete bug\n"
        + "Delete bug attribute of task 1";

    public static final String NO_INPUT = "No input item is chosen!";
    public static final String MESSAGE_DUPLICATE = "An item with the same name already exist!";
    public static final String OUT_OF_BOUNDS = "ID selected is out of bounds!";
    public static final String SUCCESS_MSG = "The item have been renamed!";
    public static final String INVALID_FORMAT = "The item cannot be renamed to such!";

    private String type;
    private String ftype;
    private Index index;

    /**
     * Constructor to create a delete field command
     */
    public DeleteFieldCommand(Index index, String ftype, String type) {
        this.index = index;
        this.type = type;
        this.ftype = ftype;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        DisplayItem item;
        try {
            item = selectFromRightModel(model, ftype, index);
        } catch (ParseException e) {
            throw new CommandException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        if (item == null && sItem == null) {
            throw new CommandException(NO_INPUT);
        }
        if (item == null) {
            item = sItem;
        }
        item.deleteAttribute(type);
        model.refresh();
        return new CommandResult(SUCCESS_MSG);
    }
}
