package seedu.address.logic.commands;

/**
 * Represents a command that modifies the mod attribute of the
 * person identified.
 */
public abstract class ModCommand extends Command {

    public static final String COMMAND_WORD = "mod";
    // TODO: Add the other mod commands
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Modifies the modules related to a person. "
            + "\nParameters: INDEX (must be a positive integer) MODULE [MORE_MODULES]..."
            + "\nadd INDEX MODULE [MORE_MODULES]... : Adds the entered mods to the person at the index";
    public static final String MESSAGE_MODS_EMPTY = "Mods cannot be empty!";
    public static final String MESSAGE_INDEX_EMPTY = "Index cannot be empty!";
}
