package seedu.address.logic.commands.persons;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

/**
 * Commands for Tasks
 */
public abstract class PersonCommand extends Command {

    public static final String COMMAND_WORD = "person";
    protected Person person = null;

    /**
     * Returns the complete command phrase for the task command with given subCommand
     *
     * @param subcommand The subcommand to be added
     * @return The complete command phrase
     */
    public static String getFullCommand(String subcommand) {
        return COMMAND_WORD + " " + subcommand;
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof Person)) {
            person = null;
            return this;
        }
        person = (Person) additionalData;
        return this;
    }

}
