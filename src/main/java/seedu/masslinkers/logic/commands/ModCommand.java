package seedu.masslinkers.logic.commands;

//@@author jonasgwt
/**
 * Represents a command that modifies the mod attribute of the
 * batchmate identified.
 */
public abstract class ModCommand extends Command {

    public static final String COMMAND_WORD = "mod";
    // TODO: Add the other mod commands
    public static final String MESSAGE_USAGE = "Enter a mod command such as mod add, "
            +
        "mod delete, mod mark, mod unmark and mod find. \n ";
    public static final String MESSAGE_MODS_EMPTY = "Mods cannot be empty! Specify mods to be modified.";
    public static final String MESSAGE_INDEX_EMPTY = "Index cannot be empty! "
            +
            "Specify the index of a batchmate to be modified.";
}
