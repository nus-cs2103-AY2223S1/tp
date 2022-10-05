package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorial.Content;
import seedu.address.model.tutorial.Group;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;


/**
 * Adds a tutorial in the address book.
 */
public class AddTutorialCommand extends Command {

    public static final String COMMAND_WORD = "addtut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial to the address book. "
            + "Parameters: "
            + PREFIX_GROUP + "GROUP NUMBER "
            + PREFIX_CONTENT + "CONTENT "
            + PREFIX_TIME + "TIME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "T08 "
            + PREFIX_CONTENT + "UML Diagram "
            + PREFIX_TIME + "2022-10-01T08:00:00";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Addtut command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Group: %1$s, Content: %2$s, Time: %3$s";
    // I may need to change this later. The time format might need to be specified clearer.

    private final Group group;
    private final Content content;
    private final Time time;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, group, content, time));
    }

    /**
     * @param group   of the tutorial
     * @param content of the tutorial
     * @param time    of the tutorial
     */
    public AddTutorialCommand(Group group, Content content, Time time) {
        requireAllNonNull(group, content, time);

        this.group = group;
        this.content = content;
        this.time = time;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTutorialCommand)) {
            return false;
        }

        // state check
        AddTutorialCommand e = (AddTutorialCommand) other;
        return group.equals(e.group)
                && content.equals(e.content)
                && time.equals(e.time);
    }
}
