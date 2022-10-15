package seedu.address.logic.commands;

/**
 * Represents a command that modifies the mod attribute of the
 * batchmate identified.
 */
public abstract class ModCommand extends Command {

    public static final String COMMAND_WORD = "mod";
    // TODO: Add the other mod commands
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Modifies the modules related to a batchmate. "
            + "\nParameters: INDEX (must be a positive integer) MODULE [MORE_MODULES]..."
            + "\nExamples:"
            + "\nmod add INDEX MODULE [MORE_MODULES]... : Adds the entered mods to the batchmate at the index."
            + "\nmod delete INDEX MODULE [MORE_MODULES]... : Deletes the entered mods from the batchmate at the index."
            + "\nmod mark INDEX MODULE [MORE_MODULES]... : Marks the entered mods of the batchmate "
            + "at the index as taken.";
    public static final String MESSAGE_MODS_EMPTY = "Mods cannot be empty!";
    public static final String MESSAGE_INDEX_EMPTY = "Index cannot be empty!";
}
